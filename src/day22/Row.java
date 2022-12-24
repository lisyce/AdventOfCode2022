package day22;

import java.util.Set;
import java.util.TreeSet;

public class Row {

    int startX;
    int endX;
    Set<Integer> wallX;

    public Row() {
        this.wallX = new TreeSet<>();
    }

    public int firstOpenTileX() {
        for (int i = startX; i <= endX; i++) {
            if (!wallX.contains(i)) return i;
        }

        return -1;
    }

    // returns whether the space is clear and within the bounds of this row
    public boolean spaceIsValid(int x) {
        return !wallX.contains(x) && startX <= x && endX >= x;
    }

    // returns -1 if the next space is not clear
    public int nextXSpace(int currX, int dir) {
        if (currX == startX && dir == -1) {
            // wrap around
            return !wallX.contains(endX) ? endX : -1;
        } else if (currX == endX && dir == 1) {
            return !wallX.contains(startX) ? startX : -1;
        }

        // just check the next space
        return !wallX.contains(currX + dir) ? currX + dir : -1;
    }

    @Override
    public String toString() {
        return "start: " + startX + ", end: " + endX + ", walls: " + wallX;
    }

}
