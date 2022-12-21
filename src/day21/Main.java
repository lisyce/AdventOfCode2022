package day21; 
 
import java.io.File;
import java.io.FileNotFoundException; 
import java.util.*; 
 
public class Main { 
    public static void main(String[] args) throws FileNotFoundException { 

        // part 1
        MonkeyNode root = buildMonkeyTree("src/day21/input.txt");
        System.out.println("Part 1: " + root.updateValue());

        //part 2
        root = buildMonkeyTree("src/day21/input.txt");
        System.out.println("Part 2: " + findHumnVal(root));
    }

    public static long findHumnVal(MonkeyNode root) {

        // find top-level equality target
        long target;
        MonkeyNode pointer;

        if (root.left.hasChild("humn")) {
            target = root.right.updateValue();
            pointer = root.left;
        } else {
            target = root.left.updateValue();
            pointer = root.right;
        }

        while (true) {
            if (pointer.id.equals("humn")) { // this is the humn node
                return target;
            }

            // find value of the branch that doesn't contain humn
            MonkeyNode hasHumn;
            if (pointer.left.hasChild("humn")) {
                pointer.right.updateValue();
                hasHumn = pointer.left;
            } else {
                pointer.left.updateValue();
                hasHumn = pointer.right;
            }

            // get missing value
            target = pointer.calcMissingValue(target);

            // move down the tree closer to the humn node
            pointer = hasHumn;
        }
    }

    public static MonkeyNode buildMonkeyTree(String fileName) throws FileNotFoundException {
        File input = new File(fileName);
        Scanner s = new Scanner(input);

        // create a bunch of unlinked nodes
        List<MonkeyNode> nodes = new ArrayList<>();
        while (s.hasNextLine()) {
            String[] line = s.nextLine().split(": ");
            nodes.add(new MonkeyNode(line[0], line[1]));
        }

        // find the root node
        MonkeyNode root = nodeByID(nodes, "root");

        // link the nodes
        linkChildren(nodes, root);

        return root;
    }

    public static void linkChildren(List<MonkeyNode> nodes, MonkeyNode node) {
        String[] children = node.childIDs();
        if (children != null) {
            MonkeyNode left = nodeByID(nodes, children[0]);
            node.left = left;
            linkChildren(nodes, left);

            MonkeyNode right = nodeByID(nodes, children[1]);
            node.right = right;
            linkChildren(nodes, right);
        }
    }

    public static MonkeyNode nodeByID(List<MonkeyNode> nodes, String id) {
        return nodes.stream().filter((a) -> a.id.equals(id)).findFirst().get();
    }
} 
