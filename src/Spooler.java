
public class Spooler implements Runnable {
    private PrintQueue queue;

    Spooler(PrintQueue queue) {
        this.queue = queue;
    }

    /**
     * the run function is used by the threads, it print all the jobs the client request with date, thread safe.
     */
    @Override
    public void run() {
        System.out.printf("%s [%s] Spooler started\n", Time.getDate(), Thread.currentThread().getName());
        while (true) {
            PrintJob job;
            try {
                job = queue.dequeue();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (job == null) {
                System.out.printf("%s [%s] Spooler finished\n", Time.getDate(), Thread.currentThread().getName());
                return;
            }
            try {
                System.out.printf("%s [%s] Printing %s\n", Time.getDate(), Thread.currentThread().getName(), job);
                Thread.sleep((long) job.getNumPages() * 20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
