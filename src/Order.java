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

    String comment;
    double totalPrice;
    int pickUpTime;
    Scanner sc = new Scanner(System.in);
    ArrayList<String> listOfExtras = new ArrayList<>();
    ArrayList<Pizza> list = new ArrayList<>();

    // Constructor for Order object
    public Order() {

    }

    public void addComment() {
        boolean exit = false;
        System.out.println("Add comment or \"Quit\" to exit");
        // Keep adding ingredients or getting extra until Quit or quit is typed.
        while (!exit) {
            comment = sc.nextLine();
           // if (!comment.startsWith("add") || comment.startsWith("Add") || comment.startsWith("extra") || comment.startsWith("Extra")) {
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
        System.out.println("Pickup at: " + pickUpTime);
    }

    // Getter for pick up time
    public int getPickUpTime(){
        return pickUpTime;
    }

    // Getter for total price
    public double getTotalPrice() {
        for (Pizza pizza : list) {
            totalPrice += pizza.getPrice();
        }
        return totalPrice;
    }

    public void addPizza() throws FileNotFoundException {
        Menu menu = new Menu();
        Map<Integer, Pizza> pizzaMenu = menu.getPizzaMenu();

        boolean exit = false;

        while(!exit) {
            System.out.println("What pizza");
            int pizzaId = sc.nextInt();
            Pizza tmp = pizzaMenu.get(pizzaId);
            System.out.println(tmp.getName() + " ok");

            System.out.println("How many pizzas");
            int amount = sc.nextInt();

            System.out.println(amount + " ok");

            list.add(tmp);
            exit = true;
        }
    }

    // Getter for the list of pizzas
    public ArrayList<Pizza> getList(){
        return list;
    }


    @Override public String toString() {
        return String.format("Order {pizzas=%s}", this.list);
    }


}