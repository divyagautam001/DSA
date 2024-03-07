import java.util.*;

class Schedule {
    int start, end;
    String name;

    public Schedule(int start, int end, String name) {
        this.start = start;
        this.end = end;
        this.name = name;
    }
}

class onCall {
    int start, end;
    Set<String> peoples;

    public onCall(int start, int end, Set<String> peoples) {
        this.start = start;
        this.end = end;
        this.peoples = peoples;
    }
}

public class Main {

    static List<onCall> res = new ArrayList<>();

    static void print() {
        for (onCall period : res) {
            System.out.print(period.start + " " + period.end + " : ");
            for (String name : period.peoples) {
                System.out.print(name + ", ");
            }
            System.out.println();
        }
    }

    static void intervalsList(List<Schedule> arr) {
        TreeMap<Integer, Set<String>> mp = new TreeMap<>();

        for (Schedule schedule : arr) {
            mp.putIfAbsent(schedule.start, new HashSet<>());
            mp.putIfAbsent(schedule.end, new HashSet<>());

            mp.get(schedule.start).add(schedule.name);
            mp.get(schedule.end).add(schedule.name);
        }

        Set<String> ans = new HashSet<>();
        int last = -1;

        for (Map.Entry<Integer, Set<String>> entry : mp.entrySet()) {
            if (!ans.isEmpty()) {
                res.add(new onCall(last, entry.getKey(), new HashSet<>(ans)));
            }

            for (String name : entry.getValue()) {
                if (ans.contains(name)) {
                    ans.remove(name);
                } else {
                    ans.add(name);
                }
            }
            last = entry.getKey();
        }
    }

    public static void main(String[] args) {
        Schedule a = new Schedule(1, 10, "Abby");
        Schedule b = new Schedule(5, 7, "Ben");
        Schedule c = new Schedule(6, 12, "Carla");
        Schedule d = new Schedule(15, 17, "David");
        Schedule e = new Schedule(19, 20, "Eva");

        List<Schedule> arr = Arrays.asList(a, e, c, d, b);

        intervalsList(arr);
        print();
    }
}
