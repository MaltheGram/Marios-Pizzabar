import java.util.ArrayList;
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
            if (comment.contains("add") || comment.contains("Add") || comment.contains("extra") || comment.contains("Extra")) {
                listOfExtras.add(comment);
                totalPrice += 10;
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

    public void addPizza(Pizza pizza, int quantity) {
        for (int i = 0; i < quantity; i++) {
            list.add(pizza);
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