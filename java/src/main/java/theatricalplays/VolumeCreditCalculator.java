package theatricalplays;


public class VolumeCreditCalculator {

    public int calculate(Performance performance, Play play) {
        int volumeCredits = 0;
        if ("historical".equals(play.type)) {
            return performance.audience / 5;
        }

        // add volume credits
        volumeCredits += Math.max(performance.audience - 30, 0);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(play.type)) volumeCredits += Math.floor(performance.audience / 5);

        return volumeCredits;
    }
    
}
