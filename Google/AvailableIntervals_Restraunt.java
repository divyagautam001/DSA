// O(NlogN) where N is number of reservations
// Sort by start time and iterate through given intervals, maintaining a count of people present in the restaurant at any given time. 
// Check if accommodating additional people (up to a given limit) would exceed the restaurant's capacity.


import java.util.*;

class Event implements Comparable<Event> {
    double time;
    double ppl;

    public Event(double time, double ppl) {
        this.time = time;
        this.ppl = ppl;
    }

    @Override
    public int compareTo(Event other) {
        return Double.compare(this.time, other.time);
    }
}

public class Main {
    public static void main(String[] args) {
        double restaurantStart = 9;
        double restaurantEnd = 22;
        int capacity = 5;
        double[][] reservations = {{10, 14, 3}, {11, 13, 2}, {13.5, 15, 1}, {16, 20, 2}};
        int N = 2;

        List<Event> events = new ArrayList<>();
        for (double[] reservation : reservations) {
            events.add(new Event(reservation[0], reservation[2]));
            events.add(new Event(reservation[1], -reservation[2]));
        }
        Collections.sort(events);

        double currentPpl = 0;
        double prevTime = restaurantStart;
        for (Event event : events) {
            if (currentPpl + N <= capacity) {
                System.out.println("[" + prevTime + ", " + event.time + "]");
            }
            currentPpl += event.ppl;
            prevTime = event.time;
        }
        if (currentPpl + N <= capacity) {
            System.out.println("[" + prevTime + ", " + restaurantEnd + "]");
        }
    }
}
