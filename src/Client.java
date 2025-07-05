public class Client implements Runnable {
    private PrintQueue queue;
    private int numberOfJobs;

    Client(PrintQueue queue, int numberOfJobs) {
        this.queue = queue;
        this.numberOfJobs = numberOfJobs;
    }
    public void run() {

    }
}
