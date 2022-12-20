package day10; 
 
import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.*; 
 
public class Main { 
    public static void main(String[] args) throws FileNotFoundException { 
        File input = new File("src/day10/input.txt");
        Scanner s = new Scanner(input);

        int x = 1;
        boolean adding = false;
        int addAmt = 0;

        String instruction;
        int nextBenchmark = 20;
        int pt1SignalStrength = 0;

        for (int i = 1; i <= 220; i++) {
            if (i == nextBenchmark) {
                pt1SignalStrength += nextBenchmark * x;
                nextBenchmark += 40;
            }

            if (adding) {
                x += addAmt;
                adding = false;
            } else {
                instruction = s.nextLine();
                if (!instruction.equals("noop")) {
                    adding = true;
                    addAmt = Integer.parseInt(instruction.split(" ")[1]);
                }
            }

        }
        System.out.println("Part 1: " + pt1SignalStrength);

    } 
} 
