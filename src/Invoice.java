
public class Invoice {

    public static void printInvoice(){
        System.out.println("Min ven, tak for handlen");
        //System.out.println("Din ordre er: " + Order.list);
        System.out.println("Samlet pris: " + Order.totalPrice);
        System.out.println("Din ordre er klar til afhentning kl: " + Order.pickUpTime);
    }
}
