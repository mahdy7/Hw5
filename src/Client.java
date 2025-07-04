
import java.util.Date;
import java.util.Random;


public class Client implements Runnable{
    private PrintQueue  listOfTasks;
    private int numberOfTasks;

    Client(PrintQueue  listOfTasks, int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
        this.listOfTasks = listOfTasks;
    }

    @Override
    public void run() {
        int maxRange = 500,minRange = 100;
        int sleepTime = new Random().nextInt(maxRange- minRange +1 )+minRange;
        String threadName = Thread.currentThread().getName();
        Date now = new Date();
        String format = String.format("[%04d-%02d-%02d %02d:%02d:%02d]", now.getYear() + 1900, now.getMonth() + 1, now.getDate(), now.getHours(), now.getMinutes(), now.getSeconds());
        System.out.println(format + threadName + "Client has started");
        for (int i = 0; i < numberOfTasks; i++) {
            PrintJob job = new PrintJob(i,(i * 7) % 20 + 1,threadName);
            try{
            listOfTasks.enqueue(job);}
            catch(Exception e){
                Thread.currentThread().interrupt();
                System.out.println("Client thread interrupted");
                return;
            }
            System.out.println(format + threadName + "submitted" + job);
            try{
            Thread.sleep(sleepTime);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Client thread interrupted");
                return;
            }
            System.out.println(format + " " + threadName + " Client finished");
        }

    }
}
