

public class Invoice {
// would make more sense to call this ConfirmationMessage
    public static void printInvoice(Order order){
        System.out.println("Min ven, tak for handlen");
        System.out.println("Din ordre er: " +  order.toString());
        System.out.println("Din ordre er klar til afhentning kl: " + order.getPickUpTime());
    }
}
