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
    static final int maxEntryLength = 80; // scale entry list to # characters

    public static void main(String[] args) throws FileNotFoundException {
        //nothing happens in the UI main :)
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

    private static void addBorder(String description,String header) {
        //split String in String[]
        String[] pizzaList = description.split("\n");
        int maxBorderWidth = getMaxLengthStringList(pizzaList);

        //draw top border with header
        String line = edge + fill(hFill, maxBorderWidth + hPadLength) + edge;
        System.out.println(line);
        System.out.printf(vFill + hPadding + "%s" + hPadding + vFill + "%n", padString(header, maxBorderWidth, hPadding));
        System.out.println(line);

        //draw box description
        for (String str : pizzaList) {
            System.out.printf(vFill + hPadding + "%s" + hPadding + vFill + "%n", padString(str, maxBorderWidth, hPadding));
        }

        //draw lower border
        System.out.println(line);
    }

    public static void drawMenu() throws FileNotFoundException {
        Menu pizzas = new Menu();
        String pizzaEntries ="";

        //Create massive pizza menu string
        for (int i=1 ; i < pizzas.getAllPizzas().size() + 1; i++) {
            String currentPizzaEntry = pizzas.getPizza(i).getId() +". " + pizzas.getPizza(i).getName() + " : "+pizzas.getPizza(i).getDescription();

            pizzaEntries += padString(currentPizzaEntry,maxEntryLength,".");

            pizzaEntries += pizzas.getPizza(i).getPrice() + ",-\n";
        }

        //add border
        addBorder(pizzaEntries,"Menu");
    }
}