import java.util.Random;

public class OrderID {
    private final Long randomID;
    private final String hexStringID;

    OrderID() {
        this.randomID = generateRandomID();
        this.hexStringID = Long.toHexString(randomID);
    }

    private Long generateRandomID() {
        return Math.abs(new Random().nextLong());
    }

    public String getHexStringID() {
        return hexStringID;
    }

}
