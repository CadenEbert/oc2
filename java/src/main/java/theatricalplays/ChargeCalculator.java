package theatricalplays;


public class ChargeCalculator {

    public int calculate(Performance performance, Play play) {
        int thisAmount = 0;

        switch (play.type) {
            case "tragedy":
                thisAmount = 40000;
                if (performance.audience > 30) {
                    thisAmount += 1000 * (performance.audience - 30);
                }
                break;
            case "comedy":
                thisAmount = 30000;
                if (performance.audience > 20) {
                    thisAmount += 10000 + 500 * (performance.audience - 20);
                }
                thisAmount += 300 * performance.audience;
                break;
            case "historical":
                thisAmount = 50000;
                if (performance.audience > 20) {
                    thisAmount += 1500 * (performance.audience - 20);
                }
                break;
            default:
                throw new Error("unknown type: %s".formatted(play.type));
        }

        return thisAmount;
    }
    
}
