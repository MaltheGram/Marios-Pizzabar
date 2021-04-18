import java.util.ArrayList;

public class StringFormatHandler {

    public String formatLine(Order o) {
        //%x =>hexadecimal
        var template = "ORDERNR=%x;PAID=%s;ORDER_TOTAL=%.2f;ORDER_TIME=%s;ORDER_ITEMS=%s\n";
        var filledInTemplate = String.format(template, o.getId(), o.getIsPaid().toString(), o.getTotalPrice(), o.getOrderTime(), formatOrderItems(o.getListOfOrderLineItems()));
        return filledInTemplate;
    }

    private String formatOrderItems(ArrayList<OrderLineItem> listOfOrderLineItems) {
        var formatted = "";
        var template = "PIZZA=%s;COUNT=%d;PRICE=%.2f";
        for(var lineItem : listOfOrderLineItems) {
            formatted += "[" + String.format(template, lineItem.getPizza().getName(), lineItem.getAmount(), lineItem.getPrice()) + "]";
        }
        return "[" + formatted + "]";
    }
}
