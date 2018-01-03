<<<<<<< HEAD:GUIGameEngine/src/snw/engine/component/TopLevelComponent.java
package snw.engine.component;

import snw.engine.core.Engine;

import static snw.engine.core.Engine.getProperty;
import static snw.engine.core.Engine.getPropertyInt;

public class TopLevelComponent extends FrameComponent {
    private boolean hasCursor = false;
    private MovingGraphic cursor;
    private int state = STATE_NORMAL;

    public static final int STATE_NORMAL = 0;
    public static final int STATE_DRAG = 1;
    public static final int STATE_BUSY = 2;
    public static final int TOTAL_STATE_NUM = 3;


    public TopLevelComponent(String name) {
        this(name, 0, 0, 0, 0);
    }

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
            setState(STATE_DRAG);
        }
        super.mouseDragged(mouseX, mouseY);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY) {
        if (hasCursor) {
            setState(STATE_NORMAL);
        }
        super.mouseReleased(mouseX, mouseY);
    }

    //TODO multi-type cursor for choice
    //Extremely Multi-Thread Unsecured (maybe)
    public void setCursor(boolean isHas) {
        if (isHas) {
            if (!hasCursor) {
                String cursorName = getProperty("cursor_name");
                Engine.storeImageList(cursorName, getPropertyInt("cursor_normal_length"));
                Engine.storeImageList(cursorName + "_drag", getPropertyInt("cursor_drag_length"));
                Engine.storeImageList(cursorName + "_busy", getPropertyInt("cursor_busy_length"));
                cursor = new MovingGraphic("cursor", Engine.getCursorImages()[state], 0, 0,
                        20);
                cursor.loop();
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

    public void setState(int state) {
        if (this.state != state) {
            this.state = state;
            cursor.setImages(Engine.getCursorImages()[state]);
            cursor.loop();
        }
    }
=======
package snw.engine.component;

import com.sun.org.apache.xpath.internal.operations.Bool;
import snw.engine.database.Database;

public class TopLevelComponent extends FrameComponent {
    private boolean hasCursor = false;
    private MovingGraphic cursor;

    public TopLevelComponent(String name) {
        this(name, 0, 0, 0, 0);
    }

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
>>>>>>> parent of 5b3b56b... version 0.1.0 pure reduction:newAVGEngineDemo/src/snw/engine/component/TopLevelComponent.java
}