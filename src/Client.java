import java.util.Random;

public class Client implements Runnable{
    private PrintQueue  listOfTasks;
    private int numberOfTasks;
    static private int ClientIds = 1;


    Client(PrintQueue  listOfTasks, int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
        this.listOfTasks = listOfTasks;
    }

    /**
     * run function is used by threads, it adds up all the jobs that client want to be printed
     */
    @Override
    public void run() {
        int minRange = 100;
        int maxRange = 500;
        int sleepTime = new Random().nextInt(maxRange - minRange +1 )+ minRange;
        System.out.printf("%s [%s] Client started\n",Time.getDate(), Thread.currentThread().getName());
        for (int i = 0; i < numberOfTasks; i++) {
                PrintJob job = new PrintJob(ClientIds++, (i * 7) % 20 + 1, Thread.currentThread().getName());
                try {
                    listOfTasks.enqueue(job);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.printf("%s [%s] submitted %s\n", Time.getDate(), Thread.currentThread().getName(), job);
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Client thread interrupted");
                    return;
                }
        }
        System.out.printf("%s [%s] Client finished\n",Time.getDate(), Thread.currentThread().getName());
    }
}
