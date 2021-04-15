import java.io.FileNotFoundException;
import java.util.ArrayList;

public class UI {
    //UI SETTINGS
    //BORDER SETTINGS, h = horizontal, v = vertical
    static final String edge = "\u2548";
    static final String hFill = "\u2501";
    static final String vFill = "\u2502";
    static final String hPadding = " ";
    static final int hPadLength = 2;

    //BOX BODY SETTINGS
    static final int maxMenuEntryLength = 80; // scale entry list to # characters
    static final int maxOrderListEntryLength = 80; // scale entry list to # characters

    //PAGEHEADER SETTINGS
    static final String pageHeader = "███╗   ███╗ █████╗ ██████╗ ██╗ ██████╗ ███████╗    ██████╗ ██╗███████╗███████╗ █████╗ \n" +
            "████╗ ████║██╔══██╗██╔══██╗██║██╔═══██╗██╔════╝    ██╔══██╗██║╚══███╔╝╚══███╔╝██╔══██╗\n" +
            "██╔████╔██║███████║██████╔╝██║██║   ██║███████╗    ██████╔╝██║  ███╔╝   ███╔╝ ███████║\n" +
            "██║╚██╔╝██║██╔══██║██╔══██╗██║██║   ██║╚════██║    ██╔═══╝ ██║ ███╔╝   ███╔╝  ██╔══██║\n" +
            "██║ ╚═╝ ██║██║  ██║██║  ██║██║╚██████╔╝███████║    ██║     ██║███████╗███████╗██║  ██║\n" +
            "╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝ ╚═════╝ ╚══════╝    ╚═╝     ╚═╝╚══════╝╚══════╝╚═╝  ╚═╝\n"; // scale entry list to # characters
    static final String edgeHeader = ".";
    static final String hFillHeader = ".";
    static final String vFillHeader = ".";
    static final String hPaddingHeader = ".";

    public static void main(String[] args) throws FileNotFoundException{
        drawHeader();
        drawMenu();
    }

    //check string stream for max length of string
    private static int getMaxLengthStringList(String[] list) {
        int len = 0;
        for (String str : list) {
            len = Math.max(str.length(), len);
        }
        return len;
    }

    //add padding to string
    private static String padString(String str, int len, String ch) {
        for (int i=str.length();i<len;i++) {
            str += ch;
        }
        return str;
    }

    //to create horizontal lines, example: border
    private static String fill(String border, int len) {
        String frame = "";
        for (int i = 0; i < len; i++) {
            frame += border;
        }
        return frame;
    }

    private static String centerString(String header,int len,String filler) {
        return fill(filler,len/2 - header.length()/2) + header;
    }

    private static void addBorder(String description,String header,String hPadding) {
        //split String in String[]
        String[] pizzaList = description.split("\n");
        int maxBorderWidth = getMaxLengthStringList(pizzaList);

        //draw top border with header
        String line = edge + fill(hFill, maxBorderWidth + hPadLength) + edge;
        System.out.println(line);
        String headerCentered = centerString(header,maxBorderWidth,hPadding);
        System.out.printf(vFill + hPadding + "%s" + hPadding + vFill + "\n", padString(headerCentered, maxBorderWidth, hPadding));
        System.out.println(line);

        //draw box description
        for (String str : pizzaList) {
            System.out.printf(vFill + hPadding + "%s" + hPadding + vFill + "\n", padString(str, maxBorderWidth, hPadding));
        }

        //draw lower border
        System.out.println(line);
    }

    public static void drawHeader() {
        //draw top border with header
        String[] headerList = pageHeader.split("\n");
        int headerLength = maxMenuEntryLength + maxOrderListEntryLength;
        //String line = edgeHeader + fill(hFillHeader, headerLength) + edgeHeader;

        //System.out.println(line);

        for (String str : headerList) {
            String headerCentered = centerString(str,headerLength,hPaddingHeader);
            System.out.printf(vFillHeader + "%s" + vFillHeader + "\n", padString(headerCentered, headerLength, hPaddingHeader));
        }

        //System.out.println(line);
    }

    public static void drawMenu() throws FileNotFoundException {
        Menu pizzas = new Menu();
        String pizzaEntries ="";

        //Create massive pizza menu string
        for (int i=1 ; i < pizzas.getAllPizzas().size() + 1; i++) {

            String currentPizzaEntry = pizzas.getPizza(i).getId() +". " + pizzas.getPizza(i).getName() + " : "+pizzas.getPizza(i).getDescription();

            pizzaEntries += padString(currentPizzaEntry,maxMenuEntryLength,".");

            pizzaEntries += Math.round(pizzas.getPizza(i).getPrice()) + ",-\n";
        }

        //add border
        addBorder(pizzaEntries,"Menu",hPadding);
    }
}