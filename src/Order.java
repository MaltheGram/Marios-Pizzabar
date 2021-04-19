import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.Serializable;

public class Order implements Serializable {

    // Temporary setters for testing (FakeMainForTest)

    public void setId(Long id) {
        this.id = id;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setPickUpTime(int pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public void setListOfOrderLineItems(ArrayList<OrderLineItem> listOfOrderLineItems) {
        this.listOfOrderLineItems = listOfOrderLineItems;
    }
    //don't remove this setter. OrderList uses it.
    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    private double totalPrice;
    private Long id = new OrderID().getNewOrderID();
    public String getOrderTime() {
        return orderTime;
    }
    private String orderTime = getCurrentSimpleTime();
    private Integer pickUpTime;
    private final Scanner sc = new Scanner(System.in);
    private ArrayList<OrderLineItem> listOfOrderLineItems = new ArrayList<>();
    private Boolean isPaid = false;

    public void pickUpTime() {
        System.out.println("Please add pickup time");
        pickUpTime = sc.nextInt();
        // Make if pickUpTime > closing time = error. Do we know closing time?
        System.out.println("Pickup at: " + pickUpTime);
    }

    private String getCurrentSimpleTime() {
            String timeStamp = fetchCompleteSystemTime();
            return timeStamp.substring(9, 13); // is this the right index?
    }

    private String fetchCompleteSystemTime() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    }

    public Long getId() {
        return id;
    }

    public int getPickUpTime(){
        return pickUpTime;
    }

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

    public ArrayList<OrderLineItem> getListOfOrderLineItems() {
        return listOfOrderLineItems;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void addPizza() throws FileNotFoundException {
        Menu menu = new Menu();
        Map<Integer, Pizza> pizzaMenu = menu.getPizzaMenu();

        while (true) {
            String input1;
            String input2;
            String input3;

            System.out.println("What pizza - Type \"quit\"");
            input1 = sc.nextLine();
            if (isQuit(input1)) {
                break;
            }
            System.out.println("How many pizzas - Type \"quit\"");
            input2 = sc.nextLine();
            if (isQuit(input2)) {
                break;
            }

            System.out.println("Comments - Press enter to skip");
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

    private boolean isQuit(String input) {
        return input.equalsIgnoreCase("quit");
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