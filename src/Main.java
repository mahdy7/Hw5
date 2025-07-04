import java.util.Random;

public class Main {
    public static final Random RANDOM = new Random(42);

    public static void main(String[] args) throws InterruptedException {
        queueOrderTest();
        System.out.println("--------------------------");
        finishEmptyQueueTest();
        System.out.println("--------------------------");
        singleClientTest();
        System.out.println("--------------------------");
        multiClientTest();
    }

    private static void queueOrderTest() throws InterruptedException {
        PrintQueue queue = new PrintQueue(3);
        PrintJob job1 = new PrintJob(1, 1, "A");
        PrintJob job2 = new PrintJob(2, 2, "B");
        queue.enqueue(job1);
        queue.enqueue(job2);
        PrintJob out1 = queue.dequeue();
        if (out1 != job1) throw new AssertionError("Expected first job to be job1");
        PrintJob out2 = queue.dequeue();
        if (out2 != job2) throw new AssertionError("Expected second job to be job2");
    }

    private static void finishEmptyQueueTest() throws InterruptedException {
        PrintQueue queue = new PrintQueue(2);
        queue.finish();
        PrintJob p = queue.dequeue();
        if (p != null) throw new AssertionError("Expected null on dequeue after finish");
    }

    private static void singleClientTest() throws InterruptedException {
        int jobs = 5, capacity = 2;
        PrintQueue queue = new PrintQueue(capacity);
        Thread spooler = new Thread(new Spooler(queue), "Spooler");
        Thread client = new Thread(new Client(queue, jobs), "Client-1");
        spooler.start();
        client.start();
        client.join();
        queue.finish();
        spooler.join();
        if (queue.dequeue() != null) throw new AssertionError("Queue not empty after single client processing");
    }

    private static void multiClientTest() throws InterruptedException {
        int numClients = 3, jobsPerClient = 4, capacity = 5;
        PrintQueue queue = new PrintQueue(capacity);
        Thread spooler = new Thread(new Spooler(queue), "Spooler");
        spooler.start();
        Thread[] clients = new Thread[numClients];
        for (int i = 0; i < numClients; i++) {
            clients[i] = new Thread(new Client(queue, jobsPerClient), "Client-" + (i+1));
            clients[i].start();
        }
        for (Thread c : clients) c.join();
        queue.finish();
        spooler.join();
        if (queue.dequeue() != null) throw new AssertionError("Queue not empty after multi-client processing");
    }
}
