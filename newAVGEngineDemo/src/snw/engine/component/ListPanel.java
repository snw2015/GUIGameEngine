package snw.engine.component;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashMap;

import snw.engine.animation.Animation;

/*
 * remove method is not available in this component
 */
public class ListPanel extends FrameComponent {
    private HashMap<Component, Component[]> successor = new HashMap<Component, Component[]>();
    private Animation flash = new Animation(100, "file/flash.anm");
    private boolean isFlashing = false;
    private Color color = Color.WHITE;
    private Graphic effect = null;
    private float effectAlpha = 1.0f;

    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;

    public ListPanel(String name, int x, int y, int width, int height) {
        super(name, x, y, width, height);
        successor.put(null, new Component[]{null, null, null, null});
        // TODO Auto-generated constructor stub
    }

    @Override
    public void add(Component sub) {
        super.add(sub);
        if (successor.get(sub) == null) {
            successor.put(sub, new Component[]{null, null, null, null});
        }
    }

    public void add(Component sub, Component[] sucs) {
        super.add(sub);
        setSuccess(sub, sucs);
    }

    public void add(Component sub, int direction, Component suc) {
        super.add(sub);
        setSuccess(sub, direction, suc);
    }

    @Override
    public boolean remove(String name) {
        return (false);
    }

    @Override
    public boolean remove(Component sub) {
        return (false);
    }

    public void setSuccess(Component sub, int direction, Component sub2) {
        if (!successor.containsKey(sub)) {
            successor.put(sub, new Component[]{null, null, null, null});
        }
        successor.get(sub)[direction] = sub2;
    }

    public void setSuccess(Component sub, Component[] sucs) {
        successor.put(sub, sucs);
    }

    public Component[] getSuccess(Component sub) {
        return (successor.get(sub));
    }

    public Component getSuccess(Component sub, int direction) {
        return (successor.get(sub)[direction]);
    }

    public boolean move(int direction) {
        Component suc = successor.get(componentFocus)[direction];

        if (suc != null) {
            if (componentFocus != null) {
                componentFocus.mouseExited();
            }
            suc.mouseEntered();

            componentFocus = suc;
            resetEffect();
            return (true);
        } else {
            return (false);
        }
    }

    public void setFlash(boolean isFlashing) {
        this.isFlashing = isFlashing;
        if (effect != null) {
            if (isFlashing) {
                effect.setAnimation(flash);
            } else {
                effect.setAnimation(null);
            }
        }
    }

    public void setFlashColor(Color flashColor) {
        if (flashColor != null) {
            color = flashColor;
        }
        if (effect != null) {
            effect.setColor(flashColor);
        }
    }

    public void setEffectAlpha(float alpha) {
        effectAlpha = alpha;
    }

    public void setEffect(Shape shape) {
        if (effect != null) {
            remove(effect);
        }
        effect = new Graphic(name + "_effect", color, shape, 0, 0, false);
        this.add(effect);
        resetEffect();
    }

    public void setEffect(int width, int height) {
        if (effect != null) {
            remove(effect);
        }
        effect = new Graphic(name + "_effect", color, new Rectangle(0, 0, width, height),
                0, 0, false);
        add(effect);
        resetEffect();
    }

    public void setEffect(Shape shape, Color color) {
        if (effect != null) {
            remove(effect);
        }
        setFlashColor(color);
        effect = new Graphic(name + "_effect", color, shape, 0, 0, false);
        add(effect);
        resetEffect();
    }

    public void setEffect(int width, int height, Color color) {
        if (effect != null) {
            remove(effect);
        }
        setFlashColor(color);
        effect = new Graphic(name + "_effect", color, new Rectangle(0, 0, width, height),
                0, 0, false);
        add(effect);
        resetEffect();
    }

    @Override
    public void keyPressed(int key) {
        switch (key) {
            case KeyEvent.VK_LEFT:
                if (!move(LEFT)) {
                    super.keyPressed(key);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (!move(RIGHT)) {
                    super.keyPressed(key);
                }
                break;
            case KeyEvent.VK_UP:
                if (!move(UP)) {
                    super.keyPressed(key);
                }
                break;
            case KeyEvent.VK_DOWN:
                if (!move(DOWN)) {
                    super.keyPressed(key);
                }
                break;
            default:
                super.keyPressed(key);
        }
    }

    @Override
    public boolean mouseMoved(int mouseX, int mouseY) {
        boolean b = super.mouseMoved(mouseX, mouseY);
        resetEffect();
        return (b);
    }

    public void resetEffect() {
        if (effect != null) {
            if (componentFocus != null) {
                //setEffect(componentFocus.getWidth(), componentFocus.getHeight());
                //if (isFlashing) {
                //effect.setAnimation(flash);
                //}
                effect.setPos(componentFocus.getPos());
                effect.setAlignment(componentFocus.getAlignment());
                effect.setAlpha(effectAlpha);
            } else {
                effect.setAlpha(0);
            }
        }
    }

    @Override
    public void mouseExited() {
        super.mouseExited();
        resetEffect();
    }
}
