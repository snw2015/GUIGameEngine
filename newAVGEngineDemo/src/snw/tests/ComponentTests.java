package snw.tests;

import snw.engine.component.*;
import snw.engine.component.Button;
import snw.engine.componentAVG.MainPanelC;
import snw.engine.database.Database;
import snw.engine.main.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ComponentTests extends TopLevelComponent {
    public static final Dimension size = new Dimension(500, 500);
    public final static int fps = 50;
    public final static int gcInterval = 5;
    public static int counter = 0;

    public ComponentTests(JFrame frame) {
        super("testMain", 0, 0, 1680, 1050, frame);
        add(new Graphic("back", Color.white, new Rectangle(1680, 1050), 0, 0, false));
        add(new ScrollBar("ScrollA", getImage("scroll_back"), getImage("scroll_slider"), 100, 100, 200, 30, 30, 30));
    }

    @Override
    public void start() {

    }

    public static void main(String[] s) {
        Database.loadUserData();

        MainFrame frame = new MainFrame("0.0.3");
        ComponentTests panel = new ComponentTests(frame);
        frame.setMainPanel(panel);

        Timer timerPaint = new Timer(1000 / fps, (ActionEvent e) ->
        {
            if (frame.isRunning) {
                frame.repaint();
                frame.getComponentGraphic();

                if (counter++ >= gcInterval) {
                    System.gc();
                    counter = 0;
                }
            }
        });
        timerPaint.start();

        frame.start();
    }
}
