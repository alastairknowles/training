package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadedAdderExecutorService extends ThreadedAdder {
    
    protected ExecutorService executorService;
    
    public ThreadedAdderExecutorService(int start, int interval, int repetitions) {
        super(start, interval, repetitions);
    }
    
    public ThreadedAdderExecutorService(int start, int interval, int repetitions, int maxThreads) {
        this(start, interval, repetitions);
        
        if (maxThreads > 0) {
            this.executorService = Executors.newFixedThreadPool(maxThreads);
        }
    }
    
    @Override
    public int calculate() throws InterruptedException, ExecutionException {
        List<Callable<Void>> executions = getExecutions();
        if (executions.size() > 0) {
            for (Future future : executorService.invokeAll(executions)) {
                future.get();
            }
        }
        
        return this.start;
    }
    
    protected List<Callable<Void>> getExecutions() {
        List<Callable<Void>> executions = new ArrayList();
        for (int i = 0; i < this.repetitions; i++) {
            executions.add(() -> {
                synchronized (this) {
                    add();
                }
                
                return null;
            });
        }
        
        return executions;
    }
    
}
