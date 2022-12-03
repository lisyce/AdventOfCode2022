package day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("src/day03/input.txt");
        Scanner s = new Scanner(input);

        // part 1
        int priorityTotal = 0;

        while (s.hasNextLine()) {
            String rucksack = s.nextLine();
            Set<Character> chars = new HashSet<>();
            for (int i = 0; i < rucksack.length(); i++) {
                char c = rucksack.charAt(i);
                if (i < rucksack.length() / 2) {
                    chars.add(c);
                } else {
                    if (chars.contains(c)) {
                        int priority = calcPriority(c);
                        priorityTotal += priority;
                        break;
                    }
                }
            }
        }
        System.out.println("Part 1: " + priorityTotal);

        // part 2
        s = new Scanner(input);
        priorityTotal = 0;

        while (s.hasNextLine()) {
            String sack1 = s.nextLine();
            String sack2 = s.nextLine();
            String sack3 = s.nextLine();

            for (int i = 0; i < sack1.length(); i++) {
                char c = sack1.charAt(i);
                if (sack2.indexOf(c) != -1 && sack3.indexOf(c) != -1) {
                    priorityTotal += calcPriority(c);
                    break;
                }
            }
        }

        System.out.println("Part 2: " + priorityTotal);
    }

    public static int calcPriority(char c) {
        if (c >= 97) return c - 'a' + 1;
        else return c - 'A' + 27;
    }
}
