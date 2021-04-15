
public class Invoice {

    public static void printInvoice(Order order){
        System.out.println("Min ven, tak for handlen");
        System.out.println("Din ordre er: " +  order.toString());
        System.out.println("Samlet pris: " + order.getTotalPrice());
        System.out.println("Din ordre er klar til afhentning kl: " + order.getPickUpTime());;
    }
}
