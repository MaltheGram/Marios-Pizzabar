import java.io.FileNotFoundException;
import java.util.*;
import java.io.Serializable;

public class Order implements Serializable {

    // Setter
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    // Setter
    public void setPickUpTime(int pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public void setListOfOrderLineItems(ArrayList<OrderLineItem> listOfOrderLineItems) {
        this.listOfOrderLineItems = listOfOrderLineItems;
    }

    private double totalPrice;
    private int pickUpTime;
    private final Scanner sc = new Scanner(System.in);
    private ArrayList<OrderLineItem> listOfOrderLineItems = new ArrayList<>();

    public void pickUpTime() {
        System.out.println("Please add pickup time");
        pickUpTime = sc.nextInt();
        // Make if pickUpTime > closing time = error
        System.out.println("Pickup at: " + pickUpTime);
    }

    // Getter for pick up time
    public int getPickUpTime(){
        return pickUpTime;
    }

    // Getter for total price
    public double getTotalPrice() {

        for (OrderLineItem lineItem : listOfOrderLineItems) {
            totalPrice += lineItem.getPrice() + getPriceOfExtras(lineItem);
        }

        return totalPrice;
    }

    private Double getPriceOfExtras(OrderLineItem item) {
        var amount = 0;
        String[] splitted = item.getComment().split(",");
        for (var comment : splitted) {
            if(comment.contains("+") || comment.toLowerCase(Locale.ROOT).contains("add")) {
                amount += 10;
            }
        }
        return Double.valueOf(amount);
    }

    // Getter for getListOfOrderLineItems
    public ArrayList<OrderLineItem> getListOfOrderLineItems() {
        return listOfOrderLineItems;
    }

    public void addPizza() throws FileNotFoundException {
        Menu menu = new Menu();
        Map<Integer, Pizza> pizzaMenu = menu.getPizzaMenu();

        while (true) {
            String input1;
            String input2;
            String input3;

            System.out.println("What pizza");
            input1 = sc.nextLine();
            if (isQuit(input1)) {
                break;
            }
            System.out.println("How many pizzas");
            input2 = sc.nextLine();
            if (isQuit(input2)) {
                break;
            }

            System.out.println("Comments");
            input3 = sc.nextLine();
            if (isQuit(input3)) {
                break;
            }

            Pizza pizza = pizzaMenu.get(Integer.parseInt(input1));
            Integer quantity = Integer.parseInt(input2);
            OrderLineItem orderLine = new OrderLineItem(pizza,quantity, input3);

            listOfOrderLineItems.add(orderLine);
        }
    }

    private boolean isQuit(String input1) {
        return input1.equalsIgnoreCase("quit") || input1.isBlank();
    }

    @Override public String toString() {
        String stringBOI = "";
        for (OrderLineItem i : listOfOrderLineItems) {
            stringBOI += i.toString();
        }
            stringBOI += getTotalPrice() + " DKK";
        return stringBOI;
    }
}