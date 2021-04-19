import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DailyReport {
    private List<String[]> paidOrders = new ArrayList<>();

    public void printDailyReport(File dailyLog, String date) {

        try {
            Scanner sc = new Scanner(dailyLog);
            while(sc.hasNextLine()) {
                var line = sc.nextLine();
                    if(line.contains("true")) {
                        paidOrders.add(line.split(";"));
                    }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(new StringFormatHandler().formatDailyReport(paidOrders, date, calculateDailyRevenue()));
    }

    private Double calculateDailyRevenue() {
        var total = .0;
        for(var order : paidOrders) {
            var v= order[2].substring("ORDER_TOTAL=".length());
            total += Double.parseDouble(v);
        }
        return total;
    }
}
