package snake;

import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import snw.engine.database.Database;
import snw.engine.main.MainFrame;

public class Launcher {
    public final static int fps = 20;
    public final static int gcInterval = 10;
    public static int counter = 0;

    public static void main(String[] s) {
        Database.loadUserData();

        SnakeScreenDemo panel = new SnakeScreenDemo("panel", 0, 0, 500, 500, 20, 20);
        MainFrame frame = new MainFrame("Snake", panel, true);

        SnakeProcessor processor = new SnakeProcessor(20, 20, 3, panel);
        processor.initialize();
        frame.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyPressed(KeyEvent e) {
                Direction direction = Direction.LEFT;

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        direction = Direction.LEFT;
                        break;
                    case KeyEvent.VK_RIGHT:
                        direction = Direction.RIGHT;
                        break;
                    case KeyEvent.VK_UP:
                        direction = Direction.UP;
                        break;
                    case KeyEvent.VK_DOWN:
                        direction = Direction.DOWN;
                }
                processor.changeDirection(direction);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub

            }
        });

        Timer timerPaint = new Timer(1000 / fps, (ActionEvent e) -> {
            if (frame.isRunning) {
                frame.repaint();
            }
        });
        timerPaint.start();

        Timer timerProcess = new Timer(1000 / fps, (ActionEvent e) -> {
            if (frame.isRunning) {
                processor.process();
                frame.update();
            }
        });
        timerProcess.start();

        frame.start();
    }
}
