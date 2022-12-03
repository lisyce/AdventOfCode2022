package day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("src/day02/input.txt");
        Scanner s = new Scanner(input);

        int pt1Total = 0;
        int pt2Total = 0;

        while (s.hasNextLine()) {
            String[] shapes = s.nextLine().split(" ");
            pt1Total += calcPtsOne(shapes[0], shapes[1]);
            pt2Total += calcPtsTwo(shapes[0], shapes[1]);
        }

        System.out.println("Part 1: " + pt1Total);
        System.out.println("Part 2: " + pt2Total);
    }

    public static int calcPtsOne(String p1, String p2) {
        int p1Pts = p1.equals("A") ? 1 : p1.equals("B") ? 2 : 3;
        int p2Pts = p2.equals("X") || p2.equals("A") ? 1 : p2.equals("Y") || p2.equals("B") ? 2 : 3;

        int diff = p2Pts - p1Pts;

        if (diff == 1 || diff == -2) return p2Pts + 6; // win
        else if (diff == 0) return p2Pts + 3; // tie
        return p2Pts; // loss
    }

    public static int calcPtsTwo(String p1, String outcome) {
        // tie
        switch(outcome) {
            case "Y":
                return calcPtsOne(p1, p1);
            case "Z":
                String move = p1.equals("A") ? "B" : p1.equals("B") ? "C" : "A";
                return calcPtsOne(p1, move);
            case "X":
                move = p1.equals("A") ? "C" : p1.equals("B") ? "A" : "B";
                return calcPtsOne(p1, move);
            default:
                throw new IllegalArgumentException();
        }
    }

}
