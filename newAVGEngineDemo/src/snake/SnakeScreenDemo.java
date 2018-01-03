package snake;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import snw.engine.component.*;
import snw.engine.component.Component;

public class SnakeScreenDemo extends TopLevelComponent implements SnakeScreen {

    public SnakeScreenDemo(String name, int x, int y, int width, int height, int m, int n) {
        super(name, x, y, width, height);
    }

    @Override
    public void printScreen(int[][] map) {
        clearScreen();
        int blockWidth = width / map.length;
        int blockHeight = height / map[0].length;
        Rectangle recBlock = new Rectangle(blockWidth, blockHeight);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                Color color = getColor(map[i][j]);
                add(new Graphic("label " + i + "," + j, color, recBlock, i * blockWidth,
                        j * blockHeight, false));
            }
        }
    }

    private void clearScreen() {
        removeAll();
    }

    private Color getColor(int index) {
        switch (index) {
            case SnakeProcessor.GROUND:
                return (Color.WHITE);
            case SnakeProcessor.BODY:
                return (Color.CYAN);
            case SnakeProcessor.HEAD:
                return (Color.BLUE);
            case SnakeProcessor.FOOD:
                return (Color.YELLOW);
        }
        return Color.BLACK;
    }

    @Override
    public void gameOver() {
        System.out.println("Game Over");
    }
}