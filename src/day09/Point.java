package day09;

import java.util.Objects;

public class Point {
    public int x;
    public int y;
    public Point following;

    public Point(Point following) {
        this(0, 0, following);
    }

    public Point(int x, int y, Point following) {
        this.x = x;
        this.y = y;
        this.following = following;
    }


    public Point copy() {
        return new Point(x, y, null);
    }

    public boolean touching(Point other) {
        return (Math.abs(other.x - this.x) <= 1 && Math.abs(other.y - this.y) <= 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point point)) return false;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
