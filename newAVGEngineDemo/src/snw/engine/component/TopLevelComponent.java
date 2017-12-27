package snw.engine.component;

import com.sun.org.apache.xpath.internal.operations.Bool;
import snw.engine.database.Database;

public class TopLevelComponent extends FrameComponent {
    private boolean hasCursor;
    private MovingGraphic cursor;

    public TopLevelComponent(String name, int x, int y, int width, int height) {
        this(name, x, y, width, height, true);
    }

    public TopLevelComponent(String name, int x, int y, int width, int height, boolean focusable) {
        super(name, x, y, width, height, focusable);
    }

    @Override
    public boolean mouseMoved(int mouseX, int mouseY) {
        if (hasCursor) {
            cursor.setPos(mouseX, mouseY);
        }

        return (super.mouseMoved(mouseX, mouseY));
    }

    @Override
    public void mouseDragged(int mouseX, int mouseY) {
        if (hasCursor) {
            cursor.setPos(mouseX, mouseY);
            changeCursor("drag");
        }
        super.mouseDragged(mouseX, mouseY);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY) {
        if (hasCursor) {
            changeCursor("normal");
        }
        super.mouseReleased(mouseX, mouseY);
    }

    //TODO multi-type cursor for choice
    //Extremely Multi-Thread Unsecured (maybe)
    public void setCursor(boolean isHas) {
        if (isHas) {
            if (!hasCursor) {
                cursor = new MovingGraphic("cursor", Database.getCursorData().getImages(), 0, 0,
                        50);
                add(cursor, 1);
                hasCursor = true;
            }
        } else {
            if (hasCursor) {
                remove(cursor);
                cursor = null;
                hasCursor = false;
            }
        }
    }

    protected void changeCursor(String typeName) {
        int posX = cursor.getX();
        int posY = cursor.getY();
        cursor.setImages(Database.getCursorData().getImages(typeName));
    }

    public void start() {
    }
}