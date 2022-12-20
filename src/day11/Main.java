package day11; 
 
import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class Main { 
    public static void main(String[] args) throws FileNotFoundException { 

        // build data about the monkeys
        // store monkeys where their idx = their id
        List<Monkey> pt1Monkeys = buildMonkeys("src/day11/test.txt");

        // simulate the throwing
        simulateThrowing(pt1Monkeys, 20, true);

        // calculate 2 most active
        System.out.println("Part 1: " + monkeyBusiness(pt1Monkeys));

//        // reset for part 2
//        List<Monkey> pt2Monkeys = buildMonkeys("src/day11/test.txt");
//        simulateThrowing(pt2Monkeys, 20, false);
//
//        System.out.println("Part 2: " + monkeyBusiness(pt2Monkeys));

    }

    public static long monkeyBusiness(List<Monkey> monkeys) {
        monkeys.sort((a, b) -> {
            long diff = b.getInspectedItems() - a.getInspectedItems();
            return diff > 0 ? 1 : diff < 0 ? -1 : 0;
        });

        return monkeys.get(0).getInspectedItems() * monkeys.get(1).getInspectedItems();
    }

    public static void simulateThrowing(List<Monkey> monkeys, int rounds, boolean pt1) {
        for (int i = 0; i < rounds; i++) {
            for (Monkey m : monkeys) {
                while (m.hasItems()) {
                    int worry = m.inspectFirst(pt1);
                    monkeys.get(m.throwTo(worry)).catchItem(worry);
                }
            }
        }
    }

    public static UnaryOperator<Integer> getOperation(String operation) {
        String[] parts = operation.split(" ");

        // both halves of the operation are "old"
        boolean oldOld = parts[0].equals(parts[2]);
        return switch (parts[1]) {
            case "+" -> oldOld ? (a) -> a + a : (a) -> a + Integer.parseInt(parts[2]);
            case "*" -> oldOld ? (a) -> a * a : (a) -> a * Integer.parseInt(parts[2]);
            default -> null;
        };

    }

    public static List<Monkey> buildMonkeys(String fileName) throws FileNotFoundException {
        File input = new File(fileName);
        Scanner s = new Scanner(input);

        List<Monkey> monkeys = new ArrayList<>();

        while (s.hasNextLine()) {
            s.nextLine();
            Queue<Integer> startingItems = Arrays.stream(s.nextLine().split(": ")[1].split(", "))
                    .map(Integer::parseInt).collect(Collectors.toCollection(LinkedList<Integer>::new));
            UnaryOperator<Integer> operation = getOperation(s.nextLine().split("= ")[1]);
            int testDivisor = Integer.parseInt(s.nextLine().split("by ")[1]);
            int trueMonkey = Integer.parseInt(s.nextLine().split("monkey ")[1]);
            int falseMonkey = Integer.parseInt(s.nextLine().split("monkey ")[1]);

            Monkey m = new Monkey(operation, testDivisor, trueMonkey, falseMonkey, startingItems);
            monkeys.add(m);

            if (s.hasNextLine()) s.nextLine();
        }

        return monkeys;
    }
} 
