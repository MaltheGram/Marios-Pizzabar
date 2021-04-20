import java.util.Arrays;
import java.util.Collection;

public class UI {
    //UI SETTINGS
    //BORDER SETTINGS, h = horizontal, v = vertical
    private static final String edge = "\u2548";
    private static final String hFill = "\u2501";
    private static final String vFill = "\u2502";
    private static final String hPadding = "  ";

    //BOX BODY SETTINGS
    private static final int maxMenuEntryLength = 90; // scale entry list to # characters
    private static final int maxOrderListEntryLength = 70; // scale entry list to # characters
    private static final int columnPadding = 1; // padding between menu and orderlist when using drawOrderListAndMenu

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

    //check string stream for max length of string
    private static int getMaxLengthStringList(String[] list) {
        return Arrays.stream(list).map(String::length).reduce(0, Integer::max);
    }

    //add padding to string
    private static String padString(String str, int len, String ch) {
        return str + ch.repeat( Integer.max(0, len - str.length()) );
    }

    //to create horizontal lines, example: border
    private static String fill(String border, int len) {
        return border.repeat( Integer.max(0, len));
    }
    //centers string with a given column width
    private static String centerString(String header,int len,String filler) {
        return fill(filler,(len - header.length())/2) + header;
    }

    //convert double to string and remove extra zero's
    public static String numberShorten(double d) {
        if(d == (int) d)
            return String.format("%d",(int) d);
        else
            return String.format("%s",d);
    }

    //takes a given string with information in lines and adds borders around it all
    private static String addBorder(String description,String header,String hPadding) {
        //split String in String[]
        String borderedString = "";
        String[] infoList = description.split("\n");
        int maxBorderWidth = getMaxLengthStringList(infoList);

        //draw top border with header
        String line = edge + fill(hFill, maxBorderWidth + 2*hPadding.length()) + edge + "\n";

        borderedString += line;

        String headerCentered = centerString(header,maxBorderWidth+ 2*hPadding.length()," ");
        borderedString += vFill + padString(headerCentered, maxBorderWidth+ 2*hPadding.length(), " ") + vFill + "\n";

        borderedString += line;

        //draw box description
        for (String str : infoList) {
            borderedString += vFill + hPadding + padString(str, maxBorderWidth, " ") + hPadding + vFill + "\n";
        }

        //draw lower border
        borderedString += line;

        return borderedString;
    }

    //draws the header for the program
    public static void drawHeader() {
        String[] headerList = pageHeader.split("\n");
        int headerLength = maxMenuEntryLength + maxOrderListEntryLength+columnPadding+4*hPadding.length()+columnPadding;

        for (String str : headerList) {
            String headerCentered = centerString(str,headerLength,hPaddingHeader);
            System.out.printf(vFillHeader + "%s" + vFillHeader + "\n", padString(headerCentered, headerLength, hPaddingHeader));
        }
    }

    //creates OrderList String and returns it
    public static String makeOrderList(Collection<Order> orders) {
        String orderEntries = "";

        //in case of Orders = empty
        if (orders.isEmpty()) {
            orderEntries += fill(" ",maxOrderListEntryLength);
            return addBorder(orderEntries,"Aktive Ordre (" + orders.size() + ")",hPadding);
        }

        //creating orders
        for (Order o : orders) {
            //create Order headline
            String orderEntry = "[Ordre #" + o.getId() +" | Leverings tid: " + o.getPickUpTime() + "]";

            orderEntry = centerString(orderEntry,maxOrderListEntryLength,hFill);

            orderEntry = padString(orderEntry,maxOrderListEntryLength,hFill) + "\n";

            //create Order Strings
            for (OrderLineItem l : o.getLineItems()) {
                orderEntry += "x"+l.getAmount() + " #"+l.getPizza().getId()+ " " + l.getPizza().getName();

                if (l.getComment().length() > 0) {orderEntry += " (" + l.getComment() + ")";}

                orderEntry += "\n";
            }

            //create Order total
            orderEntries += orderEntry;
            String pricePoint = String.valueOf(numberShorten(o.getTotalPrice()));

            String orderTotal = "Total: " + pricePoint + "kr.";

            orderTotal = padString(orderTotal,maxOrderListEntryLength," ");

            orderEntries += orderTotal + "\n";
        }

        //add border
        return addBorder(orderEntries,"Aktive Ordre (" + orders.size() + ")",hPadding);
    }

    //creates Menu as a String and returns it
    public static String makeMenu(Collection<Pizza> menu){
        String pizzaEntries ="";

        //Create massive pizza menu string
        for (Pizza p : menu) {
            String currentPizzaEntry = p.getId() +". " + p.getName() + " : "+p.getDescription();

            String pizzaPrice = Math.round(p.getPrice()) + ",-\n";
            pizzaEntries += padString(currentPizzaEntry,maxMenuEntryLength - pizzaPrice.length(),".");

            pizzaEntries += pizzaPrice;
        }

        //add border
        return addBorder(pizzaEntries,"Pizza Menu",hPadding);
    }

    public static void drawOrderList(Collection<Order> orders) {
        System.out.println(makeOrderList(orders));
    }

    public static void drawMenu(Collection<Pizza> menu){
        System.out.println(makeMenu(menu));
    }

    public static void drawOrderlistAndMenu(Collection<Order> orders, Collection<Pizza> menu) {
        String[] ordersStringArray = makeOrderList(orders).split("\n");
        String[] menuStringArray = makeMenu(menu).split("\n");
        int maxLines = Math.max(ordersStringArray.length,menuStringArray.length);

        String combined = "";

        //Add OrderList and Menu to one single string
        for (int i=0;i<maxLines;i++) {
            if (i<ordersStringArray.length) {
                combined += ordersStringArray[i];
            } else {
                combined += fill(" ",maxOrderListEntryLength+2*hPadding.length()+2*vFill.length());
            }
            combined += fill(" ",columnPadding);

            if (i<menuStringArray.length) {
                combined += menuStringArray[i];
            } else {
                combined += fill(" ",maxMenuEntryLength);
            }
            combined += "\n";

        }

        System.out.println(combined);
    }

    public static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}