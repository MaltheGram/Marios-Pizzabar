import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FakeMainForTest {
    static Menu m;

    static {
        try {
            m = new Menu();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static Map<Integer, Pizza> menu = m.getPizzaMenu();

    public FakeMainForTest() throws FileNotFoundException {
    }

    public static void main(String[] args) throws FileNotFoundException {

        Order fakeOrder1 = makeFakeOrder(210.00, 1930, "Salami");
        addPizzaToOrder(fakeOrder1);

        Order fakeOrder2 = makeFakeOrder(300.00, 2245, "cheese");
        addPizzaToOrder(fakeOrder2);

        Order fakeOrder3 = makeFakeOrder(780.00, 0400, "tomato");
        addPizzaToOrder(fakeOrder3);

        OrderList orders = new OrderList();
        orders.addOrder(fakeOrder1);
        orders.addOrder(fakeOrder2);
        orders.addOrder(fakeOrder3);
    }

    private static void addPizzaToOrder(Order fakeOrder) {
        List<Pizza> pizzas = fakeOrder.getList();
        pizzas.add(menu.get(1));
        pizzas.add(menu.get(5));
        pizzas.add(menu.get(14));
    }

    private static Order makeFakeOrder(double totalPrice, int pickUpTime, String ingredient) {
        Order order = new Order();
        order.totalPrice = totalPrice;
        order.pickUpTime = pickUpTime;
        ArrayList<String> extras = new ArrayList<>();
        extras.add(ingredient);
        order.listOfExtras = extras;

        return order;
    }
}
