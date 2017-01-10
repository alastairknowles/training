package concurrency;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class ThreadedAdderForkJoinTest {
    
    @Test
    public void shouldAddMultithreadedly() throws InterruptedException, ExecutionException {
        for (int i = 0; i <= 10; i++) {
            ThreadedAdderForkJoin calculator = new ThreadedAdderForkJoin(2, i, i);
            int result = calculator.calculate();
            Assert.assertEquals(2 + (i * i), result);
        }
    }
    
}
