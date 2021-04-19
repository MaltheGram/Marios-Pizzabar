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
        //UI.drawMenu();
        System.out.println(menu);

        System.out.println(menu.getPizza(6));

        System.out.printf("%-50.50s %-50.50s%n", "Column 1", "Column 2");

        // Add loop to keep adding pizzas
        var programIsRunning = true;
        OrderList orderList = new OrderList();

        while (programIsRunning) {
            Order order = new Order();

            order.addPizza();
            order.pickUpTime();
            Invoice.printInvoice(order);

            orderList.addOrder(order);

            System.out.println(orderList);

            programIsRunning = false;
        }
    }
}
