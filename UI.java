public class UI {
    public static void main(String[] args) {
        addBorder("waddup","hello","I eat pizza every day brother");
    }

    private static int getMaxLength(String... strings) {
        int len = 0;
        for (String str : strings) {
            len = Math.max(str.length(), len);
        }
        return len;
    }

    private static String padString(String str, int len) {
        for (int i=str.length();i<len;i++) {
            str += " ";
        }
        return str;
    }

    private static String fill(char border, int len) {
        String frame = "";
        for (int i = 0; i < len; i++) {
            frame += border;
        }
        return frame;
    }

    public static void addBorder(String... strings) {
        //Border settings, h = horizontal, v = vertical
        final char edge = 'X';
        final char hFill = '=';
        final char vFill = '|';
        final String hPadding = " ";

        int maxBorderWidth = getMaxLength(strings);

        String line = edge + fill(hFill, maxBorderWidth + hPadding.length()*2) + edge;
        System.out.println(line);

        for (String str : strings) {
            System.out.printf(vFill + hPadding + "%s" + hPadding + vFill + "%n", padString(str, maxBorderWidth));
        }
        System.out.println(line);
    }

}
