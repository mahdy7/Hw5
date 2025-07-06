import java.util.LinkedList;

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

    public synchronized void enqueue(PrintJob job) throws InterruptedException {
        if (currentSize == maxSize){
            wait();
        }
        queue.add(job);
        currentSize++;
        System.out.printf("%s [%s] Enqueued %s\n", Time.getDate(), Thread.currentThread().getName(), job.toString());
        notifyAll();
    }

    public synchronized PrintJob dequeue() throws InterruptedException {
        if (queue.isEmpty() && !finished) {
            wait();
        }
            if (queue.isEmpty() && finished) return null;

            PrintJob job = queue.removeFirst();
            System.out.printf("%s [%s] Dequeued %s\n", Time.getDate(), Thread.currentThread().getName(), job.toString());
            currentSize--;
            notifyAll();

            return job;
    }

    /**
     * marks the end of the program
     */
    public synchronized void finish(){
        finished = true;
        notifyAll();
    }

}
