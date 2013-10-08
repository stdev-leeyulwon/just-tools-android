package com.shiftpsh.just.counter;

public class Counter {
    public String title;
    public long counts;
    public Counter(){
        super();
    }
    
    public Counter(long i, String title) {
        super();
        this.counts = i;
        this.title = title;
    }
}
