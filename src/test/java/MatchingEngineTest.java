import com.company.*;
import com.company.internal.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatchingEngineTest {

    @Test
    public void match() {

        iOrderBooks orderBooks = new OrderBooks();
        assertNotNull(orderBooks);

        iTradeLedger tradeLedger;
        tradeLedger = new TradeLedger();
        assertNotNull(tradeLedger);

        MatchingEngine matchingEngine = new MatchingEngine(orderBooks, tradeLedger);
        assertNotNull(matchingEngine);

        iOrder order = new Order("GOOG", TransactionType.B, 100, 50);
        assertNotNull(order);

        iOrder order2 = new Order("GOOG", TransactionType.S, 100, 50);
        assertNotNull(order2);

        iOrderBook orderBook = orderBooks.getOrderBook("GOOG");
        assertNotNull(orderBook);

        orderBook.add(order);
        orderBook.add(order2);

        matchingEngine.match(orderBooks, tradeLedger);
        matchingEngine.stop();

        assertEquals(1, tradeLedger.get().size());

    }
}