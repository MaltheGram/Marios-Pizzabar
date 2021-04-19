import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/*todo:
   - When order is paid, change isPaid flag to true.
   If order flag is false when Mario prints daily revenue report, ignore that order
   - Make DailyReport class
   - make SimpleDateTime into a separate class (use in Order and OrderList)
 */


public class OrderList {
   //Getting environment variable for user profile, this is pre/defined by Windows
    private final String myDocuments = System.getenv("USERPROFILE") + "\\Documents\\";
    private final String dataDirectory = "Mario-s-Pizza-data";
    private static Map<String, Order> orders = new HashMap<>();

    private File selectFile(){
        String todaysFile = "log_of_daily_orders" + "_" + getCurrentSimpleDate() + ".txt";
        Path file = Paths.get(myDocuments, dataDirectory, todaysFile);

        return file.toFile();
    }

    public void addOrder(Order o) {
        File dailyLog = selectFile();

        ensureThatFileExists(dailyLog);

        orders.put(o.getId(), o);

        writeOrderToFile(o, dailyLog);
    }
    /* mkdir() is part of File class, which creates a directory denoted by an abstract file name (returns true if directory was created)
     The java.io.File.getParentFile() method returns the abstract pathname of this abstract pathname's parent, or null if this pathname does not name a parent directory.
     */

    private void ensureThatFileExists(File dailyLog) {
        try {
            if(!dailyLog.exists()) {
                dailyLog.getParentFile().mkdirs();
                dailyLog.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*File class is an abstract representation of file and directory path names.
    java.nio.file package overcomes many of the limitations of the java.io.File class (but requires the classes that use it to implement the Serializable interface)*/
    private void writeOrderToFile(Order o, File dailyLog) {
        try {
            var stringToAppend = new StringFormatHandler().formatLineForOrderList(o);

            Files.write(dailyLog.toPath(), stringToAppend.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeOrderStatus(String orderNR, Boolean paid) {
        var o = orders.get(orderNR);

        // remove order from order arrayList and file
        removeOrder(orderNR);
        // add order back
        o.setIsPaid(paid);

        addOrder(o);
    }

    public void removeOrder(String id) {
        removeOrderFromFile(orders.get(id));
        orders.remove(id);
    }

    /* https://stackoverflow.com/a/45784174
    * This method reads all lines in the file, stashes the ones that DON'T contain the orderId (hexadecimal),
    * and then writes those into the file
    * Collectors class implement various reduction operations, such as accumulating elements into collections, summarizing elements according to various criteria, etc.*/
    private void removeOrderFromFile(Order o)  {
        File dailyLog = selectFile();
        var id = o.getId();
        List<String> out = null;
        try {
            out = Files.lines(dailyLog.toPath())
                    .filter(line -> !line.contains(id))
                    .collect(Collectors.toList());
            Files.write(dailyLog.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getCurrentSimpleDate() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        return timeStamp;
    }

    public Map<String, Order> getOrderList() {
        return orders;
    }
    
    public void showDailyReport() {
        DailyReport report = new DailyReport();
        var formattedDate = new SimpleDateFormat("d.M.yyyy").format(new Date());
        report.printDailyReport(selectFile(), formattedDate);
    }

}