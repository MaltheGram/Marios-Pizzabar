import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderList {
    private String objectCreationTime;
    private final String filePath = "E:\\IntelliJ Projects\\KEA Software Development\\Group-Project---Mario-s-Pizza\\resources\\log_of_daily_orders";
    private List<Order> orders = new ArrayList<>();

    OrderList() {
        this.objectCreationTime = getCurrentSimpleDate();
    }

    private String getCurrentSimpleDate() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        return timeStamp.substring(0, 8);
    }

    public List<Order> getOrderList() {
        return orders;
    }

    public void addOrder(Order o) {
        orders.add(o);
        writeOrderToFile(o);
    }

    private void writeOrderToFile(Order o) {
        // if file exists
        // add pizza object to file
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(o);
            objectOut.close();
            System.out.println("pizza successfully added to file");
        } catch (Exception e) {
            System.out.println("could not add pizza");
            e.printStackTrace();
        }
    }

    public void removeOrder(Order o) {
        orders.remove(o);
        removeOrderFromFile(o);
    }

    private void removeOrderFromFile(Order o) {
    }
}
