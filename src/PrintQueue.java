import java.util.LinkedList;
import java.util.Date;

public class PrintQueue {
    private final int maxSize;
    private int currentSize;
    private LinkedList<PrintJob> queue;
    private boolean finished;

    PrintQueue(int capacity) {
        queue = new LinkedList<>();
        maxSize = capacity;
        currentSize = 0;
        finished = false;
    }

    public void enqueue(PrintJob job) {
        while (currentSize == maxSize);
        queue.add(job);
        currentSize++;
        System.out.printf("%s [%s] Enqueued %s\n",Time.getDate(),Thread.currentThread().getName(),job.toString());
    }

    public PrintJob dequeue() {
        while (queue.isEmpty() && !finished) ;
        if (queue.isEmpty() && finished)  return null;

        PrintJob job = queue.removeFirst();
        System.out.printf("%s [%s] Dequeued %s\n",Time.getDate(),Thread.currentThread().getName(),job.toString());
        currentSize--;

        return job;
    }

    /**
     * marks the end of the program
     */
    public void finish(){
        finished = true;
    }

}
