/* 

O(n) 

Algorithm:
Maintain a HashMap to store the start time of each request.
For each log entry, check if it’s a ‘start’ or ‘end’ type.
If it’s a ‘start’, store the timestamp in the HashMap with the id as the key.
If it’s an ‘end’, check the current timestamp against the start time in the HashMap.
If the difference is greater than the timeout, store the id and the current timestamp as the time when the timeout was detected.

*/

import java.util.*;

class Log {
    int id;
    int timestamp;
    String type;

    public Log(int id, int timestamp, String type) {
        this.id = id;
        this.timestamp = timestamp;
        this.type = type;
    }
}

public class TimeoutDetector {
    private Map<Integer, Integer> startTimes = new HashMap<>();
    private int timeout;

    public TimeoutDetector(int timeout) {
        this.timeout = timeout;
    }

    public List<Integer> detect(List<Log> logs) {
        List<Integer> timeouts = new ArrayList<>();

        for (Log log : logs) {
            if (log.type.equals("Start")) {
                startTimes.put(log.id, log.timestamp);
            } else if (log.type.equals("End")) {
                int startTime = startTimes.get(log.id);
                if (log.timestamp - startTime > timeout) {
                    timeouts.add(log.id);
                }
            }
        }

        return timeouts;
    }
}
