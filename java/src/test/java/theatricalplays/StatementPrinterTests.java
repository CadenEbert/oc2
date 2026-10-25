package theatricalplays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.approvaltests.Approvals.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StatementPrinterTests {

    @Test
    void exampleStatement() {
        Map<String, Play> plays = Map.of(
                "hamlet",  new Play("Hamlet", "tragedy"),
                "as-like", new Play("As You Like It", "comedy"),
                "othello", new Play("Othello", "tragedy"));

        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.print(invoice, plays);

        verify(result);
    }

    @Test
    void statementWithNewPlayTypes() {
        Map<String, Play> plays = Map.of(
                "henry-v",  new Play("Henry V", "history"),
                "as-like", new Play("As You Like It", "pastoral"));

        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("henry-v", 53),
                new Performance("as-like", 55)));

        StatementPrinter statementPrinter = new StatementPrinter();
        Error error = Assertions.assertThrows(Error.class,
            () -> statementPrinter.print(invoice, plays));
        assertEquals("unknown type: history", error.getMessage());
    }

    @Test
    void statementWithNoPerformances() {
        Map<String, Play> plays = Map.of();
        Invoice invoice = new Invoice("BigCo", List.of());

        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.print(invoice, plays);

        assertEquals("""
            Statement for BigCo
            Amount owed is $0.00
            You earned 0 credits
            """, result);
    }

    @Test
    void tragedyWithThirtyOneAudienceMembers() {
        Map<String, Play> plays = Map.of("hamlet", new Play("Hamlet", "tragedy"));

        Invoice invoice = new Invoice("BigCo", List.of(new Performance("hamlet", 31)));

        var result = new StatementPrinter().print(invoice, plays);
        StatementPrinter statementPrinter = new StatementPrinter();


        assertEquals("""
            Statement for BigCo
              Hamlet: $410.00 (31 seats)
            Amount owed is $410.00
            You earned 1 credits
            """, result);
    }

    @Test
    void historicalPlayWithTwentyOneAudienceMembers() {
        Map<String, Play> plays = Map.of("henry-v", new Play("Henry V", "historical"));

        Invoice invoice = new Invoice("BigCo", List.of(new Performance("henry-v", 21)));

        var result = new StatementPrinter().print(invoice, plays);
        StatementPrinter statementPrinter = new StatementPrinter();

        assertEquals("""
            Statement for BigCo
              Henry V: $515.00 (21 seats)
            Amount owed is $515.00
            You earned 4 credits
            """, result);
    }

    @Test
    void historicalPlayWithThirtyFiveAudienceMembersHasExpectedChargeAndCredits() {
        Map<String, Play> plays = Map.of("henry-v", new Play("Henry V", "historical"));

        Invoice invoice = new Invoice("BigCo", List.of(new Performance("henry-v", 35)));

        var result = new StatementPrinter().print(invoice, plays);

        assertEquals("""
            Statement for BigCo
              Henry V: $725.00 (35 seats)
            Amount owed is $725.00
            You earned 7 credits
            """, result);
    }
}
