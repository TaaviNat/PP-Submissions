import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;

public class ServicePoint {
    private LinkedList<Customer> queue;
    private int customerServed;
    private long serviceTimeSum;
    public ServicePoint() {
        queue = new LinkedList<>();
        customerServed = 0;
        serviceTimeSum = 0;
    }

    public void addCustomer(Customer a) {
        a.setStartTime(System.currentTimeMillis());
        queue.addFirst(a);
    }

    private Customer removeFromQueue() {
        if (queue.size() > 0)
            return queue.removeLast();
        else
            return null;
    }

    public void serve() {
        Customer a;
        long tic, toc;

        a = removeFromQueue();
        while (a != null) {
            // serve the customer
            tic = System.currentTimeMillis();
            try {
                Thread.sleep(Math.round(Math.random() * 9000 + 1000)); // random delay 1s .. 10s
            } catch (InterruptedException e) {}

            // customer served
            toc = System.currentTimeMillis(); a.setEndTime(toc);
            long serviceTime = toc - tic;
            customerServed++; serviceTimeSum += serviceTime;
            System.out.println(a + " ms " + serviceTime + " ms");

            a = removeFromQueue();
        }
    }

    public double avgServiceTime() {
        if (customerServed != 0) {
            return (double)serviceTimeSum / customerServed;
        } else
            return 0.0;

    }
}