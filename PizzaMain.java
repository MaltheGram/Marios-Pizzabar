import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PizzaMain {
    public static void main(String[] args) {
        //do stuff

        Menu menu = null;
        try {
            menu = new Menu();
        } catch (FileNotFoundException e) {
            System.out.println("woops");
        }
        System.out.println(menu);

        System.out.println(menu.getPizza(6));

        System.out.printf("%-50.50s %-50.50s%n", "Column 1", "Column 2");


        // Add loop to keep adding pizzas

        Scanner sc = new Scanner(System.in);
        Order order1 = new Order();

        System.out.println("Please add comment");
        String comment = sc.nextLine();
        String addComment = order1.addComment(comment);
        System.out.println(addComment + ", confirmed");

        System.out.println("Please add pickup time");
        int pickUpTime = sc.nextInt();
        int addPickUpTime = order1.pickUpTime((pickUpTime));
        System.out.println(addPickUpTime + ", confirmed");

        order1.addPizza(menu.getPizza(5), 1);
        order1.addPizza(menu.getPizza(14),3);

        System.out.println(order1.list);
        System.out.println("Total price: " + order1.getTotalPrice() + " DKK");


    }

}
