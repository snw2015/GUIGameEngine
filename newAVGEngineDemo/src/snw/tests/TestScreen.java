package snw.tests;

import snw.file.FileDirectReader;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class TestScreen extends JFrame {
    Image imageTest = FileDirectReader.getImage("file/image/background_main_menu.png");
    BufferedImage buffy;
    int counter = 0;

    public TestScreen() {
        System.out.println(Toolkit.getDefaultToolkit().getScreenSize());
        this.setSize(200, 200);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        System.out.println(imageTest.getHeight(null));


        BufferedImage iBuffer = new BufferedImage(imageTest.getWidth(null), imageTest.getHeight(null), BufferedImage.TYPE_4BYTE_ABGR);

        long t1 = System.currentTimeMillis();
        iBuffer.getGraphics().drawImage(imageTest, 0, 0, this);
        System.out.println("ib time cost: " + (System.currentTimeMillis() - t1) + "ms");

        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        GraphicsConfiguration config = device.getDefaultConfiguration();
        buffy = config.createCompatibleImage(iBuffer.getWidth(), iBuffer.getHeight(), iBuffer.getTransparency());

        long t2 = System.currentTimeMillis();
        buffy.getGraphics().drawImage(iBuffer, 0, 0, null);
        System.out.println("ib time cost: " + (System.currentTimeMillis() - t2) + "ms");


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

        if (buffy != null) {
            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(Color.black);
            g2d.fillRect(0, 0, getWidth(), getHeight());

            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));

            g2d.drawImage(buffy, 0, 0, null);

            for (int i = 0; i < 20; i++) {
                AffineTransform transform = AffineTransform.getTranslateInstance(counter, 20);
                transform.concatenate(AffineTransform.getRotateInstance(0.1 * i));
                g2d.drawImage(buffy, transform, null);
            }
        }
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        new TestScreen();
    }
}