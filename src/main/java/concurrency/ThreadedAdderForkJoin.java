package concurrency;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class ThreadedAdderForkJoin extends ThreadedAdderExecutorService {
    
    private ForkJoinPool forkJoinPool = new ForkJoinPool();
    
    public ThreadedAdderForkJoin(int start, int interval, int repetitions) {
        super(start, interval, repetitions);
    }
    
    public int calculate() throws InterruptedException, ExecutionException {
        List<Callable<Void>> executions = getExecutions();
        if (executions.size() > 0) {
            forkJoinPool.invokeAll(executions);
        }
        
        return this.start;
    }
    
}
