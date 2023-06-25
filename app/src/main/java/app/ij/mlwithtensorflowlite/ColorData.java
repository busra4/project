package app.ij.mlwithtensorflowlite;



import java.util.ArrayList;
import java.util.List;


public class ColorData {
    private List<Color> colors;

    public ColorData() {
        colors = new ArrayList<>();
        // Renkleri ve eşleştirmelerini burada ekle
        colors.add(new ColorData.Color("Alice Blue",0xF0, 0xF8,0xFF));
        colors.add(new ColorData.Color("siyah",0, 0,0));
        colors.add(new ColorData.Color("beyaz",255, 255,255));
        colors.add(new ColorData.Color("AliceBlue", 0xF0, 0xF8, 0xFF));
        colors.add(new ColorData.Color("AntiqueWhite", 0xFA, 0xEB, 0xD7));
        colors.add(new ColorData.Color("Aqua", 0x00, 0xFF, 0xFF));
        colors.add(new ColorData.Color("Aquamarine", 0x7F, 0xFF, 0xD4));
        colors.add(new ColorData.Color("Azure", 0xF0, 0xFF, 0xFF));
        colors.add(new ColorData.Color("Beige", 0xF5, 0xF5, 0xDC));
        colors.add(new ColorData.Color("Bisque", 0xFF, 0xE4, 0xC4));
        colors.add(new ColorData.Color("siyah", 0x00, 0x00, 0x00));
        colors.add(new ColorData.Color("BlanchedAlmond", 0xFF, 0xEB, 0xCD));
        colors.add(new ColorData.Color("Blue", 0x00, 0x00, 0xFF));
        colors.add(new ColorData.Color("BlueViolet", 0x8A, 0x2B, 0xE2));
        colors.add(new ColorData.Color("Brown", 0xA5, 0x2A, 0x2A));
        colors.add(new ColorData.Color("BurlyWood", 0xDE, 0xB8, 0x87));
        colors.add(new ColorData.Color("CadetBlue", 0x5F, 0x9E, 0xA0));
        colors.add(new ColorData.Color("Chartreuse", 0x7F, 0xFF, 0x00));
        colors.add(new ColorData.Color("Chocolate", 0xD2, 0x69, 0x1E));
        colors.add(new ColorData.Color("Coral", 0xFF, 0x7F, 0x50));
        colors.add(new ColorData.Color("CornflowerBlue", 0x64, 0x95, 0xED));
        colors.add(new ColorData.Color("Cornsilk", 0xFF, 0xF8, 0xDC));
        colors.add(new ColorData.Color("Crimson", 0xDC, 0x14, 0x3C));
        colors.add(new ColorData.Color("Cyan", 0x00, 0xFF, 0xFF));
        colors.add(new ColorData.Color("DarkBlue", 0x00, 0x00, 0x8B));
        colors.add(new ColorData.Color("DarkCyan", 0x00, 0x8B, 0x8B));
        colors.add(new ColorData.Color("DarkGoldenRod", 0xB8, 0x86, 0x0B));
        colors.add(new ColorData.Color("DarkGray", 0xA9, 0xA9, 0xA9));
        colors.add(new ColorData.Color("DarkGreen", 0x00, 0x64, 0x00));
        colors.add(new ColorData.Color("DarkKhaki", 0xBD, 0xB7, 0x6B));
        colors.add(new ColorData.Color("DarkMagenta", 0x8B, 0x00, 0x8B));
        colors.add(new ColorData.Color("DarkOliveGreen", 0x55, 0x6B, 0x2F));
        colors.add(new ColorData.Color("DarkOrange", 0xFF, 0x8C, 0x00));
        colors.add(new ColorData.Color("DarkOrchid", 0x99, 0x32, 0xCC));
        colors.add(new ColorData.Color("DarkRed", 0x8B, 0x00, 0x00));
        colors.add(new ColorData.Color("DarkSalmon", 0xE9, 0x96, 0x7A));
        colors.add(new ColorData.Color("DarkSeaGreen", 0x8F, 0xBC, 0x8F));
        colors.add(new ColorData.Color("DarkSlateBlue", 0x48, 0x3D, 0x8B));
        colors.add(new ColorData.Color("DarkSlateGray", 0x2F, 0x4F, 0x4F));
        colors.add(new ColorData.Color("DarkTurquoise", 0x00, 0xCE, 0xD1));
        colors.add(new ColorData.Color("DarkViolet", 0x94, 0x00, 0xD3));
        colors.add(new ColorData.Color("DeepPink", 0xFF, 0x14, 0x93));
        colors.add(new ColorData.Color("DeepSkyBlue", 0x00, 0xBF, 0xFF));
        colors.add(new ColorData.Color("DimGray", 0x69, 0x69, 0x69));
        colors.add(new ColorData.Color("DodgerBlue", 0x1E, 0x90, 0xFF));
        colors.add(new ColorData.Color("FireBrick", 0xB2, 0x22, 0x22));
        colors.add(new ColorData.Color("FloralWhite", 0xFF, 0xFA, 0xF0));
        colors.add(new ColorData.Color("ForestGreen", 0x22, 0x8B, 0x22));
        colors.add(new ColorData.Color("Fuchsia", 0xFF, 0x00, 0xFF));
        colors.add(new ColorData.Color("Gainsboro", 0xDC, 0xDC, 0xDC));
        colors.add(new ColorData.Color("GhostWhite", 0xF8, 0xF8, 0xFF));
        colors.add(new ColorData.Color("Gold", 0xFF, 0xD7, 0x00));
        colors.add(new ColorData.Color("GoldenRod", 0xDA, 0xA5, 0x20));
        colors.add(new ColorData.Color("Gray", 0x80, 0x80, 0x80));
        colors.add(new ColorData.Color("Green", 0x00, 0x80, 0x00));
        colors.add(new ColorData.Color("GreenYellow", 0xAD, 0xFF, 0x2F));
        colors.add(new ColorData.Color("HoneyDew", 0xF0, 0xFF, 0xF0));
        colors.add(new ColorData.Color("HotPink", 0xFF, 0x69, 0xB4));
        colors.add(new ColorData.Color("IndianRed", 0xCD, 0x5C, 0x5C));
        colors.add(new ColorData.Color("Indigo", 0x4B, 0x00, 0x82));
        colors.add(new ColorData.Color("Ivory", 0xFF, 0xFF, 0xF0));
        colors.add(new ColorData.Color("Khaki", 0xF0, 0xE6, 0x8C));
        colors.add(new ColorData.Color("Lavender", 0xE6, 0xE6, 0xFA));
        colors.add(new ColorData.Color("LavenderBlush", 0xFF, 0xF0, 0xF5));
        colors.add(new ColorData.Color("LawnGreen", 0x7C, 0xFC, 0x00));
        colors.add(new ColorData.Color("LemonChiffon", 0xFF, 0xFA, 0xCD));
        colors.add(new ColorData.Color("LightBlue", 0xAD, 0xD8, 0xE6));
        colors.add(new ColorData.Color("LightCoral", 0xF0, 0x80, 0x80));
        colors.add(new ColorData.Color("LightCyan", 0xE0, 0xFF, 0xFF));
        colors.add(new ColorData.Color("LightGoldenRodYellow", 0xFA, 0xFA, 0xD2));
        colors.add(new ColorData.Color("LightGray", 0xD3, 0xD3, 0xD3));
        colors.add(new ColorData.Color("LightGreen", 0x90, 0xEE, 0x90));
        colors.add(new ColorData.Color("LightPink", 0xFF, 0xB6, 0xC1));
        colors.add(new ColorData.Color("LightSalmon", 0xFF, 0xA0, 0x7A));
        colors.add(new ColorData.Color("LightSeaGreen", 0x20, 0xB2, 0xAA));
        colors.add(new ColorData.Color("LightSkyBlue", 0x87, 0xCE, 0xFA));
        colors.add(new ColorData.Color("LightSlateGray", 0x77, 0x88, 0x99));
        colors.add(new ColorData.Color("LightSteelBlue", 0xB0, 0xC4, 0xDE));
        colors.add(new ColorData.Color("LightYellow", 0xFF, 0xFF, 0xE0));
        colors.add(new ColorData.Color("Lime", 0x00, 0xFF, 0x00));
        colors.add(new ColorData.Color("LimeGreen", 0x32, 0xCD, 0x32));
        colors.add(new ColorData.Color("Linen", 0xFA, 0xF0, 0xE6));
        colors.add(new ColorData.Color("Magenta", 0xFF, 0x00, 0xFF));
        colors.add(new ColorData.Color("Maroon", 0x80, 0x00, 0x00));
        colors.add(new ColorData.Color("MediumAquaMarine", 0x66, 0xCD, 0xAA));
        colors.add(new ColorData.Color("MediumBlue", 0x00, 0x00, 0xCD));
        colors.add(new ColorData.Color("MediumOrchid", 0xBA, 0x55, 0xD3));
        colors.add(new ColorData.Color("MediumPurple", 0x93, 0x70, 0xDB));
        colors.add(new ColorData.Color("MediumSeaGreen", 0x3C, 0xB3, 0x71));
        colors.add(new ColorData.Color("MediumSlateBlue", 0x7B, 0x68, 0xEE));
        colors.add(new ColorData.Color("MediumSpringGreen", 0x00, 0xFA, 0x9A));
        colors.add(new ColorData.Color("MediumTurquoise", 0x48, 0xD1, 0xCC));
        colors.add(new ColorData.Color("MediumVioletRed", 0xC7, 0x15, 0x85));
        colors.add(new ColorData.Color("MidnightBlue", 0x19, 0x19, 0x70));
        colors.add(new ColorData.Color("MintCream", 0xF5, 0xFF, 0xFA));
        colors.add(new ColorData.Color("MistyRose", 0xFF, 0xE4, 0xE1));
        colors.add(new ColorData.Color("Moccasin", 0xFF, 0xE4, 0xB5));
        colors.add(new ColorData.Color("NavajoWhite", 0xFF, 0xDE, 0xAD));
        colors.add(new ColorData.Color("Navy", 0x00, 0x00, 0x80));
        colors.add(new ColorData.Color("OldLace", 0xFD, 0xF5, 0xE6));
        colors.add(new ColorData.Color("Olive", 0x80, 0x80, 0x00));
        colors.add(new ColorData.Color("OliveDrab", 0x6B, 0x8E, 0x23));
        colors.add(new ColorData.Color("Orange", 0xFF, 0xA5, 0x00));
        colors.add(new ColorData.Color("OrangeRed", 0xFF, 0x45, 0x00));
        colors.add(new ColorData.Color("Orchid", 0xDA, 0x70, 0xD6));
        colors.add(new ColorData.Color("PaleGoldenRod", 0xEE, 0xE8, 0xAA));
        colors.add(new ColorData.Color("PaleGreen", 0x98, 0xFB, 0x98));
        colors.add(new ColorData.Color("PaleTurquoise", 0xAF, 0xEE, 0xEE));
        colors.add(new ColorData.Color("PaleVioletRed", 0xDB, 0x70, 0x93));
        colors.add(new ColorData.Color("PapayaWhip", 0xFF, 0xEF, 0xD5));
        colors.add(new ColorData.Color("PeachPuff", 0xFF, 0xDA, 0xB9));
        colors.add(new ColorData.Color("Peru", 0xCD, 0x85, 0x3F));
        colors.add(new ColorData.Color("Pink", 0xFF, 0xC0, 0xCB));
        colors.add(new ColorData.Color("Plum", 0xDD, 0xA0, 0xDD));
        colors.add(new ColorData.Color("PowderBlue", 0xB0, 0xE0, 0xE6));
        colors.add(new ColorData.Color("Purple", 0x80, 0x00, 0x80));
        colors.add(new ColorData.Color("Red", 0xFF, 0x00, 0x00));
        colors.add(new ColorData.Color("RosyBrown", 0xBC, 0x8F, 0x8F));
        colors.add(new ColorData.Color("RoyalBlue", 0x41, 0x69, 0xE1));
        colors.add(new ColorData.Color("SaddleBrown", 0x8B, 0x45, 0x13));
        colors.add(new ColorData.Color("Salmon", 0xFA, 0x80, 0x72));
        colors.add(new ColorData.Color("SandyBrown", 0xF4, 0xA4, 0x60));
        colors.add(new ColorData.Color("SeaGreen", 0x2E, 0x8B, 0x57));
        colors.add(new ColorData.Color("SeaShell", 0xFF, 0xF5, 0xEE));
        colors.add(new ColorData.Color("Sienna", 0xA0, 0x52, 0x2D));
        colors.add(new ColorData.Color("Silver", 0xC0, 0xC0, 0xC0));
        colors.add(new ColorData.Color("SkyBlue", 0x87, 0xCE, 0xEB));
        colors.add(new ColorData.Color("SlateBlue", 0x6A, 0x5A, 0xCD));
        colors.add(new ColorData.Color("SlateGray", 0x70, 0x80, 0x90));
        colors.add(new ColorData.Color("Snow", 0xFF, 0xFA, 0xFA));
        colors.add(new ColorData.Color("SpringGreen", 0x00, 0xFF, 0x7F));
        colors.add(new ColorData.Color("SteelBlue", 0x46, 0x82, 0xB4));
        colors.add(new ColorData.Color("Tan", 0xD2, 0xB4, 0x8C));
        colors.add(new ColorData.Color("Teal", 0x00, 0x80, 0x80));
        colors.add(new ColorData.Color("Thistle", 0xD8, 0xBF, 0xD8));
        colors.add(new ColorData.Color("Tomato", 0xFF, 0x63, 0x47));
        colors.add(new ColorData.Color("Turquoise", 0x40, 0xE0, 0xD0));
        colors.add(new ColorData.Color("Violet", 0xEE, 0x82, 0xEE));
        colors.add(new ColorData.Color("Wheat", 0xF5, 0xDE, 0xB3));
        colors.add(new ColorData.Color("White", 0xFF, 0xFF, 0xFF));
        colors.add(new ColorData.Color("WhiteSmoke", 0xF5, 0xF5, 0xF5));
        colors.add(new ColorData.Color("Yellow", 0xFF, 0xFF, 0x00));
        colors.add(new ColorData.Color("YellowGreen", 0x9A, 0xCD, 0x32));
        // Daha fazla renk ekleme işlemi...
    }

