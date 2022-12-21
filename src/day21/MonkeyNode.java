package day21;

import java.util.Optional;

public class MonkeyNode {
    String id;
    Optional<Long> value;

    MonkeyNode left;
    MonkeyNode right;
    String operation;

    public MonkeyNode(String id, String operation) {
        this.id = id;
        this.operation = operation;

        try {
            this.value = Optional.of(Long.parseLong(operation));
        } catch (NumberFormatException e) {
            this.value = Optional.empty();
        }
    }

    // assumes all the children are linked properly and there is a bottom of the tree with nodes that just hold numbers
    public long updateValue() {
        if (value.isEmpty()) {
            long result = switch (operation.split(" ")[1]) {
                case "+" -> left.updateValue() + right.updateValue();
                case "-" -> left.updateValue() - right.updateValue();
                case "*" -> left.updateValue() * right.updateValue();
                case "/" -> left.updateValue() / right.updateValue();
                default -> 0;
            };

            value = Optional.of(result);
        }

        return value.get();
    }

    public String[] childIDs() {

        String[] ids = new String[2];
        String[] parts = operation.split(" ");
        if (parts.length == 1) return null; // signals that this node just contains an int

        ids[0] = parts[0];
        ids[1] = parts[2];

        return ids;
    }

    @Override
    public String toString() {
        return id + ": " + operation + " (" + value + ")";
    }
}
