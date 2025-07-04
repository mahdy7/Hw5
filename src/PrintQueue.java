import java.util.LinkedList;
import java.util.Date;


public class PrintQueue {
    private LinkedList<PrintJob> queue;
    private int maxSize;
    private boolean finished;

    public PrintQueue(int maxSize) {
        this.queue = new LinkedList<>();
        this.maxSize = maxSize;
        this.finished = false;
    }

    public void enqueue(PrintJob job) {
    }

    public PrintJob dequeue() {
        return null;
    }
}

