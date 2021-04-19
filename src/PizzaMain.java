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
                Press 9: to quit""";

        System.out.println(printOption);
        while(programIsRunning) {
        var input = sc.nextLine();
            if (input.toLowerCase().startsWith("1")) {
                Order order = new Order();

                order.addPizza();
                order.pickUpTime();
                Invoice.printInvoice(order);

                orderList.addOrder(order);
                UI.drawOrderlistAndMenu(orderList,menu);
                System.out.println(printOption);

            } if (input.equalsIgnoreCase("2")){
                orderList.printOrderList();
                Order order = new Order();
                var removeInput = sc.nextLine();
                orderList.removeOrder(removeInput);
                orderList.printOrderList();

            } if (input.toLowerCase().startsWith("9")) {
                System.out.println("Terminating");
                programIsRunning = false;
            }

        }
            UI.drawOrderlistAndMenu(orderList, menu);

    }
















}
