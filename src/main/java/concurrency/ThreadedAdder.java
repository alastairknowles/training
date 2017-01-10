package concurrency;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;

public class ThreadedAdder extends Adder {
    
    private Semaphore semaphore;
    
    private Set<Thread> runningThreads = new HashSet();
    
    private int maxRunningThreads;
    
    public ThreadedAdder(int start, int interval, int repetitions) {
        super(start, interval, repetitions);
    }
    
    public ThreadedAdder(int start, int interval, int repetitions, int maxThreads) {
        this(start, interval, repetitions);
        this.semaphore = new Semaphore(maxThreads);
    }
    
    @Override
    public int calculate() throws InterruptedException, ExecutionException {
        List<Thread> threads = new ArrayList();
        for (int i = 0; i < this.repetitions; i++) {
            Thread thread = new Thread(() -> {
                Thread currentThread = Thread.currentThread();
                try {
                    semaphore.acquire();
                    startCalculation(currentThread);
                    currentThread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    finishCalculation(currentThread);
                    semaphore.release();
                }
            });
            
            threads.add(thread);
            thread.start();
        }
        
        for (Thread thread : threads) {
            thread.join();
        }
        
        return this.start;
    }
    
    public int getMaxRunningThreads() {
        return maxRunningThreads;
    }
    
    private synchronized void startCalculation(Thread thread) throws InterruptedException {
        this.runningThreads.add(thread);
        int runningThreads = this.runningThreads.size();
        if (runningThreads > this.maxRunningThreads) {
            this.maxRunningThreads = runningThreads;
        }
        
        add();
    }
    
    private synchronized void finishCalculation(Thread thread) {
        this.runningThreads.remove(thread);
    }
    
}
