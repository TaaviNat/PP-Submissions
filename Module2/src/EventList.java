import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class EventList {
    private PriorityQueue<Event> eventList;

    public EventList() {
        eventList = new PriorityQueue<>();
    }

    public void add(Event e) {
        System.out.printf("Adding to the event list %s T.2f/n", e.getType() + " " + e.getTime());
        eventList.add(e);
    }

    public Event remove() {
        if (eventList.isEmpty()) {
            return null;
        }
        System.out.printf("Removing from the event list %s %.2f" , eventList.peek().getType() + " " +  eventList.peek().getTime());
        return eventList.remove();
    }

    public void print() {
        Object[] tmp = eventList.toArray();
        Arrays.sort(tmp);
        for (Object e : tmp) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        EventList eventList = new EventList();
        Random random = new Random();
        // generate events
        for (int i = 0; i < 10; i++) {
            EventType type;
            if (random.nextBoolean())
                type = EventType.ARR;
            else
                type = EventType.DEP;
            eventList.add(new Event(type, random.nextInt(1000)));
        }

        // remove the first event to be processed
        System.out.println("Event removed " + eventList.remove());

        // finally print the whole event list
        eventList.print();

    }
}
