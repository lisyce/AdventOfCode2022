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
        int maxScore = 0;

        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                int[] data = scenicScore(grid, i, j);
                maxScore = Math.max(maxScore, data[1]);

                if (data[0] == 1) {
                    visibleTrees++;
                }
            }
        }

        System.out.println("Part 1: " + visibleTrees);
        System.out.println("Part 2: " + maxScore);
    }

    /**
     * @return an int[] with the first element as 0 if the tree is hidden or 1 if visible and
     *         the second element corresponding to the scenic score of the tree.
     */
    public static int[] scenicScore(List<List<Integer>> grid, int row, int col) {
        int[] returns = new int[2];

        boolean topVis = true;
        boolean leftVis = true;
        boolean rightVis = true;
        boolean bottomVis = true;

        int top = 0;
        int bottom = 0;
        int left = 0;
        int right = 0;

        int thisTree = grid.get(row).get(col);

        // top
        for (int i = row - 1; i >= 0; i--) {
            int comparison = grid.get(i).get(col);
            top++;
            if (comparison >= thisTree) {
                topVis = false;
                break;
            }
        }

        // bottom
        for (int i = row + 1; i < grid.size(); i++) {
            int comparison = grid.get(i).get(col);
            bottom++;
            if (comparison >= thisTree) {
                bottomVis = false;
                break;
            }
        }

        // left
        for (int i = col - 1; i >= 0; i--) {
            int comparison = grid.get(row).get(i);
            left++;
            if (comparison >= thisTree) {
                leftVis = false;
                break;
            }
        }

        // right
        for (int i = col + 1; i < grid.get(0).size(); i++) {
            int comparison = grid.get(row).get(i);
            right++;
            if (comparison >= thisTree) {
                rightVis = false;
                break;
            }
        }

        returns[0] = !topVis && !bottomVis && !leftVis && !rightVis ? 0 : 1;
        returns[1] = top * bottom * left * right;

        return returns;
    }
}