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
        ArrayList<OrderLineItem> lineItemList = new ArrayList<>(); // used by all objects, just clear every time

        Order fakeOrder1 = makeFakeOrder(210.00, 1930, "Salami");
        lineItemList.add(makeFakeLine(makeFakePizza(1), 5, "first comment"));
        lineItemList.add(makeFakeLine(makeFakePizza(2), 1, "second comment"));
        fakeOrder1.setListOfOrderLineItems(lineItemList);

       lineItemList = new ArrayList<>();
       Order fakeOrder2 = makeFakeOrder(300.00, 2245, "cheese");
       lineItemList.add(makeFakeLine(makeFakePizza(3), 2, "third comment"));
       lineItemList.add(makeFakeLine(makeFakePizza(5),1, "fourth comment"));
       lineItemList.add(makeFakeLine(makeFakePizza(9),10, "fifth comment"));
       fakeOrder2.setListOfOrderLineItems(lineItemList);

        Order fakeOrder3 = makeFakeOrder(780.00, 0400, "tomato");
        //fakeOrder3.addPizzaLineItemToList( 2, "comment3");

        OrderList orders = new OrderList();
        orders.addOrder(fakeOrder1);
        orders.addOrder(fakeOrder2);
 //       orders.addOrder(fakeOrder3);

        System.out.println(orders.getOrderList());;
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
        ArrayList<String> extras = new ArrayList<>();
        extras.add(ingredient);
        order.setListOfExtras(extras);

        return order;
    }

}


