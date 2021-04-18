import java.io.File;
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

    public static void main(String[] args) throws FileNotFoundException{
        System.out.println(new File(".").getAbsolutePath());

        ArrayList<OrderLineItem> lineItemList = new ArrayList<>(); // used by all objects, just clear every time

        Order fakeOrder1 = makeFakeOrder(210.00, 1930);
        lineItemList.add(makeFakeLine(makeFakePizza(1), 5, "first comment"));
        lineItemList.add(makeFakeLine(makeFakePizza(2), 1, "second comment"));
        fakeOrder1.setListOfOrderLineItems(lineItemList);

       lineItemList = new ArrayList<>();
       Order fakeOrder2 = makeFakeOrder(300.00, 2245);
       lineItemList.add(makeFakeLine(makeFakePizza(3), 2, "third comment"));
       lineItemList.add(makeFakeLine(makeFakePizza(5),1, "fourth comment"));
       lineItemList.add(makeFakeLine(makeFakePizza(9),10, "fifth comment"));
       fakeOrder2.setListOfOrderLineItems(lineItemList);

        lineItemList = new ArrayList<>();
        Order fakeOrder3 = makeFakeOrder(780.00, 0400);
        lineItemList.add(makeFakeLine(makeFakePizza(11), 3, "sixth comment"));
        fakeOrder3.setListOfOrderLineItems(lineItemList);


        OrderList orders = new OrderList();
        orders.addOrder(fakeOrder1);
        orders.addOrder(fakeOrder2);
        orders.addOrder(fakeOrder3);

        orders.printOrderList();

        //MARK ADDED THIS SHIT vvv
        Menu pizzas = new Menu();
        UI.drawHeader();
        UI.drawOrderlist(orders);
        UI.drawMenu(pizzas);


        orders.removeOrder(fakeOrder3);

        orders.changeOrderStatus(fakeOrder2,true);
    }

    private static Pizza makeFakePizza(Integer id) {
        return menu.get(id);
    }

    private static OrderLineItem makeFakeLine(Pizza pizza, Integer amount, String comment) {
        return new OrderLineItem(pizza, amount, comment);
    }

   private static Order makeFakeOrder(double totalPrice, int pickUpTime) {
        Order order = new Order();
        order.setTotalPrice(totalPrice);
        order.setPickUpTime(pickUpTime);

        return order;
    }

}


