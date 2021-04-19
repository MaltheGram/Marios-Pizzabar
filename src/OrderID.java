import java.util.Random;

public class OrderID {
    private final Integer randomID;
    private final String hexStringID;

    OrderID() {
        this.randomID = generateRandomID();
        this.hexStringID = Integer.toHexString(randomID);
    }

    private Integer generateRandomID() {
        return Math.abs(new Random().nextInt());
    }

    public String getHexStringID() {
        return hexStringID;
    }

}
