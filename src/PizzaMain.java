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
        UI.drawMenu(menu);
       // System.out.println(menu);

       // System.out.println(menu.getPizza(6));



        // Add loop to keep adding pizzas

        var programIsRunning = true;
        OrderList orderList = new OrderList();
        Scanner sc = new Scanner(System.in);
        String printOption = """
                Press 1: to add pizza
                Press 2: to remove a pizza from the order list
                Press 3: Mark order as paid.
                Press 4: Print daily orders
                Press 9: to quit""";

        System.out.println(printOption);
        while(programIsRunning) {
        var input = sc.nextLine();
            if (input.equals("1")) {
                Order order = new Order();

                order.addPizza();
                order.pickUpTime();
                Invoice.printInvoice(order);

                orderList.addOrder(order);
                UI.drawOrderlistAndMenu(orderList, menu);
                System.out.println(printOption);

            } if (input.equals("3")) {
                System.out.println("Inset order number to mark as paid:");
                input = sc.nextLine();
                orderList.changeOrderStatus(input, true);

            } if (input.equals("4")){


            } if (input.equals("2")){
                var idToRemove = sc.nextLine();
                orderList.removeOrder(idToRemove);

            } if (input.equals("9")) {
                System.out.println("Terminating");
                // PRINT DAILY REPORT
                programIsRunning = false;
            }

        }
            UI.drawOrderlistAndMenu(orderList, menu);

    }
















}
