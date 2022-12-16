package day09; 
 
import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.*; 
 
public class Main {

    static Set<Point> tailVisits = new HashSet<>();
    static Point tailPos = new Point(0, 0);
    static Point headPos = new Point(0, 0);

    public static void main(String[] args) throws FileNotFoundException { 
        File input = new File("src/day09/input.txt");
        Scanner s = new Scanner(input);

        tailVisits.add(tailPos.copy());

        while (s.hasNextLine()) {
            String[] line = s.nextLine().split(" ");
            move(line[0], Integer.parseInt(line[1]));
        }

        System.out.println("Part 1: " + tailVisits.size());
    }

    // returns whether the tail moved or not
    public static void move(String input, int steps) {

        for (int i = 0; i < steps; i++) {
            // move head
            switch (input) {
                case "R" -> headPos.x += 1;
                case "L" -> headPos.x -= 1;
                case "U" -> headPos.y += 1;
                case "D" -> headPos.y -= 1;
            }


            // are the head and tail touching? if so, don't move the tail
            if (tailPos.touching(headPos)) continue;

            // make tail catch up
            // is the head in the same row or column? if so, just move one step that way
            if (tailPos.x == headPos.x) {
                tailPos.y += Math.signum(headPos.y - tailPos.y);
            } else if (tailPos.y == headPos.y) {
                tailPos.x += Math.signum(headPos.x - tailPos.x);
            } else {
                // move diagonally to keep up
                tailPos.y += Math.signum(headPos.y - tailPos.y);
                tailPos.x += Math.signum(headPos.x - tailPos.x);
            }

            tailVisits.add(tailPos.copy());
        }

    }
} 
