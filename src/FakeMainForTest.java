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

    public FakeMainForTest()  {
    }

    public static void main(String[] args) {

        Order fakeOrder1 = makeFakeOrder(210.00, 1930, "Salami");
        ArrayList<OrderLineItem> lineItemList = new ArrayList<>();
        lineItemList.add(makeFakeLine(makeFakePizza(1), 5, "first comment"));
        // can add more lines
        fakeOrder1.setListOfOrderLineItems(lineItemList);

/*       Order fakeOrder2 = makeFakeOrder(300.00, 2245, "cheese");
        fakeOrder2.addPizzaLineItemToList(1, "comment2");

        Order fakeOrder3 = makeFakeOrder(780.00, 0400, "tomato");
        fakeOrder3.addPizzaLineItemToList( 2, "comment3");*/

        OrderList orders = new OrderList();
        orders.addOrder(fakeOrder1);
 //       orders.addOrder(fakeOrder2);
 //       orders.addOrder(fakeOrder3);
    }

    private static Pizza makeFakePizza(Integer id) {
        return menu.get(id);
    }

    private static OrderLineItem makeFakeLine(Pizza pizza, Integer amount, String comment) {
        return new OrderLineItem(pizza, amount, comment);
    }

   private static Order makeFakeOrder(double totalPrice, int pickUpTime, String ingredient) {
        Order order = new Order();
        order.setTotalPrice(totalPrice);
        order.setPickUpTime(pickUpTime);
        order.setComment("comment!");
        ArrayList<String> extras = new ArrayList<>();
        extras.add(ingredient);
        order.setListOfExtras(extras);

        return order;
    }

}


