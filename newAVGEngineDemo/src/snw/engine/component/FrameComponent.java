package snw.engine.component;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.ColorModel;
import java.util.ArrayList;

import com.sun.scenario.animation.shared.AnimationAccessor;
import snw.engine.animation.AnimationData;
import snw.math.VectorInt;


public class FrameComponent extends Component {

    private ArrayList<Component> subComponents = new ArrayList<>();
    private ArrayList<Integer> subPriorities = new ArrayList<>();

    protected Component componentFocus = null;
    private int mouseX;
    private int mouseY;

    public FrameComponent(String name, int x, int y, int width, int height, boolean focusable) {
        super(name, x, y, width, height, focusable);
    }

    public FrameComponent(String name, int x, int y, int width, int height) {
        super(name, x, y, width, height);
    }

    protected void add(Component sub, int priority) {
        synchronized (this) {
            int index = subPriorities.size();
            for (int i = 0; i < subPriorities.size(); i++) {
                if (subPriorities.get(i) > priority) {
                    index = i;
                    break;
                }
            }

            subPriorities.add(index, new Integer(priority));
            subComponents.add(index, sub);
        }
    }

    protected void add(Component sub) {
        add(sub, 0);
    }

    protected boolean remove(String name) {
        synchronized (this) {
            int index = 0;

            while (index < subComponents.size()) {
                if (subComponents.get(index).getName().equals(name)) {
                    break;
                }
                index++;
            }
            if (index < subComponents.size()) {
                remove(index);
                return (true);
            }
            return (false);

        }
    }

    protected boolean remove(Component sub) {
        synchronized (this) {
            int index = 0;

            while (index < subComponents.size()) {
                if (subComponents.get(index) == sub) {
                    break;
                }
                index++;
            }
            return (remove(index));
        }
    }

    private boolean remove(int index) {
        synchronized (this) {
            if (subComponents.size() > index) {
                subComponents.remove(index);
                subPriorities.remove(index);

                exitFocus();
                return (true);
            }
            return (false);
        }
    }

    protected boolean removeAll() {
        synchronized (this) {
            if (subComponents.isEmpty()) {
                return false;
            }
            subComponents.clear();
            subComponents.trimToSize();
            subPriorities.clear();
            subPriorities.trimToSize();

            exitFocus();
            return true;
        }
    }

    private void exitFocus() {
        if (componentFocus != null) {
            componentFocus.mouseExited();
            componentFocus = null;
        }
    }


    protected Component getSub(int index) {
        synchronized (this) {
            if (subComponents.size() > index) {
                return (subComponents.get(index));
            }
        }
        return (null);
    }

    protected Component getSub(String name) {
        synchronized (this) {
            int index = 0;

            while (index < subComponents.size()) {
                if (subComponents.get(index).getName().equals(name)) {
                    break;
                }
                index++;
            }
            if (index < subComponents.size()) {
                return (getSub(index));
            }
        }
        return (null);
    }

    public ArrayList<Component> getSubs() {
        return subComponents;
    }

    @Override
    public void paint(Graphics2D g, AnimationData appliedData) {
        synchronized (this) {
            for (Component sub : subComponents) {
                if (sub != null) {
                    sub.render(g, appliedData);
                }
            }
        }
    }

    @Override
    public void update() {
        synchronized (this) {
            super.update();
            for (Component sub : subComponents) {
                if (sub != null) {
                    sub.update();
                }
            }
        }
    }

    protected boolean hasSub(Component sub) {
        return (subComponents.contains(sub));
    }

    private boolean isInBound(Component sub, int x, int y, int width, int height) {
        int l1 = x, r1 = x + width, t1 = y, b1 = y + height;
        int l2 = sub.x, r2 = sub.x + sub.width, t2 = sub.y, b2 = sub.y + sub.height;
        return (l1 <= r2 && r1 >= l2 && t1 <= b2 && b1 >= t2);
    }

    @Override
    public void keyTyped(char keyChar) {
        if (componentFocus != null) {
            componentFocus.keyTyped(keyChar);
        }
    }

    @Override
    public void keyPressed(int key) {
        if (componentFocus != null) {
            componentFocus.keyPressed(key);
        }
    }

    @Override
    public void keyReleased(int key) {
        if (componentFocus != null) {
            componentFocus.keyReleased(key);
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        refocusMouse();
        if (componentFocus != null) {
            Component sub = componentFocus;
            sub.mouseClicked(mouseX - sub.getX(), mouseY - sub.getY());
        }
    }

    @Override
    public boolean mouseMoved(int mouseX, int mouseY) {
        // print("move in " + name);
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        return refocusMouse();
    }

    public boolean refocusMouse() {
        Component sub = null;

        for (int i = subComponents.size() - 1; i >= 0; i--) {
            sub = subComponents.get(i);
            if (sub != null && sub.isFocusable() && sub.getBound().contains(mouseX, mouseY)) {
                boolean changed = componentFocus != sub;
                if (changed) {
                    if (componentFocus != null) {
                        componentFocus.mouseExited();
                    }
                    sub.mouseEntered();
                    componentFocus = sub;
                }

                sub.mouseMoved(mouseX - sub.getAlignedX(), mouseY - sub.getAlignedY());

                return (changed);
            }
        }
        if (componentFocus != null) {
            componentFocus.mouseExited();
            componentFocus = null;
            return (true);
        }
        return (false);
    }

    @Override
    public void setPaintedPosition(int paintedX, int paintedY) {
        for (Component sub : subComponents) {
            if (sub != null) {
                sub.setPos(sub.getX(-paintedX), sub.getY(-paintedY));
            }
        }
    }

    @Override
    public void mouseDragged(int mouseX, int mouseY) {
        if (componentFocus != null) {
            componentFocus.mouseDragged(mouseX - componentFocus.getAlignedX(),
                    mouseY - componentFocus.getAlignedY());
            super.mouseDragged(mouseX, mouseY);
        }
    }

    @Override
    public void mouseExited() {
        if (componentFocus != null) {
            componentFocus.mouseExited();
        }
        componentFocus = null;
    }

    public String toString() {
        return (name);
    }
}
