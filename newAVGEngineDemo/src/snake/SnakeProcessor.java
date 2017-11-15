package snake;

import java.util.LinkedList;
import java.util.Random;

public class SnakeProcessor {
    public final static int INITIAL_LENGTH = 5;
    public final static int GROUND = 0;
    public final static int BODY = 1;
    public final static int HEAD = 2;
    public final static int FOOD = 3;

    private final int m, n;
    private final SnakeScreen screen;
    private LinkedList<Point> body = new LinkedList<>();
    private Point[] foodPos;
    private Direction direction;
    private Direction newDirection;

    public SnakeProcessor(int m, int n, int foodNum, SnakeScreen screen) {
        this.m = m;
        this.n = n;
        foodPos = new Point[foodNum];
        this.screen = screen;
        if (n <= 5 || m <= 5) {
            System.out.println("The size of the map is too small.");
        }
    }

    public void initialize() {
        for (int i = 0; i < INITIAL_LENGTH; i++) {
            extendBody(i, 0);
        }
        direction = Direction.RIGHT;
        newDirection = direction;
        generateFoods();
    }

    public void process() {
        direction = newDirection;
        move();
        if (isGameOver()) {
            screen.gameOver();
            System.exit(0);
        } else {
            screen.printScreen(getMap());
        }
    }

    private int[][] getMap() {
        int[][] map = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = GROUND;
            }
        }
        for (Point point : body) {
            if (!isOutOfBound(point)) {
                map[point.getX()][point.getY()] = BODY;
            }
        }
        map[body.getLast().getX()][body.getLast().getY()] = HEAD;
        for (Point foodPoint : foodPos) {
            map[foodPoint.getX()][foodPoint.getY()] = FOOD;
        }
        return map;
    }

    private void generateFoods() {
        for (int i = 0; i < foodPos.length; i++) {
            generateFood(i);
        }
    }

    private void generateFood(int k) {
        int rndRange = m * n - body.size() - k;
        Random rnd = new Random();
        int foodIndex = rnd.nextInt(rndRange);

        boolean[][] map = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = true;
            }
        }
        for (Point point : body) {
            if (!isOutOfBound(point)) {
                map[point.getX()][point.getY()] = false;
            }
        }

        for (int i = 0; i < k; i++) {
            if (!isOutOfBound(foodPos[i])) {
                map[foodPos[i].getX()][foodPos[i].getY()] = false;
            }
        }

        int posAvailable = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j]) {
                    if (posAvailable++ >= foodIndex) {
                        foodPos[k] = new Point(i, j);
                        return;
                    }
                }
            }
        }
    }


    private void move() {
        Point head = body.getLast();
        Point newHead = null;

        switch (direction) {
            case LEFT:
                newHead = head.add(new Point(-1, 0));
                break;
            case RIGHT:
                newHead = head.add(new Point(1, 0));
                break;
            case UP:
                newHead = head.add(new Point(0, -1));
                break;
            case DOWN:
                newHead = head.add(new Point(0, 1));
        }

        extendBody(newHead);

        if (eat(newHead)) {
            generateFoods();
        } else {
            shortenBody();
        }
    }

    private boolean eat(Point newHead) {
        for (Point pos : foodPos) {
            if (pos.equals(newHead)) {
                return true;
            }
        }
        return false;
    }

    private boolean isGameOver() {
        if (isOutOfBound(body.getLast())) {
            return (true);
        } else if (isCollision(body.getLast())) {
            return (true);
        }
        return (false);
    }

    private boolean isCollision(Point point) {
        for (Point bodyPoint : body) {
            if (point != bodyPoint && point.equals(bodyPoint)) {
                return (true);
            }
        }
        return (false);
    }

    private boolean isOutOfBound(Point point) {
        int x = point.getX();
        int y = point.getY();
        return (x < 0 || x >= m || y < 0 || y >= n);
    }

    private void extendBody(int x, int y) {
        extendBody(new Point(x, y));
    }

    private void extendBody(Point point) {
        body.addLast(point);
    }

    private Point shortenBody() {
        return (body.removeFirst());
    }

    public boolean changeDirection(Direction newDirection) {

        switch (direction) {
            case LEFT:
                if (newDirection != Direction.RIGHT) {
                    this.newDirection = newDirection;
                    return (true);
                }
                return (false);
            case RIGHT:
                if (newDirection != Direction.LEFT) {
                    this.newDirection = newDirection;
                    return (true);
                }
                return (false);
            case UP:
                if (newDirection != Direction.DOWN) {
                    this.newDirection = newDirection;
                    return (true);
                }
                return (false);
            case DOWN:
                if (newDirection != Direction.UP) {
                    this.newDirection = newDirection;
                    return (true);
                }
                return (false);
        }
        return (false);
    }
}