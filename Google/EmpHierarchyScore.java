// O(V + E) due to dfs
// DFS 


import java.util.*;

class CompanyScore {
    private Map<String, List<String>> adj;
    private Map<String, Integer> score;
    private Map<String, String> reportTo;

    public CompanyScore(Map<String, List<String>> graph) {
        adj = new HashMap<>();
        score = new HashMap<>();
        reportTo = new HashMap<>();

        for (Map.Entry<String, List<String>> entry : graph.entrySet()) {
            String emp = entry.getKey();
            adj.put(emp, new ArrayList<>(entry.getValue()));
            score.put(emp, 0);
            for (String reportee : entry.getValue()) {
                reportTo.put(reportee, emp);
            }
        }
    }

    private void dfs(String curNode) {
        score.put(curNode, 1);
        for (String child : adj.get(curNode)) {
            dfs(child);
            score.put(curNode, score.get(curNode) + score.get(child));
        }
    }

    public void calculateScore() {
        String ceo = null;
        for (String emp : adj.keySet()) {
            if (!reportTo.containsKey(emp)) {
                ceo = emp;
                break;
            }
        }
        assert ceo != null;
        dfs(ceo);
    }
  
    //ONLY WHEN ASKED FOLLOW UP
    //Write a function to recompute these employee scores should someone move teams.
    public void pushChangeUpTree(String node, int val) {
        while (node != null) {
            score.put(node, score.get(node) + val);
            node = reportTo.get(node);
        }
    }

    public void changeReportingManager(String emp, String newManager) {
        String prevManager = reportTo.get(emp);
        for (String child : adj.get(emp)) {
            reportTo.put(child, prevManager);
        }
        pushChangeUpTree(prevManager, -1);

        adj.get(emp).clear();
        score.put(emp, 1);
        reportTo.put(emp, newManager);

        pushChangeUpTree(newManager, 1);
    }

    public void changeReportingManagerWithTeam(String emp, String newManager) {
        String prevManager = reportTo.get(emp);
        pushChangeUpTree(prevManager, -score.get(emp));
        reportTo.put(emp, newManager);
        pushChangeUpTree(newManager, score.get(emp));
    }

  // FOLLOW UP

    public int getEmployeeScore(String emp) {
        return score.get(emp);
    }

    public void printAllScores() {
        for (Map.Entry<String, Integer> entry : score.entrySet()) {
            System.out.println("score[" + entry.getKey() + "] = " + entry.getValue());
        }
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("alice", Arrays.asList("bob"));
        graph.put("bob", Arrays.asList("charlie", "dave"));
        graph.put("charlie", new ArrayList<>());
        graph.put("dave", new ArrayList<>());

        CompanyScore c = new CompanyScore(graph);

        c.calculateScore();
        c.printAllScores();

        c.changeReportingManager("dave", "alice");
        c.printAllScores();

        c.changeReportingManagerWithTeam("bob", "dave");
        c.printAllScores();
    }
}
