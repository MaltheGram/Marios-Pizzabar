import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.Scanner;

public class PizzaMain {
    private static Boolean shouldContinueRunning = true;
    private static final Scanner sc = new Scanner(System.in);
    private static final DailyReport report = new DailyReport();

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
            UI.drawOrderlistAndMenu(orderList.getAllOrders(), menu.getAllPizzas());
            System.out.println(UIOptions);

            String input = sc.nextLine();

            if (input.equalsIgnoreCase( "1")) {
                Order order = new Order();

                makeNewOrder(order);

                if (order.getTotalPrice() > 0) {
                    setPickUpTime(order);
                    orderList.addOrder(order);
                }
            } else if ( input.equalsIgnoreCase("2")) {
                markOrderAsPaid();
            } else if ( input.equalsIgnoreCase("3")) {
                removeOrder();
            } else if ( input.equalsIgnoreCase("4")) {
                printDailyReport();
            } else if ( input.equalsIgnoreCase("9")) {
                System.out.println("Lukker...");
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

            System.out.print("Kommentar: ");
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

    public static void printDailyReport() {
         UI.clear();
         orderList.showDailyReport();
         System.out.println("Tast enter for at lukke");
         sc.nextLine();
    }

    public static void setPickUpTime(Order order) {
         System.out.print("Tidspunkt for afhenting: ");
         LocalTime pickUpTime = UserInput.parseLocalTime( sc.nextLine() );
         order.setPickUpTime( pickUpTime );
    }

    public static void markOrderAsPaid() {
         System.out.print("Indtast bestillings nr: ");
         String userInputOrderID = sc.nextLine();
         try {
             orderList.changeOrderStatus(userInputOrderID, true);
         } catch(Exception e) {
            System.out.println("Bestilling ikke fundet");
         }

    }

    public static void removeOrder() {
         System.out.print("Indtast bestillings nr: ");
         String userInputOrderId = sc.nextLine();
         try {
             orderList.removeOrder(userInputOrderId);
         } catch(Exception e) {
             System.out.println("Bestilling ikke fundet");
         }
    }
}
