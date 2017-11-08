package snw.tests;

import snw.file.FileDirectReader;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class TestScreen extends JFrame {
    Image imageTest = FileDirectReader.getImage("file/image/background_main_menu.png");
    int counter = 0;

    public TestScreen() {
        System.out.println(Toolkit.getDefaultToolkit().getScreenSize());
        this.setSize(200, 200);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        while (true) {
            repaint();
            counter = (counter + 1) % 10;

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));

        BufferedImage buffy = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);

        long t = System.currentTimeMillis();
        buffy.getGraphics().drawImage(imageTest, 0, 0, null);
        print("Paint first, cost: " + (System.currentTimeMillis() - t));
        t = System.currentTimeMillis();
        buffy.getGraphics().drawImage(imageTest, 0, 0, null);
        print("Paint second, cost: " + (System.currentTimeMillis() - t));
        t = System.currentTimeMillis();
        buffy.getGraphics().drawImage(imageTest, 0, 0, null);
        print("Paint third, cost: " + (System.currentTimeMillis() - t));
/**
        for (int i = 0; i < 20; i++) {
            AffineTransform transform = AffineTransform.getTranslateInstance(counter, 20);
            transform.concatenate(AffineTransform.getRotateInstance(0.1 * i));
            t = System.currentTimeMillis();
            g2d.drawImage(imageTest, transform, null);
            print("Paint " + i + ", cost: " + (System.currentTimeMillis() - t));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
*/
        t = System.currentTimeMillis();
        g2d.drawImage(imageTest, 0, 0, null);
        print("Paint final, cost: " + (System.currentTimeMillis() - t));
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        new TestScreen();
    }

    public static void print(Object s) {
        System.out.println(s);
    }
}