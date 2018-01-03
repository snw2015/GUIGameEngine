package snw.engine.frame;

import snw.engine.animation.AnimationData;
import snw.engine.component.TopLevelComponent;
import snw.engine.core.Engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class EngineFrame extends JFrame {
    final TopLevelComponent panel = Engine.getPanel();
    final ContentPanel contentPanel = new ContentPanel();

    public EngineFrame() {
        add(contentPanel);
        resize();
        //TODO
        setCursor(getToolkit().createCustomCursor(
                new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(), null));
        panel.setCursor(true);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                Engine.runNewThread(() -> {
                    Engine.exit();
                });
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                Engine.runNewThread(() -> {
                    panel.keyTyped(e.getKeyChar());
                });
            }

            @Override
            public void keyPressed(KeyEvent e) {
                Engine.runNewThread(() -> {
                    panel.keyPressed(e.getKeyCode());
                });
            }

            @Override
            public void keyReleased(KeyEvent e) {
                Engine.runNewThread(() -> {
                    panel.keyReleased(e.getKeyCode());
                });
            }
        });
    }

    private class ContentPanel extends JPanel {
        public ContentPanel() {
            this.setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight()));
            this.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    Engine.runNewThread(() -> {
                        panel.mouseClicked(e.getX(), e.getY());
                    });
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    Engine.runNewThread(() -> {
                        panel.mousePressed(e.getX(), e.getY());
                    });
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    Engine.runNewThread(() -> {
                        panel.mouseReleased(e.getX(), e.getY());
                    });
                }

                @Override
                public void mouseEntered(MouseEvent e) {

                    Engine.runNewThread(() -> {
                        panel.mouseEntered();
                    });
                }

                @Override
                public void mouseExited(MouseEvent e) {

                    Engine.runNewThread(() -> {
                        panel.mouseExited();
                    });
                }
            });
            this.addMouseMotionListener(new MouseMotionListener() {

                @Override
                public void mouseMoved(MouseEvent e) {

                    Engine.runNewThread(() -> {
                        panel.mouseMoved(e.getX(), e.getY());
                    });
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    Engine.runNewThread(() -> {
                        panel.mouseDragged(e.getX(), e.getY());
                    });
                }
            });
        }

        @Override
        public void paint(Graphics g) {
            if (panel != null) {
                panel.render((Graphics2D) g, new AnimationData(AffineTransform.getTranslateInstance(0, 0)));
            }
        }

        public void resize() {
            this.setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight()));
        }
    }

    public void resize() {
        contentPanel.resize();
        setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight()));
        pack();
        setLocationRelativeTo(null);
    }
}