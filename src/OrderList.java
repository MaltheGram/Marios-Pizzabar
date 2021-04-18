import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/*todo:
   - When order is paid, change isPaid flag to true.
   If order flag is false when Mario prints daily revenue report, ignore that order
   - Make DailyReport class
 */


public class OrderList {
   //Getting environment variable for user profile, this is pre/defined by Windows
    private final String myDocuments = System.getenv("USERPROFILE") + "\\Documents\\";
    private final String dataDirectory = "Mario-s-Pizza-data";

    private static List<Order> orders = new ArrayList<>();

    private File selectFile(){
        String todaysFile = "log_of_daily_orders" + "_" + getCurrentSimpleDate() + ".txt";
        Path file = Paths.get(myDocuments, dataDirectory, todaysFile);

        return file.toFile();
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
            var stringToAppend = new StringFormatHandler().formatLine(o);

            Files.write(dailyLog.toPath(), stringToAppend.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeOrderStatus(Order o, Boolean paid) {

        // remove order from file
        removeOrder(o);
        // add order back into file with changed flag
        o.setIsPaid(paid);

        addOrder(o);
    }

    public void removeOrder(Order o) {
        orders.remove(o);

        removeOrderFromFile(o);
    }
    /* https://stackoverflow.com/a/45784174
    * This method reads all lines in the file, stashes the ones that DON'T contain the orderId (hexadecimal),
    * and then writes those into the file */
    private void removeOrderFromFile(Order o)  {
        File dailyLog = selectFile();
        var hexOrderId = Long.toHexString(o.getId());
        System.out.println("Removing order with id: " + hexOrderId);
        List<String> out = null;
        try {
            out = Files.lines(dailyLog.toPath())
                    .filter(line -> !line.contains(hexOrderId))
                    .collect(Collectors.toList());
            Files.write(dailyLog.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public String toString() {
        // all information of isPaid true orders, incl. orderID
        return null;
    }
}