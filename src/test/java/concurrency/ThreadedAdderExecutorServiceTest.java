package concurrency;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class ThreadedAdderExecutorServiceTest {
    
    @Test
    public void shouldAddMultithreadedly() throws InterruptedException, ExecutionException {
        for (int i = 0; i <= 10; i++) {
            ThreadedAdderExecutorService calculator = new ThreadedAdderExecutorService(2, i, i, i);
            int result = calculator.calculate();
            Assert.assertEquals(2 + (i * i), result);
        }
    }
    
}
