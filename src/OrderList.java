import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class OrderList {

    public static void main(String[] args) throws FileNotFoundException {
        OrderList orderList = new OrderList();
        Menu menu = new Menu();

        Order myOrder = orderList.createNewOrder();
        Pizza myPizza = menu.getPizza(5);
        Pizza myOtherPizza = menu.getPizza(11);

        myOrder.addPizza(myPizza, 1);
        myOrder.addPizza(myOtherPizza, 2);

        System.out.println("orderList = " + orderList);
    }

    private Collection<Order> orders = new ArrayList<>();

    public OrderList() {

    }
    
    public Order createNewOrder() {
        Order order = new Order();
        this.orders.add(order);

        return order;
    }

    public void saveOrderListToDisk(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        BufferedWriter bw = new BufferedWriter(fw);

        for (Order order : this.orders) {
            bw.write( order.toString() );
            bw.newLine();
        }

        bw.close();
        fw.close();
    }

    @Override
    public String toString() {
        return this.orders.toString();
    }
}
