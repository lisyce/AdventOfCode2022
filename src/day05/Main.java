package day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("src/day05/input.txt");
        Scanner s = new Scanner(input);

        List<Stack<Character>> ptOneStacks = new ArrayList<>();
        List<Stack<Character>> ptTwoStacks = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            ptOneStacks.add(new Stack<>());
            ptTwoStacks.add(new Stack<>());
        }

        // parse into Stacks
        String line = s.nextLine();
        while (!line.startsWith(" 1")) {
            int stackNum = 0;

            for (int i = 0; i < line.length(); i++) {
                String crate = line.substring(i, i+3);
                if (!crate.isBlank()) {
                    char letter = crate.charAt(1);
                    ptOneStacks.get(stackNum).add(letter);
                }
                i += 3;
                stackNum++;
            }

            line = s.nextLine();
        }

        // reverse stacks to match problem
        for (int i = 0; i < ptOneStacks.size(); i++) {
            Stack<Character> ptOneStack = ptOneStacks.get(i);
            Stack<Character> ptTwoStack = ptTwoStacks.get(i);

            Queue<Character> temp = new LinkedList<>();
            while (!ptOneStack.isEmpty()) temp.add(ptOneStack.pop());
            while (!temp.isEmpty()) {
                char removed = temp.remove();
                ptOneStack.push(removed);
                ptTwoStack.push(removed);
            }
        }

        // skip blank line
        s.nextLine();

        // move crates
        while (s.hasNextLine()) {
            String[] instructions = s.nextLine().split(" ");
            int amount = Integer.parseInt(instructions[1]);
            int fromStack = Integer.parseInt(instructions[3]) - 1;
            int toStack = Integer.parseInt(instructions[5]) - 1;

            Stack<Character> temp = new Stack<>();

            for (int i = 0; i < amount; i++) {
                char ptOneCrate = ptOneStacks.get(fromStack).pop();
                char ptTwoCrate = ptTwoStacks.get(fromStack).pop();
                temp.push(ptTwoCrate);
                ptOneStacks.get(toStack).push(ptOneCrate);
            }

            while (!temp.isEmpty()) {
                ptTwoStacks.get(toStack).push(temp.pop());
            }

//            System.out.println();
//            ptTwoStacks.forEach(System.out::println);
        }

        // print message
        System.out.print("Part 1: ");
        for (Stack<Character> stack : ptOneStacks) {
            if (!stack.isEmpty()) System.out.print(stack.pop());
        }

        System.out.print("\nPart 2: ");
        for (Stack<Character> stack : ptTwoStacks) {
            if (!stack.isEmpty()) System.out.print(stack.pop());
        }
    }
}
