import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderList {
   //Getting environment variable for user profile, this is pre/defined by Windows
    private final String myDocuments = System.getenv("USERPROFILE") + "\\Documents\\";
    private final String dataDirectory = "Mario-s-Pizza-data";
   // private final String fileName = "log_of_daily_orders" + "_" + date + ".txt";

    private static List<Order> orders = new ArrayList<>();

    private File selectFile(){
        String todaysFile = "log_of_daily_orders" + "_" + getCurrentSimpleDate() + ".txt";
        Path file = Paths.get(myDocuments, dataDirectory, todaysFile);
        System.out.println(file);

        return file.toFile();
    }

    public OrderList() {

    }


    public void addOrder(Order o) {
        File dailyLog = selectFile();

        ensureThatFileExists(dailyLog);

        orders.add(o);

        writeOrderToFile(o, dailyLog);
    }

    private void ensureThatFileExists(File dailyLog) {
        try {
            if(!dailyLog.exists()) {
                dailyLog.getParentFile().mkdirs();
                dailyLog.createNewFile();
                System.out.println("created log file for day: " + dailyLog.getName());



            } else {
                System.out.println("file exists for day: " + dailyLog.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeOrderToFile(Order o, File dailyLog) {
        try {
            var stringToAppend = "";

            for(OrderLineItem lineItem : o.getListOfOrderLineItems()) {
                stringToAppend += lineItem.toString() + "\n";
            }
            stringToAppend += "ORDER TOTAL: " + o.getTotalPrice() + "\n\n";

            Files.write(dailyLog.toPath(), stringToAppend.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeOrder(Order o) {
        orders.remove(o);
        removeOrderFromFile(o);
    }

    private void removeOrderFromFile(Order o) {

    }

    private String getCurrentSimpleDate() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        return timeStamp.substring(0, 8);
    }

    public List<Order> getOrderList() {
        return orders;
    }

    public void printOrderList() {

        for(int i = 0; i < getOrderList().size(); i++) {
            System.out.println(getOrderList().get(i));
        }
    }
}