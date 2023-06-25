package app.ij.mlwithtensorflowlite;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.media.Image;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.util.Size;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.util.concurrent.ListenableFuture;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

import app.ij.mlwithtensorflowlite.databinding.ActivityMainBinding;
import app.ij.mlwithtensorflowlite.ml.Model;

public class MainActivity extends AppCompatActivity {
    //XML dosyasındaki bileşenlere erişim sağlar.
    private ActivityMainBinding binding;
    private MainActivity activity;

    //TextView sınıfından nesne referansları alır. Metinleri görüntülemek için kullanılır.
    TextView result,resultColor, confidence;
    int imageSize = 224;


    //private EditText write;

    //metinleri sesli okutmak için kullanılır.
    private TextToSpeech ttobj;

    //savedInstanceState parametresi, aktivitenin önceki durumunu içeren bir Bundle nesnesidir.
    // Bu parametre, aktivite yeniden oluşturulduğunda veya durumunun geçici olarak kaydedildiği durumda kullanılabilir.
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // MainActivity sınıfına erişmek için kullanılır.  iç sınıfın referansını activity değişkenine atayarak dış sınıfa erişimi sağlar.
        // Daha sonra başka bir yerde bu örneğe erişmek gerektiğinde activity değişkeni kullanılabilir.
        activity = this;

