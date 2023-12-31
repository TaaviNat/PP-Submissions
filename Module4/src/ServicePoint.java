import eduni.distributions.ContinuousGenerator;
import eduni.distributions.Normal;

import java.util.LinkedList;

public class ServicePoint {
    private static final String GREEN = "\033[0;32m";
    private static final String WHITE = "\033[0;37m";
    private LinkedList<Customer> queue;
    private ContinuousGenerator generator;
    private EventList eventList;
    private EventType eventTypeScheduled;
    private double serviceTimeSum;
    private int customerServiced;
    private boolean reserved = false;

    public ServicePoint(ContinuousGenerator g, EventList tl, EventType type) {
        this.generator = g;
        this.eventList = tl;
        this.eventTypeScheduled = type;

        this.serviceTimeSum = 0;
        this.customerServiced = 0;

        queue = new LinkedList<>();
    }

    public void addToQueue(Customer a) {
        queue.addFirst(a);
    }

    public Customer removeFromQueue() {
        if (queue.size() > 0) {
            Customer a = queue.removeLast();
            a.setRemovalTime(Clock.getInstance().getClock());
            double serviceTime = a.getRemovalTime() - a.getArrivalTime();
            System.out.printf(" %sCustomer #%d serviced, service time: %.2f%s\n", GREEN, a.getId(), serviceTime, WHITE);
            serviceTimeSum += serviceTime;
            customerServiced++;
            reserved = false;
            return a;
        } else
            return null;
    }

    public void beginService() {
        System.out.printf("%sStarting a new service for the customer #%d%s", GREEN, queue.peek().getId(), WHITE);

        reserved = true;
        double serviceTime = generator.sample();
        eventList.add(new Event(eventTypeScheduled, Clock.getInstance().getClock() + serviceTime));
    }

    public boolean isReserved() {
        return reserved;
    }

    public boolean isOnQueue() {
        return queue.size() > 0;
    }

    public int getCustomerServiced() {
        return customerServiced;
    }

    public double getMeanServiceTime() {
        return serviceTimeSum / customerServiced;
    }

    public void serve() {
        Customer a;
        Normal normalGenerator = new Normal(5, 1);

        //Clock.getInstance().setClock(Clock.getInstance().getClock() + 5);
        a = removeFromQueue();
        while (a != null) {
            Clock.getInstance().setClock(Clock.getInstance().getClock() + normalGenerator.sample());

            a.setRemovalTime(Clock.getInstance().getClock());
            a.reportResults();

            a = removeFromQueue();
        }
    }
}