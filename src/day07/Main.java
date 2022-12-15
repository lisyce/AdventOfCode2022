package day07; 
 
import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.*; 
 
public class Main { 
   public static void main(String[] args) throws FileNotFoundException {
       File input = new File("src/day07/input.txt");
       Scanner s = new Scanner(input);

       FileNode root = new FileNode("/", null, true);
       FileNode pointer = root;

       // build the graph
       s.nextLine();
       while (s.hasNextLine()) {
           String line = s.nextLine();
           if (line.startsWith("$ cd")) {
               String toDir = line.split(" ")[2];
               if (toDir.equals("..")) {
                   pointer = pointer.parent;
                   continue;
               }

               // does the pointer have this subnode already?
               FileNode toDirSubNode = pointer.findSubNode(toDir);
               if (toDirSubNode != null) {
                   // already has this node
                   pointer = toDirSubNode;
               } else {
                   // create the node
                   FileNode subNode = new FileNode(toDir, pointer, true);
                   pointer.subNodes.add(subNode);
                   pointer = subNode;
               }

           } else if (line.startsWith("$ ls")) continue;
           else {
               // it's a file or folder
               String[] halves = line.split(" ");
               FileNode subNode;
               if (halves[0].equals("dir")) {
                   // subfolder
                   subNode = new FileNode(halves[1], pointer, true);
               } else {
                   // file
                   int size = Integer.parseInt(halves[0]);
                   subNode = new FileNode(halves[1], size, pointer, false);
               }
               pointer.subNodes.add(subNode);
           }
       }

       // search for directories with size <= 100000
       Set<FileNode> ptOneNodes = searchPtOne(root);
       System.out.println(ptOneNodes);
       int ptOneTotal = ptOneNodes.stream().mapToInt(x -> x.size).sum();
       System.out.println("Part 1: " + ptOneTotal);
   }

   public static Set<FileNode> searchPtOne(FileNode root) {
       Set<FileNode> smallNodes = new TreeSet<>();

       if (root.size <= 100000 && root.isDirectory) {
           smallNodes.add(root);
       }

       for (FileNode child : root.subNodes) {
           smallNodes.addAll(searchPtOne(child));
       }

       return smallNodes;
   }
} 