        //getApplicationContext() -> uygulama bağlamını alıyor
        //OnInitListener() -> onInit yöntemi, metin okuma işlemi başlatıldığında çağrılacak olan bir dinleyiciyi tanımlar.
        //Bu metot, TTS (Text-to-Speech) motorunun başlatılması ve hazır olduğu bildirimini almak için kullanılır.
        this.ttobj=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
            }
        });

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        result = findViewById(R.id.result);
        resultColor = findViewById(R.id.resultColor);
        confidence = findViewById(R.id.confidence);

        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            startCamera();
        } else {
            // Kamera iznini ister
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
        }
    }

    private void startCamera(){
        //Bu, kamera işlemlerini yönetmek için kullanılan bir sınıftır.
        ListenableFuture<ProcessCameraProvider> cameraProvider = ProcessCameraProvider.getInstance(this);

        //SDK sürüm kontrolleri yapılır.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            //Bu dinleyici, kamera sağlayıcısı hazır olduğunda çalıştırılacak olan kodu belirtir.
            cameraProvider.addListener(
                    //Bir işlemin birden fazla kez belli periyotlarla çalışmasını istersek
                    // Runnable() geri bir sonuç döndürmez.
                    new Runnable() {
                        @Override
                        public void run() {
                            // kameranın önizlemesini görüntülemek için kullanılır.
                            Preview preview = (new Preview.Builder()).build();
                            preview.setSurfaceProvider(binding.viewFinder.getSurfaceProvider());

                            ImageAnalysis analyzer = new ImageAnalysis.Builder().build();
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                                analyzer.setAnalyzer(getMainExecutor(), new ClassAnalyzer(
                                        new ICallback() {
                                            @Override
                                            public void Callback(Bitmap bitmap) {
                                                classifyImage(bitmap);
                                            }
                                        }
                                ));
                            }

                            try{
                                // önizleme, görüntü yakalama ve analiz bileşenlerini kameranın yaşam döngüsüne bağlanır
                                cameraProvider.get().unbindAll();

                                cameraProvider.get().bindToLifecycle(
                                        activity,
                                        CameraSelector.DEFAULT_BACK_CAMERA,
                                        preview,
                                        new ImageCapture.Builder().build(),
                                        analyzer);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    getMainExecutor()
            );
        }
    }

    public void classifyImage(Bitmap image){
        try {
            //Model sınıfından bir örnek oluşturulur.
            Model model = Model.newInstance(getApplicationContext());

            // İlk boyut 1 olduğu için yalnızca bir görüntü işlenir.
            // İkinci ve üçüncü boyutlar, görüntünün yükseklik ve genişlik boyutlarını belirtir (224x224).
            // Dördüncü boyut ise renk kanallarını temsil eder (3, RGB renk uzayında üç kanal vardır).
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
            //ByteBuffer görüntünün piksel verilerini içeren bellek bloğunu temsil eder.
            //allocateDirect() metoduyla bellekte bir blok ayrılır.
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);  // her bir piksel için 4 baytlık (float32) bir alan ayrıldı,  genişlik, yükseklik ve renk kanalları
            //baytların sıralamasını cihazın yerel sıralamasına (endianness) göre ayarlar.  verilerin doğru şekilde saklandığından ve okunduğundan emin olunur.
            byteBuffer.order(ByteOrder.nativeOrder());

            // get 1D array of 224 * 224 pixels in image
            // image nesnesinin belirli bir bölgesindeki piksel değerlerini intValues dizisine kopyalar.
            int[] intValues = new int[imageSize * imageSize];
            image.getPixels(intValues, 0, imageSize, (image.getWidth() - imageSize) / 2, (image.getHeight() - imageSize) / 2, imageSize, imageSize);

            // iterate over pixels and extract R, G, and B values. Add to bytebuffer.

            //val değişkeni üzerinden renk kanallarına ayrıştırma işlemi gerçekleştirilir. >> operatörü
            // ile bit kaydırma yapılır ve & operatörü ile bit düzeyinde AND işlemi uygulanır.
            // Elde edilen renk kanalı değerleri (((val >> 16) & 0xFF), ((val >> 8) & 0xFF), (val & 0xFF))
            // 0 ila 1 aralığında normalize edilir. Bunun için (1.f / 255.f) ile çarpılır.
            // Normalleştirilmiş float değerler, sırasıyla byteBuffer'a eklenir.
            //Bu işlem sonucunda, byteBuffer içerisinde piksel değerlerini temsil eden float
            // değerleri içeren bir veri oluşur. Bu veri, daha sonra modele girdi olarak verilir.
            int pixel = 0;
            for (int i = 0; i < imageSize; i++) {
                for (int j = 0; j < imageSize; j++) {
                    int val = intValues[pixel++]; // RGB
                    byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255.f));
                    byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255.f));
                    byteBuffer.putFloat((val & 0xFF) * (1.f / 255.f));
                }
            }

            inputFeature0.loadBuffer(byteBuffer);

            // Runs model inference and gets result.
            Model.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            float[] confidences = outputFeature0.getFloatArray();
            // find the index of the class with the biggest confidence.

            //Sonraki adımlarda, en yüksek güvenilirliğe sahip sınıfın indeksi (maxPos) ve güvenilirlik değeri (maxConfidence) belirlenir.
            // Bunun için confidences dizisi üzerinde bir döngü kullanılır. Dizideki her bir değer kontrol edilir ve en yüksek güvenilirliğe
            // sahip sınıfın indeksi ve değeri kaydedilir.

            int maxPos = 0;
            float maxConfidence = 0;
            for (int i = 0; i < confidences.length; i++) {
                if (confidences[i] > maxConfidence) {
                    maxConfidence = confidences[i];
                    maxPos = i;
                }
            }
            String[] classes = {"checkered", "dotted", "floral", "solid", "striped", "zigzag"};
            result.setText(classes[maxPos]);


            //Döngü kullanarak, classes dizisi ve confidences dizisi kullanılarak tüm sınıfların adlarını ve güven değerlerini oluşturulan metin stringi olan s'ye eklenir.
            // String.format kullanılarak her bir sınıfın adı ve güven değeri % formatında eklenir.
            // Örneğin, "checkered: 80.0%" gibi bir metin oluşturulur.
            String s = "";
            for (int i = 0; i < classes.length; i++) {
                s += String.format("%s: %.1f%%\n", classes[i], confidences[i] * 100);
            }
            confidence.setText(s);


            ColorData colorData = new ColorData();

            // Renkleri saklamak için bir liste oluştur
            List<String> colors = new ArrayList<>();

            // Desen bölgesindeki pikselleri analiz et
            for (int pixel1 : intValues) {
                // Renk değerlerini elde et
                int red = Color.red(pixel1);
                int green = Color.green(pixel1);
                int blue = Color.blue(pixel1);

                // Renkleri analiz et ve listeye ekle
                String colorName = colorData.analyzeColor(red, green, blue);
                if( colorName == null)
                    continue;

                if (!colors.contains(colorName)) {
                    colors.add(colorName);
                }

                // İlk 5 rengi kontrol et
                if (colors.size() == 5)
                    break;
            }

            if(colors.size() == 0)
                colors.add("Diğer");

            // Renkleri metin formatında döndür
            StringBuilder sb = new StringBuilder();
            for (String color : colors) {
                sb.append(color).append(", ");
            }
            if (sb.length() > 2) {
                sb.setLength(sb.length() - 2); // Son iki karakteri kırp (virgül ve boşluk)
            }
            resultColor.setText(sb);
            ttobj.speak(sb + classes[maxPos], TextToSpeech.QUEUE_FLUSH, null);
            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
            // TODO Handle the exception
        }
    }




    private class ClassAnalyzer implements ImageAnalysis.Analyzer{
        private ICallback callback;

        public ClassAnalyzer(ICallback call){
            callback = call;
        }

        @SuppressLint("UnsafeOptInUsageError")
        @Override
        public void analyze(@NonNull ImageProxy image) {
            callback.Callback(toBitmap(image.getImage()));

            image.close();
        }

        private Bitmap toBitmap(Image image) {
            // İmajın düzlemlerini (Y, U, V) elde et
            Image.Plane[] planes = image.getPlanes();
            ByteBuffer yBuffer = planes[0].getBuffer();
            ByteBuffer uBuffer = planes[1].getBuffer();
            ByteBuffer vBuffer = planes[2].getBuffer();

            // Her bir düzlem için boyutları hesapla
            int ySize = yBuffer.remaining();
            int uSize = uBuffer.remaining();
            int vSize = vBuffer.remaining();

            // YUV verilerini içerecek NV21 tipinde bir byte dizisi oluştur
            byte[] nv21 = new byte[ySize + uSize + vSize];

            // Y, U ve V verilerini NV21 dizisine kopyala
            yBuffer.get(nv21, 0, ySize);
            vBuffer.get(nv21, ySize, vSize);
            uBuffer.get(nv21, ySize + vSize, uSize);

            // NV21 verilerinden bir YuvImage nesnesi oluştur
            YuvImage yuvImage = new YuvImage(nv21, ImageFormat.NV21, image.getWidth(), image.getHeight(), null);

            // YuvImage'ı JPEG formatına sıkıştır
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            yuvImage.compressToJpeg(new Rect(0, 0, yuvImage.getWidth(), yuvImage.getHeight()), 75, out);

            // Sıkıştırılmış JPEG verilerini bir byte dizisine dönüştür
            byte[] imageBytes = out.toByteArray();

            // Byte dizisini bir Bitmap'e dönüştür
            return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        }
    }

    public interface ICallback{
        void Callback(Bitmap bitmap);
    }
}

