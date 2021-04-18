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
        return this.amount * this.pizza.getPrice();
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
