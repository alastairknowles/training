package concurrency;

import java.util.concurrent.ExecutionException;

public class Adder {
    
    protected int start;
    
    protected int interval;
    
    protected int repetitions;
    
    public Adder(int start, int interval, int repetitions) {
        this.start = start;
        this.interval = interval;
        this.repetitions = repetitions;
    }
    
    public int calculate() throws InterruptedException, ExecutionException {
        for (int i = 0; i < repetitions; i++) {
            add();
        }
        
        return start;
    }
    
    protected void add() {
        this.start = this.start + this.interval;
    }
    
}
