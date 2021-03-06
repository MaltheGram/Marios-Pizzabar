import java.util.Collection;
import java.util.List;

public class StringFormatHandler {

    public String formatLineForOrderList(Order o) {
        //%x =>hexadecimal
        var template = "ORDERNR=%s;PAID=%s;ORDER_TOTAL=%.2f;ORDER_TIME=%s;ORDER_ITEMS=%s\n";
        var filledInTemplate = String.format(template, o.getId(), o.getHasBeenPaidFor().toString(), o.getTotalPrice(), o.getOrderTime(), formatOrderItems(o.getLineItems()));
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

    public String formatDailyReport(List<String[]> paidOrders, String date, Double revenue) {
        // modify date to have dots in between
        //var formattedDate =
        var report = "";
        report += "DAGENS ORDRERAPPORT "+ date +"\n********************\n";
        var template = "ordreNr: %s, i alt: %s \n";
        for(var order : paidOrders) {
            // ordreNr: i alt:
            report += String.format(template, order[0].substring("ORDERNR=".length()), order[2].substring("ORDER_TOTAL=".length()));
        }

        return report;

    }
}