    public String analyzeColor(int red, int green, int blue) {
        // Renkleri döngü ile eşleştir ve rengin ismini döndür eğer tüm renkleri gezip renk bulamazsan null döndür
        for (Color color : colors) {
            if (color.match(red, green, blue)) {
                return color.getName();
            }
        }
        return null;
    }

    private class Color {
        private int red;
        private int green;
        private int blue;
        private String name;

        //Bu, Color sınıfının bir yapıcı metodudur (constructor).
        //Yapıcı metodlar, bir sınıftan yeni bir nesne oluşturulduğunda çalışan özel metodlardır.
        public Color(String name, int red, int green, int blue) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.name = name;
        }

        public boolean match(int r, int g, int b) {
            // Renkleri eşleştirme mantığı burada uygulanır
            //kırmızı ve r, yeşil ve g, mavi ve b arasındaki mutlak farkın 10'dan küçük veya eşit olup olmadığını kontrol eder.
           // Üç farkın tümü 10'dan küçük veya ona eşitse, renkleri bir eşleşme olarak kabul eder ve true değerini döndürür; aksi halde false döndürür.
            return (Math.abs(red - r) <= 10 && Math.abs(green - g) <= 10 && Math.abs(blue - b) <= 10);
        }
        //getName yöntemi, yalnızca rengin adını döndürür.
        public String getName() {
            return name;
        }
    }
}



