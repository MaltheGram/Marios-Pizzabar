import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
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
        Scanner sc = new Scanner(System.in);

        System.out.println("""
                Press 1: to add pizza
                Press 2: to remove a pizza from the order list
                Press 9: to quit""");

        while(programIsRunning) {
        var input = sc.nextLine();
            if (input.toLowerCase().startsWith("1")) {
                Order order = new Order();

                order.addPizza();
                order.pickUpTime();
                Invoice.printInvoice(order);

                orderList.addOrder(order);

            } if (input.equalsIgnoreCase("2")){
                orderList.printOrderList();
                Order order = new Order();
                var removeInput = sc.nextInt();
                orderList.removeOrder(order);
                orderList.printOrderList();

            } if (input.toLowerCase().startsWith("9")) {
                System.out.println("Terminating");
                programIsRunning = false;
            }

        }

    }
















}
