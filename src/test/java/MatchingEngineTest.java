import com.company.*;
import com.company.internal.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatchingEngineTest {

    @Test
    public void match() throws InterruptedException {

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

        iOrder order3 = new Order("YNDX", TransactionType.B, 100, 50);
        assertNotNull(order);

        iOrder order4 = new Order("YNDX", TransactionType.S, 100, 50);
        assertNotNull(order2);

        iOrderBook orderBook2 = orderBooks.getOrderBook("YNDX");
        assertNotNull(orderBook2);

        orderBook2.add(order3);
        orderBook2.add(order4);

        Thread.sleep(2000);

        matchingEngine.stop();

        assertEquals(2, tradeLedger.get().size());

    }
}