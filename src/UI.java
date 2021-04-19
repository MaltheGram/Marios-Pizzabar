import java.io.Console;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class UI {
    //UI SETTINGS
    //BORDER SETTINGS, h = horizontal, v = vertical
    private static final String edge = "\u2548";
    private static final String hFill = "\u2501";
    private static final String vFill = "\u2502";
    private static final String hPadding = " ";
    private static final int hPadLength = 2;

    //BOX BODY SETTINGS
    private static final int maxMenuEntryLength = 90; // scale entry list to # characters
    private static final int maxOrderListEntryLength = 90; // scale entry list to # characters

    //PAGEHEADER SETTINGS
    private static final String pageHeader =
            """
            ███╗   ███╗ █████╗ ██████╗ ██╗ ██████╗ ███████╗    ██████╗ ██╗███████╗███████╗ █████╗
            ████╗ ████║██╔══██╗██╔══██╗██║██╔═══██╗██╔════╝    ██╔══██╗██║╚══███╔╝╚══███╔╝██╔══██╗
            ██╔████╔██║███████║██████╔╝██║██║   ██║███████╗    ██████╔╝██║  ███╔╝   ███╔╝ ███████║
            ██║╚██╔╝██║██╔══██║██╔══██╗██║██║   ██║╚════██║    ██╔═══╝ ██║ ███╔╝   ███╔╝  ██╔══██║
            ██║ ╚═╝ ██║██║  ██║██║  ██║██║╚██████╔╝███████║    ██║     ██║███████╗███████╗██║  ██║
            ╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝ ╚═════╝ ╚══════╝    ╚═╝     ╚═╝╚══════╝╚══════╝╚═╝  ╚═╝
            """; // scale entry list to # characters
    private static final String vFillHeader = ":";
    private static final String hPaddingHeader = ".";

    public static void main(String[] args) throws FileNotFoundException{
    }

    //check string stream for max length of string
    private static int getMaxLengthStringList(String[] list) {
        return Arrays.stream(list).map(String::length).reduce(0, Integer::max);
        /*
        int len = 0;
        for (String str : list) {
            len = Math.max(str.length(), len);
        }
        return len;
         */
    }

    //add padding to string
    private static String padString(String str, int len, String ch) {
        return str + ch.repeat( len - str.length() );
        /*
        str + ch.repeat(len)
        for (int i=str.length();i<len;i++) {
            str += ch;
        }
        return str;
         */
    }

    //to create horizontal lines, example: border
    private static String fill(String border, int len) {
        return border.repeat(len);
        /*
        String frame = "";
        for (int i = 0; i < len; i++) {
            frame += border;
        }
        return frame;
         */
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
    public static void drawOrderlist(OrderList orders) {
        String orderEntries = "";
        for (Order o : orders.getOrderList()) {
            String orderEntry = "[Ordre #" + o.getId() +" | Pickup Time: " + o.getPickUpTime() + "]";

            orderEntry = centerString(orderEntry,maxOrderListEntryLength,hFill);

            orderEntry = padString(orderEntry,maxOrderListEntryLength,hFill) + "\n";

            for (OrderLineItem l : o.getListOfOrderLineItems()) {
                orderEntry += "x"+l.getAmount() + " #"+l.getPizza().getId()+ " " + l.getPizza().getName();

                if (l.getComment().length() > 0) {orderEntry += " (" + l.getComment() + ")";}

                orderEntry += "\n";
            }

            orderEntries += orderEntry;
            String pricePoint = String.valueOf(numberShorten(o.getTotalPrice()));

            String orderTotal = "Total: " + pricePoint + ",- DKK";

            orderTotal = padString(orderTotal,maxOrderListEntryLength," ");

            orderEntries += orderTotal + "\n";
        }
        //Create massive pizza menu string

        //add border
        addBorder(orderEntries,"Active Orders",hPadding);
    }

    public static String numberShorten(double d) {
        if(d == (long) d)
            return String.format("%d",(long) d);
        else
            return String.format("%s",d);
    }

    public static void drawMenu(Menu menu){
        String pizzaEntries ="";

        //Create massive pizza menu string
        for (Pizza p : menu.getAllPizzas()) {

            String currentPizzaEntry = p.getId() +". " + p.getName() + " : "+p.getDescription();

            String pizzaPrice = Math.round(p.getPrice()) + ",-\n";
            pizzaEntries += padString(currentPizzaEntry,maxMenuEntryLength - pizzaPrice.length()+1,".");

            pizzaEntries += pizzaPrice;

        }

        //add border
        addBorder(pizzaEntries,"Menu",hPadding);
    }

    public static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}