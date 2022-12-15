package day08; 
 
import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.*;

public class Main { 
   public static void main(String[] args) throws FileNotFoundException { 
       File input = new File("src/day08/input.txt");
       Scanner s = new Scanner(input); 
       List<List<Integer>> grid = new ArrayList<>();

       // get grid
       while (s.hasNextLine()) {
           String[] line = s.nextLine().split("");
           List<Integer> nums = Arrays.stream(line).mapToInt(Integer::parseInt).boxed().toList();
           grid.add(nums);
       }

       int visibleTrees = 0;

       for (int i = 0; i < grid.size(); i++) {
           for (int j = 0; j < grid.get(i).size(); j++) {
               if (!isHidden(grid, i, j)) {
//                   System.out.println(i + ", " + j);
                   visibleTrees++;
               }
           }
       }

       System.out.println("Part 1: " + visibleTrees);

   }

   // just need to find one tree at least as tall in all directions
   public static boolean isHidden(List<List<Integer>> grid, int row, int col) {
       int thisTree = grid.get(row).get(col);
       boolean topVis = true;
       boolean leftVis = true;
       boolean rightVis = true;
       boolean bottomVis = true;

       // top
       for (int i=row-1; i >= 0; i--) {
           int comparison = grid.get(i).get(col);
           if (comparison >= thisTree) {
               topVis = false;
               break;
           }
       }

       // bottom
       for (int i=row+1; i < grid.size(); i++) {
           int comparison = grid.get(i).get(col);
           if (comparison >= thisTree) {
               bottomVis = false;
               break;
           }
       }

       // left
       for (int i=col-1; i >= 0; i--) {
           int comparison = grid.get(row).get(i);
           if (comparison >= thisTree) {
               leftVis = false;
               break;
           }
       }

       // right
       for (int i=col+1; i < grid.get(0).size(); i++) {
           int comparison = grid.get(row).get(i);
           if (comparison >= thisTree) {
               rightVis = false;
               break;
           }
       }

       return !topVis && !bottomVis && !leftVis && !rightVis;
   }

}
