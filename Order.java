import java.util.ArrayList;

public class Order {
    String comment;
    int pickUpTime;
    int quantity;
    double totalPrice;

    ArrayList<Pizza> list = new ArrayList<>();

    // Constructor for Order object
    public Order() {

    }


    // Add new pizza(s)
    // Add comment to order
    // Pickup time of the order
    // Total price

    public String addComment(String comment) {
        return comment;
    }

    public int pickUpTime(int pickUpTime) {
        return pickUpTime;
    }

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
}



