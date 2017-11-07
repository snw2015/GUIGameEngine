package snw.tests;

import snw.engine.animation.Animation;
import snw.engine.component.*;
import snw.engine.component.Button;
import snw.engine.component.Component;
import snw.engine.componentAVG.MainPanelC;
import snw.engine.database.Database;
import snw.engine.main.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;

public class ComponentTests extends TopLevelComponent {
    public static final Dimension size = new Dimension(500, 500);
    public final static int fps = 50;
    public final static int gcInterval = 5;
    public static int counter = 0;

    public ComponentTests() {
        super("testMain", 0, 0, 1680, 1050, true);        Graphic back = new Graphic("back", Color.white, new Rectangle(1680, 1050), 0, 0, false);
        add(back);



        Component[][] comps = new Component[6][2];

        for (int i = 0; i < comps.length; i++) {
            for (int j = 0; j < comps[i].length; j++) {
                comps[i][j] = new Graphic("shape " + i + "," + j, Color.black, new Arc2D.Double(0, 0, 200, 200, 0, 360, Arc2D.PIE)
                        , i * 200, j * 200, true);
            }
        }

        ListPanel lp = ListPanelFactory.getGridInstance("ListPaneA", 0, 0, 500, 400, comps);
        lp.setEffect(200,200);
        lp.setFlash(true);

        ScrollBar sb = new ScrollBar("ScrollA", getImage("scroll_back"), getImage("scroll_slider"), 0, 400, 500, 30, 30, 30);
        //add(sb);

        ScrollPanel sp = new ScrollPanel("ScrollPaneA", 100, 100, 500, 430, 1200, sb, lp);
        add(sp);
    }

    @Override
    public void start() {

    }

    public static void main(String[] s) {
        Database.loadUserData();


        ComponentTests panel = new ComponentTests();
        MainFrame frame = new MainFrame("0.0.3", panel);

        Timer timerPaint = new Timer(1000 / fps, (ActionEvent e) ->
        {
            if (frame.isRunning) {
                frame.repaint();
            }
        });
        timerPaint.start();

        Timer timerUpdate = new Timer(1000 / fps, (e) -> {
            if (frame.isRunning) {
                frame.update();
            }
        });
        timerUpdate.start();

        frame.start();
    }
}
