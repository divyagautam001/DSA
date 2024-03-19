import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrintFileSystem {

    class Dir {
        Map<String, Dir> dirs = new HashMap<>();
    }

    Dir root;

    private PrintFileSystem() {
        root = new Dir();
    }

    private void mkdir(String path) {
        if (path == null || path.length() == 0 || path.equals("/")) {
            return;
        }

        Dir temp = root;
        String[] array = path.split("/");
        for (int i = 1; i < array.length; i++) {
            String name = array[i];
            if (!temp.dirs.containsKey(name)) {
                temp.dirs.put(name, new Dir());
            }
            temp = temp.dirs.get(name);
        }
    }

    private void printFileStructure(List<String> input) {
        for (String path : input) {
            mkdir(path);
        }

        printStructure(root.dirs, 0);
    }

    private void printStructure(Map<String, Dir> dirMap, int tabLength) {
        if (dirMap.size() == 0) {
            return;
        }

        for (Map.Entry<String, Dir> entry : dirMap.entrySet()) {
            for (int i = 0; i < tabLength; i++) {
                System.out.print(" -");
            }

            System.out.print(entry.getKey() + "\n");

            printStructure(entry.getValue().dirs, tabLength + 1);
        }
    }

    public static void main(String[] args) {
        ArrayList<String> input = new ArrayList<String>();
        input.add("/C:/users/Downloads");
        input.add("/C:/users/Documents");
        input.add("/C:/Windows/");
        input.add("/D:/Files");
        input.add("/F:/Apps/App1");

        PrintFileSystem s = new PrintFileSystem();
        s.printFileStructure(input);
    }
}
