import java.io.Serializable;

public class OrderLineItem implements Serializable {
    private final Pizza pizza;
    private final Integer amount;
    private final String comment;

    public OrderLineItem(Pizza pizza, Integer amount, String comment) {
        this.pizza = pizza;
        this.amount = amount;
        this.comment = comment;
    }

    @Override public String toString() {
        return String.format("x%d %s, ", this.amount, this.pizza.getName() + " (" + comment + ")");
    }

    public Double getPrice() {
        return this.amount * ( this.pizza.getPrice() + priceOfExtras() );
    }

    private Double priceOfExtras() {
        Double commentPrice = 0.0;

        for (String str : this.comment.split(",") ) {
            if ( str.toLowerCase().contains("+") ||
                    str.toLowerCase().contains("plus") ||
                    str.toLowerCase().contains("ekstra") ||
                    str.toLowerCase().contains("extra") ||
                    str.toLowerCase().contains("add") ||
                    str.toLowerCase().contains("more") ||
                    str.toLowerCase().contains("xtra") ||
                    str.toLowerCase().contains("mere") ||
                    str.toLowerCase().contains("tilføj")
            ) {
                commentPrice += 10;
            }
        }
        return commentPrice;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getComment() {
        return comment;
    }
}
