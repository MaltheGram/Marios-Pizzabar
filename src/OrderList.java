import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class OrderList {
   /*Getting environment variable for user profile, this is pre-defined by Windows*
   Program tries to fin Documents folder, but if it doesn't, folder is made elsewhere*/
    private final String myDocuments = System.getenv("USERPROFILE") + "\\Documents\\";
    private final String dataDirectory = "Mario-s-Pizza-data";

    private final Map<String, Order> orders = new HashMap<>();

    public void addOrder(Order o) {
        File dailyLog = selectFile();

        ensureThatFileExists(dailyLog);

        orders.put(o.getId(), o);

        writeOrderToFile(o, dailyLog);
    }

    private File selectFile(){
        String todaysFile = "log_of_daily_orders" + "_" + getCurrentSimpleDate() + ".txt";
        Path file = Paths.get(myDocuments, dataDirectory, todaysFile);

        return file.toFile();
    }

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

    private String getCurrentSimpleDate() {
        return new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
    }

    public Collection<Order> getAllOpenOrders() {
        return this.orders.values().stream().sorted().collect(Collectors.toList());
    }

    public void changeOrderStatus(String orderNR, Boolean paid) {
        var o = orders.get(orderNR);

        removeOrder(orderNR);

        o.setHasBeenPaidFor(paid);

        writeOrderToFile(o, selectFile());
    }

    public void removeOrder(String id) {
        removeOrderFromFile(orders.get(id));
        orders.remove(id);
    }

    public void showDailyReport() {
        DailyReport report = new DailyReport();
        var formattedDate = new SimpleDateFormat("d.M.yyyy").format(new Date());
        report.printDailyReport(selectFile(), formattedDate);
    }

    /* https://stackoverflow.com/a/45784174
    * This method reads all lines in the file, stashes the ones that DON'T contain the orderId,
    * and then writes those into the file
    * Collectors class implement various reduction operations, such as accumulating elements into collections, summarizing elements according to various criteria, etc.*/
    private void removeOrderFromFile(Order o)  {

        if(o.equals(null)) {
            System.out.println("ordre " + o.getId() + " eksisterer ikke");
            return;
        } else {
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
    }
}