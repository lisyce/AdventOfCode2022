package day11;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.UnaryOperator;

public class Monkey {
    private UnaryOperator<Integer> operation;
    private int testDivisor;
    private Queue<Integer> heldItems;
    private int trueMonkey;
    private int falseMonkey;
    private long inspectedItems;

    // operation param takes in an item and returns your worry level after the monkey inspects it
    public Monkey(UnaryOperator<Integer> operation, int testDivisor, int trueMonkey, int falseMonkey, Queue<Integer> heldItems) {
        this.operation = operation;
        this.testDivisor = testDivisor;
        this.heldItems = heldItems;
        this.trueMonkey = trueMonkey;
        this.falseMonkey = falseMonkey;
        this.inspectedItems = 0;
    }

    public boolean hasItems() {
        return !heldItems.isEmpty();
    }

    // returns the new worry level of the item after the monkey inspects it but before it throws it
    public int inspectFirst(boolean pt1) {
        int item = heldItems.remove();
        inspectedItems++;

        return pt1 ? operation.apply(item) / 3 : operation.apply(item);
    }

    // returns which monkey this monkey should throw the given item to
    public int throwTo(int worry) {
        return worry % testDivisor == 0 ? trueMonkey : falseMonkey;
    }

    public void catchItem(int worry) {
        heldItems.add(worry);
    }

    public long getInspectedItems() {
        return inspectedItems;
    }

    public String toString() {
        return heldItems.toString();
    }

}
