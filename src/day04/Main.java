package day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("src/day04/input.txt");
        Scanner s = new Scanner(input);

        int fullOverlaps = 0;
        int overlaps = 0;

        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] halves = line.split(",");

            int[] one = Arrays.stream(halves[0].split("-")).mapToInt(Integer::parseInt).toArray();
            int[] two = Arrays.stream(halves[1].split("-")).mapToInt(Integer::parseInt).toArray();

            if ((one[0] <= two[0] && one[1] >= two[1]) || (two[0] <= one[0] && two[1] >= one[1])) fullOverlaps++;
            if ((one[0] <= two[0] && one[1] >= two[0]) || (two[0] <= one[0] && two[1] >= one[0])) overlaps++;

        }

        System.out.println("part 1: " + fullOverlaps);
        System.out.println("part 2: " + overlaps);

    }
}
