package day14; 
 
import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.*; 
 
public class Main { 
    public static void main(String[] args) throws FileNotFoundException {
        // build the grid
        List<List<Character>> grid = buildGrid("src/day14/input.txt");

        // simulate the falling sand
        // if the sand reaches the bottom of the grid, the unit before this one was the last unit of sand
        int maxUnits = 0;
        while (true) {
            if (!simulateSand(grid)) maxUnits++;
            else break;
        }

        System.out.println("Part 1: " + maxUnits);


    }

    // simulates the full path of one unit of sand
    // returns whether the sand fell off the map
    public static boolean simulateSand(List<List<Character>> grid) {
        int[] sandCoords = getCoords(grid, "500,0", true);

        boolean offMap = false;

        while(true) {
            // is the sand in the last row of the grid?
            if (sandCoords[1] == grid.size() - 1) {
                offMap = true;
                break;
            }

            // can the sand fall down one step?
            if (grid.get(sandCoords[1] + 1).get(sandCoords[0]) == '.') {
                // fall down one step
                sandCoords[1]++;
            } else if (grid.get(sandCoords[1] + 1).get(sandCoords[0] - 1) == '.') { // can the sand go diagonally to the left?
                sandCoords[0]--;
                sandCoords[1]++;
            } else if (grid.get(sandCoords[1] + 1).get(sandCoords[0] + 1) == '.') { // can it go diagonally to the right?
                sandCoords[0]++;
                sandCoords[1]++;
            } else break;
        }
        grid.get(sandCoords[1]).set(sandCoords[0], 'o');
        return offMap;
    }

    public static List<List<Character>> buildGrid(String fileName) throws FileNotFoundException {
        File input = new File(fileName);
        Scanner s = new Scanner(input);

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
                            grid.get(y+dir).set(relativeCoords[0], '#');
                        }
                    }

                }
            }
        }

        // give the right, left, and bottom one unit of padding
        int startX = grid.get(0).indexOf('+');
        extendGrid(grid, 500 - startX - 1, grid.size());
        extendGrid(grid, 500 + grid.get(0).size() - startX - 1, 0);

        return grid;
    }

    // example input: 503,4
    public static int[] getCoords(List<List<Character>> grid, String input, boolean relative) {
        int[] coords = Arrays.stream(input.split(",")).mapToInt(Integer::parseInt).toArray();
        if (!relative) return coords;

        int relX = coords[0] < 500 ? grid.get(0).indexOf('+') - (500 - coords[0]) : grid.get(0).indexOf('+') + (coords[0] - 500);
        return new int[]{relX, coords[1]};
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
