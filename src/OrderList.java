import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderList {
    private final String filePath = "E:\\IntelliJ Projects\\KEA Software Development\\Group-Project---Mario-s-Pizza\\resources\\log_of_daily_orders";
    private List<Order> orders = new ArrayList<>();

    OrderList() {
        this.orders = orders;
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
            FileOutputStream fileOut = new FileOutputStream((filePath));
            ObjectOutputStream objectOut = new ObjectOutputStream((fileOut));
            objectOut.writeObject((o));
            objectOut.close();
            System.out.println("pizza successfully added to file");
        } catch (Exception e) {
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
