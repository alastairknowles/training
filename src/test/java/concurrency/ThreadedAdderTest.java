package concurrency;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class ThreadedAdderTest {
    
    @Test
    public void shouldAddMultithreadedly() throws InterruptedException, ExecutionException {
        for (int i = 0; i <= 25; i++) {
            ThreadedAdder calculator = new ThreadedAdder(2, i, i, i);
            int result = calculator.calculate();
            int maxRunningThreads = calculator.getMaxRunningThreads();
            
            Assert.assertEquals(2 + (i * i), result);
            Assert.assertEquals(i, maxRunningThreads);
        }
    }
    
}
