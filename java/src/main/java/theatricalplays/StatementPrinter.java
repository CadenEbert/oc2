package theatricalplays;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    public String print(Invoice invoice, Map<String, Play> plays) {
        var totalAmount = 0;
        var volumeCredits = 0;
        var result = new StringBuilder();
        result.append(String.format("Statement for %s%n", invoice.customer));

        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
        ChargeCalculator cc = new ChargeCalculator();
        VolumeCreditCalculator vcc = new VolumeCreditCalculator();

        for (var perf : invoice.performances) {
            var play = plays.get(perf.playID);
            var thisAmount = 0;

            // charge for each performance
            thisAmount = cc.calculate(perf, play);            

            // add volume credits
            volumeCredits += vcc.calculate(perf, play);

            // print line for this order
            result.append(String.format("  %s: %s (%s seats)%n", play.name, frmt.format(thisAmount / 100), perf.audience));
            totalAmount += thisAmount;
        }

        result.append(String.format("Amount owed is %s%n", frmt.format(totalAmount / 100)));
        result.append(String.format("You earned %s credits%n", volumeCredits));
        return result.toString();
    }

}
