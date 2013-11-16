package net.shiftstudios.just.rev3.counter;

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
