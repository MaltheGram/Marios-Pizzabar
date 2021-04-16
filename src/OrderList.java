import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderList { // must only be instantiated once, at the start of the program! won't work otherwise
    private String objectCreationTime = getCurrentSimpleDate();
    private String fileName = "log_of_daily_orders" + "_" + objectCreationTime;
    private final String filePath = "E:\\IntelliJ Projects\\KEA Software Development\\Group-Project---Mario-s-Pizza\\resources\\" + fileName;
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order o) {
        orders.add(o);
        writeOrderToFile(o);
    }

    private void writeOrderToFile(Order o) {
        try {
            var stringToAppend = "";

            // while there are more orderLines, add lines to string
            // add order total amount

            for(OrderLineItem lineItem : o.getListOfOrderLineItems()) {
                stringToAppend += lineItem.toString();
            }
            stringToAppend += "ORDER TOTAL: " + o.getTotalPrice();

            Files.write(Paths.get(fileName), stringToAppend.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {

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
}
