import java.io.FileNotFoundException;

public class PizzaMain {
    public static void main(String[] args) {
        //do stuff

        String test = "test";
        Menu menu = null;
        try {
        	menu = new Menu();
        } catch (FileNotFoundException e) {
        	System.out.println("woops");
        }
        System.out.println(menu);
        
        System.out.println(menu.getPizza(6));

        System.out.printf("%-50.50s %-50.50s%n","doot","shit");
    }
}
