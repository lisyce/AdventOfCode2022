package day14; 
 
import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.*; 
 
public class Main { 
    public static void main(String[] args) throws FileNotFoundException { 
        File input = new File("src/day14/test.txt"); 
        Scanner s = new Scanner(input);

        // build the grid
        // sand flows from (500, 0)
        List<List<Character>> grid = new ArrayList<>();
        grid.add(new ArrayList<>(List.of('+'))); // (500, 0)

        while (s.hasNextLine()) {
            String[] steps = s.nextLine().split(" -> ");

            for (int i = 0; i < steps.length; i++) {
                String step = steps[i];

                int[] coords = getCoords(grid, step, false);
                extendGrid(grid, coords[0], coords[1]);

                int[] relativeCoords = getCoords(grid, step, true);

                if (i == 0) {
                    // first coord, just put a # there
                    grid.get(relativeCoords[1]).set(relativeCoords[0], '#');
                } else {
                    // did the previous step change in the x or the y?
                    int[] prevRelCoords = getCoords(grid, steps[i-1], true);

                    // x -> move this.x - prev.x steps to the right (could be negative)
                    if (relativeCoords[1] == prevRelCoords[1]) { // (the y coords are the same so the x ones changed)
                        int dir = (int) Math.signum(relativeCoords[0] - prevRelCoords[0]);
                        for (int x = prevRelCoords[0]; x != relativeCoords[0]; x += dir) {
                            grid.get(relativeCoords[1]).set(x+dir, '#');
                        }
                    } else { // y
                        int dir = (int) Math.signum(relativeCoords[1] - prevRelCoords[1]);
                        for (int y = prevRelCoords[1]; y != relativeCoords[1]; y += dir) {
                            grid.get(y+1).set(relativeCoords[0], '#');
                        }
                    }

                }
                System.out.println(step);
                grid.forEach(System.out::println);
                System.out.println();

            }
        }


    }

    // example input: 503,4
    public static int[] getCoords(List<List<Character>> grid, String input, boolean relative) {
        int[] coords = Arrays.stream(input.split(",")).mapToInt(Integer::parseInt).toArray();
        if (!relative) return coords;

        int newX = coords[0] < 500 ? grid.get(0).indexOf('+') - (500 - coords[0]) : grid.get(0).indexOf('+') + (coords[0] - 500);
        return new int[]{newX, coords[1]};
    }

    public static void extendGrid (List<List<Character>> grid, int x, int y) {

        // extend vertically
        for (int i = grid.size(); i <= y; i++) {
            grid.add(new ArrayList<>());
            for (int j = 0; j < grid.get(0).size(); j++) grid.get(grid.size() - 1).add('.');
        }

        // find index of 500, 0 (the +);
        int start = grid.get(0).indexOf('+'); // 3

        for (List<Character> row : grid) {
            if (x < 500) {
                // add to the left
                for (int i = x; i < 500 - start; i++) {
                    row.add(0, '.');
                }

            } else if (x > 500) {
                // add to the right
                for (int i = row.size() - start - 1; i < x - 500; i++) {
                    row.add('.');
                }

            }
        }

    }
} 
