import java.util.ArrayList;
import java.util.List;

public class BankTransaction {
    public static int calculate(List<Integer> inp, int initAmount) {
        int j = 0;
        int ans = 0;
        
        for (int i = 0; i < inp.size(); i++) {
            j = Math.max(j, i);
            while (j < inp.size() && initAmount + inp.get(j) >= 0) {
                initAmount += inp.get(j++);
            }

            ans = Math.max(ans, j - i);
            if (i < j) initAmount -= inp.get(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        List<Integer> inp = new ArrayList<>();
        inp.add(1);
        inp.add(-3);
        inp.add(5);
        inp.add(-2);
        inp.add(1);
        System.out.println(calculate(inp, 1));

        inp.clear();
        inp.add(-3);
        inp.add(-4);
        inp.add(-5);
        inp.add(-6);
        inp.add(-7);
        inp.add(-100);
        System.out.println(calculate(inp, 1));
    }
}
