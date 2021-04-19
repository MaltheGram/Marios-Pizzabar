import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.io.Serializable;

public class Order implements Serializable, Comparable {
    private String id = new OrderID().getHexStringID();
    private String orderTime = getCurrentSimpleTime();
    private final Collection<OrderLineItem> lineItems = new ArrayList<>();
    private LocalTime pickUpTime = null;
    private Boolean hasBeenPaidFor = false;

    public String getOrderTime() {
        return orderTime;
    }

    private String getCurrentSimpleTime() {
            String timeStamp = fetchCompleteSystemTime();
            return timeStamp.substring(9, 13); // is this the right index?
    }


    private String fetchCompleteSystemTime() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    }

    public String getId() {
        return id;
    }

    public Double getTotalPrice() {
        return this.lineItems.stream().map(OrderLineItem::getPrice).reduce(0.0, Double::sum);
    }

    public Collection<OrderLineItem> getLineItems() {
        return lineItems;
    }
    public void addLineItem(OrderLineItem lineItem) {
        this.lineItems.add(lineItem);
    }

    public Boolean getHasBeenPaidFor() {
        return this.hasBeenPaidFor;
    }
    public void setHasBeenPaidFor(Boolean hasBeenPaidFor) {
        this.hasBeenPaidFor = hasBeenPaidFor;
    }

    public LocalTime getPickUpTime(){
        return pickUpTime;
    }
    public void setPickUpTime(LocalTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    @Override public String toString() {
        StringBuilder orderString = new StringBuilder();

        for (OrderLineItem lineItem: lineItems) {
            orderString.append(lineItem).append(String.format("%n"));
        }

        orderString.append( getTotalPrice()).append(" DKK");
        return orderString.toString();
    }

    @Override
    public int compareTo(Object o) {
        return this.getPickUpTime().compareTo(((Order) o).getPickUpTime() );
    }
}