package snw.engine.main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import snw.engine.componentAVG.MainPanelC;
import snw.engine.database.Database;
import snw.engine.database.UserConfig;

public class Launcher {
    public final static int fps = 50;
    public final static int gcInterval = 5;
    public static int counter = 0;

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");

        Database.loadUserData();


        MainPanelC panel = new MainPanelC();
        MainFrame frame = new MainFrame("0.0.3", panel,true);
        frame.setCursor(frame.getToolkit().createCustomCursor(
                new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(), null));

        Timer timerPaint = new Timer(1000 / fps, (e) ->
        {
            if (frame.isRunning) {
                //long t = System.currentTimeMillis();
                frame.repaint();
                //System.out.println("Paint cost: " + (System.currentTimeMillis() - t)+"ms");
            }
        });
        timerPaint.start();

        Timer timerUpdate = new Timer(1000 / fps, (e) -> {
            if (frame.isRunning) {
                frame.update();

                //if(counter++>=gcInterval){
                //	System.gc();
                //	counter=0;
                //}
            }
        });
        timerUpdate.start();

        frame.start();

        frame.setEnabled(true);
        frame.setVisible(true);
    }
}