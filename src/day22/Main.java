package day22; 
 
import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.*;


public class Main { 
    public static void main(String[] args) throws FileNotFoundException {

        File input = new File("src/day22/input.txt");
        Scanner s = new Scanner(input);

        // build grid
        List<Row> grid = buildGrid(s);

        // follow directions
        int x = grid.get(0).firstOpenTileX();
        int y = 1;
        int xDir = 1;
        int yDir = 0;

        Scanner instructionScan = new Scanner(s.nextLine());
        instructionScan.useDelimiter("[RL]");
        // we can also use the \\d+ delim for nums. we just need to switch between them

        while (instructionScan.hasNext()) {
            String instruction = instructionScan.next();
            System.out.println(instruction);

            try {
                int dist = Integer.parseInt(instruction);

                if (xDir != 0) { // moving L or R
                    for (int i = 0; i < dist; i++) {
                        // is the next space clear?
                        int nextSpace = grid.get(y - 1).nextXSpace(x, xDir);
                        if (nextSpace == -1) break;

                        // move in the x direction
                        x = nextSpace;

//                        System.out.println(x + ", " + y);

                    }
                } else { // moving U or D

                    for (int i = 0; i < dist; i++) {
                        // is the next space clear?
                        int nextSpace = nextYSpace(grid, y, x, yDir);
                        if (nextSpace == -1) break;

                        // move in the y direction
                        y = nextSpace;

//                        System.out.println(x + ", " + y);

                    }
                }


            } catch (NumberFormatException e) {

                switch (instruction) {
                    case "L":
                        if (xDir != 0) {
                            yDir = -xDir;
                            xDir = 0;
                        } else {
                            xDir = yDir;
                            yDir = 0;
                        }
                        break;
                    case "R":
                        if (xDir != 0) {
                            yDir = xDir;
                            xDir = 0;
                        } else {
                            xDir = -yDir;
                            yDir = 0;
                        }
                        break;
                }
            }

            if (instructionScan.delimiter().toString().equals("[RL]")) {
                instructionScan.useDelimiter("\\d+");
                System.out.println(x + ", " + y);
                System.out.println();
            } else {
                instructionScan.useDelimiter("[RL]");

            }
        }

        // figure out final password
        int facingPoints = xDir == 1 ? 0 : xDir == -1 ? 2 : yDir == 1 ? 1 : 3;
        int password = (1000 * y) + (4 * x) + facingPoints;
        System.out.println("Part 1: " + password);
    }

    // returns -1 if the next space is blocked
    public static int nextYSpace(List<Row> grid, int currY, int currX, int yDir) {

        // can we just move to the next spot?
        if (!(currY == 1 && yDir == -1) && !(currY == grid.size() && yDir == 1)) {
            Row nextRow = grid.get(currY - 1 + yDir);
            if (nextRow.spaceIsValid(currX)) {
                return currY + yDir;

            } else if (nextRow.wallX.contains(currX)) { // are we just blocked by a wall? if so, stop
                return -1;
            }
        }
        // move in the opposite direction until we find the row to look for
        int originalY = currY;

        while (true) {
            if ((currY == 1 || currY == grid.size()) && originalY != currY) break;

            Row nextRow = grid.get(currY - yDir - 1);
            if ((nextRow.startX > currX || nextRow.endX < currX) && originalY != currY) break;
            currY -= yDir;
        }
        return grid.get(currY - 1).spaceIsValid(currX) ? currY : -1;

    }
    public static List<Row> buildGrid(Scanner s) {

        // build grid
        List<Row> grid = new ArrayList<>();

        String line = s.nextLine();

        do {
            // build row
            Row r = new Row();

            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);

                if (c != ' ') {
                    if (r.startX == 0) r.startX = i+1;

                    if (c == '#') r.wallX.add(i+1);
                }
            }
            r.endX = line.length();
            grid.add(r);

            line = s.nextLine();
        } while (!line.isEmpty());

        return grid;
    }
} 
