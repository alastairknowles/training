package concurrency;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class AdderTest {
    
    @Test
    public void shouldAddMultithreadedly() throws InterruptedException, ExecutionException {
        for (int i = 0; i <= 25; i++) {
            Adder calculator = new Adder(2, i, i);
            int result = calculator.calculate();
            Assert.assertEquals(2 + (i * i), result);
        }
    }
    
}
