package snw.tests;

import snw.engine.animation.Animation;
import snw.engine.component.*;
import snw.engine.component.Button;
import snw.engine.component.Component;
import snw.engine.component.reaction.Reaction;
import snw.engine.componentAVG.MainPanelC;
import snw.engine.database.Database;
import snw.engine.main.MainFrame;
import snw.math.VectorInt;

import javax.swing.*;
import javax.xml.crypto.Data;
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
        super("testMain", 0, 0, 1680, 1050, true);
        Graphic back = new Graphic("back", Color.white, new Rectangle(1680, 1050), 0, 0, false);
        add(back);


        Component[][] comps = new Component[6][2];

        for (int i = 0; i < comps.length; i++) {
            for (int j = 0; j < comps[i].length; j++) {
                comps[i][j] = new Graphic("shape " + i + "," + j, Color.black, new Arc2D.Double(0, 0, 200, 200, 0, 360, Arc2D.PIE)
                        , i * 200, j * 200, true);
            }
        }

        ListPanel lp = ListPanelFactory.getGridInstance("ListPaneA", 0, 0, 500, 400, comps);
        lp.setEffect(200, 200);
        lp.setFlash(true);

        ScrollBar sb = new ScrollBar("ScrollA", getImage("scroll_back"), getImage("scroll_slider"), 0, 400, 500, 30, 30, 30);
        //add(sb);

        ScrollPanel sp = new ScrollPanel("ScrollPaneA", 100, 100, 500, 430, 1200, sb, lp);
        add(sp);

        Button b1 = new Button("b1", 600, 400, new Graphic("b1b", Color.blue, new Arc2D.Double(0, 0, 200, 200, 0, 360, Arc2D.PIE), 0, 0, true), "");
        //add(b1);
        b1.setReactionClicked((e) -> {
            print(e);
        });


        MovingTextBox mtb = new MovingTextBox("mtb", getImage("textbox_common"), "uwuqofuohouwheof", 600, 200, 500, 500);
        mtb.setSpeed(20);
        //add(mtb);

        MessageBox mb = new MessageBox("hi", getImage("textbox_common"), "test!", 300, 300, 300, 300, "yes");
        //add(mb);
        mb.setReactionClicked((e) -> {
            remove(mb);
            print("Yes!");
        });

        OptionBox ob = new OptionBox("ob", getImage("textbox_common"), "hihihiohoiehfq", 200, 0, 300, 800, "yes", "no");
        //add(ob);
        ob.setReactionClicked((e) -> {
            remove(ob);
            print(e);
        });

        SelectionBox sb2 = new SelectionBox("sb2", getImage("textbox_common"), "???", 20, 20, 300, 500, new String[]{"qeqwe1", "dfsfew2", "hbbvu3"});
        //add(sb2);
        sb2.setReactionClicked((e) -> {
            print(e);
        });

        SetSwitch ss = new SetSwitch("ss", 200, 400, 200, 200, new String[]{"1", "2", "3"});
        add(ss);
    }

    @Override
    public void start() {

    }

    public static void main(String[] s) {
        Database.loadUserData();


        ComponentTests panel = new ComponentTests();
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        MainFrame frame = new MainFrame("0.0.3", panel, false);

        device.setFullScreenWindow(frame);

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
