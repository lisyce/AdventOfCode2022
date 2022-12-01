package day01;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("src/day01/input.txt");
        Scanner s = new Scanner(input);

        int thisElf = 0;

        List<Integer> allCals = new ArrayList<>();

        while (s.hasNextLine()) {
            String line = s.nextLine();
            if (line.isEmpty()) {

                allCals.add(thisElf);
                thisElf = 0;
                continue;
            }

            thisElf += Integer.parseInt(line);
        }

        Collections.sort(allCals);
        int maxCals = allCals.get(allCals.size() - 1);
        int secCals = allCals.get(allCals.size() - 2);
        int thirdCals = allCals.get(allCals.size() - 3);

        System.out.println("Max Cals: " + maxCals);
        System.out.println("Sum of top 3: " + (maxCals + secCals + thirdCals));

    }
}