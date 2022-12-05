package day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("src/day05/input.txt");
        Scanner s = new Scanner(input);

        List<Stack<Character>> stacks = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            stacks.add(new Stack<>());
        }

        // parse into Stacks
        String line = s.nextLine();
        while (!line.startsWith(" 1")) {
            int stackNum = 0;

            for (int i = 0; i < line.length(); i++) {
                String crate = line.substring(i, i+3);
                if (!crate.isBlank()) {
                    char letter = crate.charAt(1);
                    stacks.get(stackNum).add(letter);
                }
                i += 3;
                stackNum++;
            }

            line = s.nextLine();
        }

        // reverse stacks to match problem
        for (Stack<Character> stack : stacks) {
            Queue<Character> temp = new LinkedList<>();
            while (!stack.isEmpty()) temp.add(stack.pop());
            while (!temp.isEmpty()) stack.push(temp.remove());
        }

        // skip blank line
        s.nextLine();

        // move crates
        while (s.hasNextLine()) {
//            System.out.println();
//            stacks.forEach(System.out::println);

            String[] instructions = s.nextLine().split(" ");
            int amount = Integer.parseInt(instructions[1]);
            int fromStack = Integer.parseInt(instructions[3]) - 1;
            int toStack = Integer.parseInt(instructions[5]) - 1;


            for (int i = 0; i < amount; i++) {
                char crate = stacks.get(fromStack).pop();
                stacks.get(toStack).push(crate);
            }
        }

        // print message
        for (Stack<Character> stack : stacks) {
            System.out.print("Part 1: ");
            if (!stack.isEmpty()) System.out.print(stack.pop());
        }



    }
}
