package day09; 
 
import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.*; 
 
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("src/day09/input.txt");
        Scanner s = new Scanner(input);

        List<Point> pt1Rope = buildRope(2);
        Set<Point> pt1History = new HashSet<>();

        List<Point> pt2Rope = buildRope(10);
        Set<Point> pt2History = new HashSet<>();

        pt1History.add(new Point(null));
        pt2History.add(new Point(null));

        while (s.hasNextLine()) {
            String[] line = s.nextLine().split(" ");
            move(line[0], Integer.parseInt(line[1]), pt1Rope, pt1History);
            move(line[0], Integer.parseInt(line[1]), pt2Rope, pt2History);
        }

        System.out.println("Part 1: " + pt1History.size());
        System.out.println("Part 2: " + pt2History.size());
    }

    public static List<Point> buildRope(int length) {
        List<Point> rope = new ArrayList<>();
        Point head = new Point(null);
        rope.add(head);
        Point prev = head;

        for (int i = 0; i < length - 1; i++) {
            Point node = new Point(prev);
            rope.add(node);
            prev = node;
        }

        return rope;
    }


    public static void move(String input, int steps, List<Point> rope, Set<Point> tailVisits) {
        Point dummyHead = rope.get(0);

        for (int i = 0; i < steps; i++) {
            // move real head
            switch (input) {
                case "R" -> dummyHead.x += 1;
                case "L" -> dummyHead.x -= 1;
                case "U" -> dummyHead.y += 1;
                case "D" -> dummyHead.y -= 1;
            }

            // move the rest of the nodes
            for (int n = 1; n < rope.size(); n++) {
                Point node = rope.get(n);

                // is the node touching its leader? if so, don't move it
                if (node.touching(node.following)) continue;

                // make node catch up
                // is the leader in the same row or column? if so, just move one step that way
                if (node.x == node.following.x) {
                    node.y += Math.signum(node.following.y - node.y);
                } else if (node.y == node.following.y) {
                    node.x += Math.signum(node.following.x - node.x);
                } else {
                    // move diagonally to keep up
                    node.y += Math.signum(node.following.y - node.y);
                    node.x += Math.signum(node.following.x - node.x);
                }

                if (n == rope.size() - 1) {
                    tailVisits.add(node.copy()); // only add the very end of the rope to the Set
                }
            }
        }

    }

} 