// NOT :
// NV21 (YUV420SP) formatı, video ve görüntü işleme alanlarında yaygın olarak kullanılan bir renk düzenidir.
// YUV (YCbCr) renk modelini kullanır ve bir görüntünün parlaklık (luma) ve renk bilgilerini (chroma) içerir.
// NV21, 8 bitlik bir renk derinliğiyle çalışır ve bir görüntüyü 2 plana ayırır:
// Y (luma) bileşeni: Görüntünün parlaklık bilgisini içerir. Bu bileşen, her piksel için tek bir değer içerir.
// UV (chroma) bileşeni: Görüntünün renk bilgisini içerir. Bu bileşen, iki değeri sıkıştırılmış bir şekilde içerir.
// Her iki piksel için bir çift U ve V değeri paylaşılır. NV21 formatında, önce U (mavi-mor) bileşeni, ardından V (kırmızı-yeşil) bileşeni saklanır.
// Bu düzen, Y bileşenini bir plana, U ve V bileşenlerini ise birleşik bir plana yerleştirir.
// Bu sayede, görüntüyü temsil eden byte dizisi, ardışık Y değerlerini takip eden ardışık UV çiftlerini içerir.
// Bu özellik, NV21 formatının veri sıkıştırma avantajlarına ve bellek verimliliğine sahip olmasını sağlar.
// Özetle, NV21 formatı, Y bileşenini bir plana, UV bileşenlerini ise birleşik bir plana yerleştirerek görüntüyü temsil eden bir düzenlemeyi ifade eder.