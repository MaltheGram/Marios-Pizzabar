import java.util.Random;

public class OrderID {
    private final Long randomID;

    OrderID() {
        this.randomID = generateRandomID();
    }

    private Long generateRandomID() {
        return Math.abs(new Random().nextLong());
    }

    public Long getNewOrderID() {
        return randomID;
    }
}
