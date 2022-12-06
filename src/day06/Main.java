package day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("src/day06/input.txt");
        Scanner s = new Scanner(input);
        String buffer = s.nextLine();

        int pt1 = -1;
        int pt2 = -1;
        for (int i = 0; i < buffer.length(); i++) {
            if (pt1 > 0 && pt2 > 0) break;

            String pt1Packet = buffer.substring(i, i+4);
            String pt2Packet = buffer.substring(i, i+14);
            if (containsAllUniqueChars(pt1Packet) && pt1 < 0) {
                pt1 = i+4;
            }

            if (containsAllUniqueChars(pt2Packet) && pt2 < 0) {
                pt2 = i+14;
            }
        }

        System.out.println("Part 1: " + pt1);
        System.out.println("Part 2: " + pt2);
    }

    public static boolean containsAllUniqueChars(String s) {
        Set<Character> chars = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            chars.add(s.charAt(i));
        }
        return chars.size() == s.length();
    }
}
