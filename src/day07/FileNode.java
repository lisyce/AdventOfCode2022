package day07;

import java.util.*;

public class FileNode implements Comparable<FileNode> {
    final FileNode parent;

    final List<FileNode> subNodes;
    int size;
    String name;
    boolean isDirectory;

    public FileNode(String name, int size, FileNode parent, boolean isDirectory) {
        this.subNodes = new ArrayList<>();
        this.name = name;
        this.size = size;
        this.parent = parent;
        this.isDirectory = isDirectory;

        // adjust the size of all parent Nodes
        FileNode pointer = this.parent;
        while (pointer != null) {
            pointer.size += this.size;
            pointer = pointer.parent;
        }
    }

    public FileNode(String name, FileNode parent, boolean isDirectory) {
        this(name, 0, parent, isDirectory);
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

    @Override
    public int compareTo(FileNode o) {
        return this.name.compareTo(o.name);
    }
}
