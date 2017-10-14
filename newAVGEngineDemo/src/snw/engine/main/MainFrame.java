package snw.engine.main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;
import javax.swing.text.AbstractDocument;

import snw.engine.component.Component;
import snw.engine.component.TopLevelComponent;
import snw.engine.componentAVG.MainPanelC;

public class MainFrame extends JFrame {
    public boolean isRunning = true;
    public double fps = 60.0;

    private TopLevelComponent panel = null;
    private JPanel contentPanel = null;
    private Image image = null;

    class ContentPanel extends JPanel
    {
        public ContentPanel()
        {
            this.setPreferredSize(new Dimension(1680,1050));
            this.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    panel.mouseClicked(e.getX(), e.getY());

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    panel.mousePressed(e.getX(), e.getY());
                }

                @Override
                public void mouseReleased(MouseEvent e) {

                    panel.mouseReleased(e.getX(), e.getY());
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    panel.mouseEntered();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    panel.mouseExited();
                }
            });
            this.addMouseMotionListener(new MouseMotionListener() {

                @Override
                public void mouseMoved(MouseEvent e) {
                    panel.mouseMoved(e.getX(), e.getY());
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    panel.mouseDragged(e.getX(), e.getY());
                }
            });
        }

        public void paint(Graphics g) {
            if (image != null) {
                g.drawImage(image, 0, 0, this);
            }
        }
    }

    public MainFrame(String title) {
        this.setTitle(title);

        this.panel = new MainPanelC(this);

        contentPanel = new ContentPanel();
        this.add(contentPanel);
        this.pack();
        this.setLocationRelativeTo(null);

        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                panel.keyTyped(e.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {
                panel.keyPressed(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {

                panel.keyReleased(e.getKeyCode());
            }
        });

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        image = createImage(contentPanel.getWidth(), contentPanel.getHeight());
    }

    public void setMainPanel(TopLevelComponent panel)
    {
        this.panel = panel;
    }

    public void getComponentGraphic() {
        panel.render(image.getGraphics());
        image.flush();
    }

    public static void print(String s) {
        System.out.println(s);
    }

    public void start() {
        panel.start();
    }
}
