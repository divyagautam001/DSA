import java.util.*;

class Node {
    String val;
    Node prev;
    Node next;

    public Node(String val) {
        this.val = val;
    }
}

class SearchDS {
    int N;
    Map<String, Node> map;
    Node left;
    Node right;

    public SearchDS(int num) {
        this.N = num;
        map = new HashMap<>();
        left = new Node(null); // Initialize left and right nodes
        right = new Node(null);
        left.next = right;
        right.prev = left;
    }

    // R: O(1)
    public void addSearch(String s) {
        if (!map.containsKey(s)) {
            map.put(s, new Node(s));
        } else {
            remove(map.get(s));
        }

        insert(map.get(s));
    }

    // R: O(N)
    public List<String> getRecentSearches() {
        List<String> res = new ArrayList<>();
        Node curr = this.right.prev; // Start from the rightmost node

        int iter = this.N;
        while (iter > 0 && curr != left) {
            res.add(curr.val);
            iter--;
            curr = curr.prev;
        }

        return res;
    }

    // Remove node from DLL
    public void remove(Node n) {
        Node prv = n.prev;
        Node nxt = n.next;

        prv.next = nxt;
        nxt.prev = prv;
    }

    // Add Node at right
    public void insert(Node n) {
        Node prv = right.prev;
        Node nxt = right;

        prv.next = n;
        nxt.prev = n;
        n.prev = prv;
        n.next = nxt;
    }

    public static void main(String[] args) {
        SearchDS searchDS = new SearchDS(5); // Capacity of 5
        searchDS.addSearch("python tutorial");
        searchDS.addSearch("machine learning");
        searchDS.addSearch("data structures");
        System.out.println(searchDS.getRecentSearches()); // Output: [data structures, machine learning, python tutorial]
        searchDS.addSearch("algorithms");
        searchDS.addSearch("Google it");
        searchDS.addSearch("ChatBots");
        System.out.println(searchDS.getRecentSearches()); // Output: [neural networks, chatbots, deep learning, algorithms, data structures]
    }
}
