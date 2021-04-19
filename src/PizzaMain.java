import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.Scanner;

public class PizzaMain {
    private static Boolean shouldContinueRunning = true;
    private static final Scanner sc = new Scanner(System.in);

    private static final String UIOptions =
                """
                1: Opret ny bestilling
                2: Markér en bestilling som betalt
                3: Fjern en bestilling
                4: Udskriv dagens bestillinger
                9: Gem og luk program
                """;

    private static final OrderList orderList = new OrderList();
    private static Menu menu;

     static {
        try {
            menu = new Menu("resources/menu.tsv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        while (shouldContinueRunning) {
            UI.clear();
            UI.drawHeader();
            UI.drawOrderlistAndMenu(orderList.getOrders(), menu.getAllPizzas());
            System.out.println(UIOptions);

            String input = sc.nextLine();

            if (input.equalsIgnoreCase( "1")) {
                Order order = new Order();

                makeNewOrder(order);
                setPickUpTime(order);

                orderList.addOrder(order);
            } else if ( input.equalsIgnoreCase("2")) {
                    markOrderAsPaid();
            } else if ( input.equalsIgnoreCase("3")) {
                    removeOrder();
            } else if ( input.equalsIgnoreCase("4")) {
                //TODO: Print daily report
            } else if ( input.equalsIgnoreCase("9")) {
                System.out.println("Lukker...");
                // TODO: PRINT DAILY REPORT
                shouldContinueRunning = false;
            }
        }
    }

    public static void makeNewOrder(Order order) {

         while (true) {
            System.out.print("Pizza nr: ");
            String userInputPizzaId = sc.nextLine();
            if (UserInput.isQuit(userInputPizzaId)) {
                break;
            }

            System.out.print("Antal: ");
            String userInputPizzaAmount = sc.nextLine();
            if (UserInput.isQuit(userInputPizzaAmount)) {
                break;
            }

            System.out.println("Kommentar: ");
            String userInputComment = sc.nextLine();
            if (UserInput.isQuit(userInputComment)) {
                break;
            }

            Pizza pizza = menu.getPizza(Integer.parseInt(userInputPizzaId));
            Integer quantity = Integer.parseInt(userInputPizzaAmount);
            String comment = userInputComment;

            order.addLineItem( new OrderLineItem(pizza, quantity, comment) );
        }
    }

    public static void setPickUpTime(Order order) {
         System.out.print("Tidspunkt for afhenting: ");
         LocalTime pickUpTime = UserInput.parseLocalTime( sc.nextLine() );
         order.setPickUpTime( pickUpTime );
    }

    public static void markOrderAsPaid() {
         System.out.print("Indtast bestillings nr: ");
         String userInputOrderID = sc.nextLine();
         orderList.changeOrderStatus(userInputOrderID, true);
    }

    public static void removeOrder() {
         String userInputIdToRemove = sc.nextLine();
         orderList.removeOrder(userInputIdToRemove);
    }
}
