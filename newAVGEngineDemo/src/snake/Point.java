package snake;

public class Point {
    private int x, y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point add(Point other) {
        return (new Point(this.getX() + other.getX(), this.getY() + other.getY()));
    }

    public boolean equals(Point other) {
        return (this.getX() == other.getX() && this.getY() == other.getY());
    }

    public Point(int x, int y) {
        setX(x);
        setY(y);
    }

    @Override
    public String toString() {
        return (x + " , " + y);
    }
}

