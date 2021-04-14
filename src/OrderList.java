import java.io.FileNotFoundException;
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

    @Override
    public String toString() {
        return this.orders.toString();
    }
}
