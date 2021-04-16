import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class PizzaMain {

    static OrderList newList = new OrderList();

    public static void main(String[] args) throws IOException {
        //do stuff
        Menu menu = null;
        try {
            menu = new Menu();
        } catch (FileNotFoundException e) {
            System.out.println("woops");
        }
        UI.drawHeader();
        UI.drawMenu();
        System.out.println(menu);

        System.out.println(menu.getPizza(6));

        System.out.printf("%-50.50s %-50.50s%n", "Column 1", "Column 2");

        // Add loop to keep adding pizzas

        Order order1 = new Order();
        Order order2 = new Order();

        order1.addPizza();
        order1.pickUpTime();
        Invoice.printInvoice(order1);

        order2.addPizza();
        order2.pickUpTime();
        Invoice.printInvoice(order2);

        OrderList orderList = new OrderList();
        orderList.addOrder(order1);
        orderList.addOrder(order2);

        System.out.println(orderList);

        
    }

}
