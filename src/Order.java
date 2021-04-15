import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Order {

    // Features off src.Order class
    // Add new pizza(s)
    // Add comment to order
    // Pickup time of the order
    // Total price

    private String comment;
    private double totalPrice;
    private int pickUpTime;
    private final Scanner sc = new Scanner(System.in);

    private final ArrayList<OrderLineItem> listOfOrderLineItems = new ArrayList<>();
    private final ArrayList<String> listOfExtras = new ArrayList<>();
    //private final ArrayList<Pizza> list = new ArrayList<>();
    //private final ArrayList<Integer> quantity = new ArrayList<>() ;

    // Constructor for Order object
    public Order() {

    }

    public void addComment() {
        boolean exit = false;
        System.out.println("Add comment or \"Quit\" to exit");
        // Keep adding ingredients or getting extra until Quit or quit is typed.
        while (!exit) {
            comment = sc.nextLine();
           // While () this --> if (!comment.startsWith("add") || comment.startsWith("Add") || comment.startsWith("extra") || comment.startsWith("Extra")) {
                // System.out.println("Wrong input. Please use \"add\" or \"extra\"");
                if (comment.startsWith("add") || comment.startsWith("Add") || comment.startsWith("extra") || comment.startsWith("Extra")) {
                listOfExtras.add(comment);
                totalPrice += 10;
                // if customer wants to remove any ingredient, add the comment to the list, but the price stays the same
                // if price must change: totalPrice -= x
            } else if (comment.startsWith("no") || comment.startsWith("No")) {
                listOfExtras.add(comment);
            } else if (comment.equals("Quit") || comment.equals("quit")) {
                // If the ArrayList is empty, don't print an empty list.
                exit = true;
                if (listOfExtras.isEmpty())
                System.out.println("Quit comment");
                // If the list contains something, print it.
                else
                System.out.println(listOfExtras + " ja tjak min ven");
            }
        }
    }
    // Getter for the comment list of extras
    public ArrayList<String> getCommentList(){
        return listOfExtras;
    }

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
        for (int i = 0; i< listOfOrderLineItems.size();i++) {
            totalPrice += listOfOrderLineItems.get(i).getPrice()*listOfOrderLineItems.get(i).getAmount();
        }

        return totalPrice;
    }

    public void addPizza() throws FileNotFoundException {
        Menu menu = new Menu();
        Map<Integer, Pizza> pizzaMenu = menu.getPizzaMenu();

        boolean exit = false;

        while (!exit) {
            String input1;
            String input2;

            System.out.println("What pizza");
            input1 = sc.nextLine();

            System.out.println("How many pizzas");
            input2 = sc.nextLine();

            if (input1.equals("Quit") || input2.equals("Quit")) {
                    exit = true;
            } else {
                Pizza pizza = pizzaMenu.get(Integer.parseInt(input1));
                Integer quantity = Integer.parseInt(input2);

                OrderLineItem orderLine = new OrderLineItem(pizza,quantity,"");

                listOfOrderLineItems.add(orderLine);

                //System.out.println(tmp.getName() + " ok");
                //System.out.println("ok " + input1 + " nr. " + input2+"'s");

                //orderLine.add(tmp);
                //quantity.add(Integer.valueOf(input2));
                }
            }
        }


    // Getter for the list of pizzas
    //public ArrayList<Pizza> getList(){
    //    return listOfOrderLineItems;
    //}


    @Override public String toString() {
        String stringBOI = "";
        for (int i=0;i<listOfOrderLineItems.size();i++) {
            String currentPizza = listOfOrderLineItems.get(i).getPizza().getName();
            int currentQuantity = listOfOrderLineItems.get(i).getAmount();
            stringBOI += "x" +currentQuantity + " " + currentPizza + ", ";
        }
            stringBOI += "\n" + listOfExtras;
            stringBOI += "\n" + getTotalPrice() + " DKK";
        return stringBOI;
    }
}