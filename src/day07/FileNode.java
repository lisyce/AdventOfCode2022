package day07;

import java.util.ArrayList;
import java.util.List;

public class FileNode {
    final FileNode parent;

    final List<FileNode> subNodes;
    int size;
    String name;

    public FileNode(String name, int size, FileNode parent) {
        this.subNodes = new ArrayList<>();
        this.name = name;
        this.size = size;
        this.parent = parent;

        // adjust the size of all parent Nodes
        FileNode pointer = this.parent;
        while (pointer != null) {
            pointer.size += this.size;
            pointer = pointer.parent;
        }
    }

    public FileNode(String name, FileNode parent) {
        this(name, 0, parent);
    }

    public FileNode findSubNode(String name) {
        for (FileNode node : subNodes) {
            if (node.name.equals(name)) return node;
        }

        return null;
    }

    public String toString() {
        return this.name + ": " + this.size;
    }
}
