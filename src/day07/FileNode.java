package day07;

import java.util.ArrayList;
import java.util.List;

public class FileNode {
    FileNode parent;

    final List<FileNode> subNodes;
    int size;
    String name;

    public FileNode(String name, int size) {
        this.subNodes = new ArrayList<>();
        this.name = name;
        this.size = size;
        this.parent = null;
    }

    public FileNode(String name) {
        this(name, 0);
    }

    public FileNode findSubNode(String name) {
        for (FileNode node : subNodes) {
            if (node.name.equals(name)) return node;
        }

        return null;
    }

    public String toString() {
        return this.name;
    }
}
