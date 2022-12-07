package day07; 
 
import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.*; 
 
public class Main { 
   public static void main(String[] args) throws FileNotFoundException {
       File input = new File("src/day07/test.txt");
       Scanner s = new Scanner(input);

       FileNode root = new FileNode("/", null);
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
                   FileNode subNode = new FileNode(toDir, pointer);
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
                   subNode = new FileNode(halves[1], pointer);
               } else {
                   // file
                   int size = Integer.parseInt(halves[0]);
                   subNode = new FileNode(halves[1], size, pointer);
               }
               pointer.subNodes.add(subNode);
           }
       }

   } 
} 
