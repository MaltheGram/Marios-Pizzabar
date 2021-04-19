import java.util.Collection;

public class StringFormatHandler {

    public String formatLineForOrderList(Order o) {
        //%x =>hexadecimal
        var template = "ORDERNR=%s;PAID=%s;ORDER_TOTAL=%.2f;ORDER_TIME=%s;ORDER_ITEMS=%s%n";
        var filledInTemplate = String.format(template, o.getId(), o.getHasBeenPaidFor().toString(), o.getPrice(), o.getOrderTime(), formatOrderItems(o.getLineItems()));
        return filledInTemplate;
    }

    private String formatOrderItems(Collection<OrderLineItem> listOfOrderLineItems) {
        var formatted = "";
        var template = "PIZZA=%s;COUNT=%d;PRICE=%.2f";
        for(var lineItem : listOfOrderLineItems) {
            formatted += "[" + String.format(template, lineItem.getPizza().getName(), lineItem.getAmount(), lineItem.getPrice()) + "]";
        }
        return "[" + formatted + "]";
    }

    public String humanReadableFileText(OrderList list, String date) {
        var totalOrder = "DAILY REPORT FOR " + date + ".";
        for(Order o : list.getOrderList().values()) {
            var template = "Order Nr:%s;Was paid:%s;Order total:%.2f;Order time:%s;Pizzas:%s%n";
            var filledInTemplate = String.format(template, o.getId(), o.getHasBeenPaidFor().toString(), o.getPrice(), o.getOrderTime(), formatOrderItems(o.getLineItems()));
            totalOrder += filledInTemplate;
        }

        return totalOrder;

    }
}
