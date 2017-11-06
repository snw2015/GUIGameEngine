package snw.engine.component;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.sun.javafx.tk.Toolkit;

import snw.engine.database.Database;
import snw.math.VectorInt;

public abstract class TopLevelComponent extends FrameComponent {
    private boolean hasCursor;
    private MovingGraphic cursor;

    public TopLevelComponent(String name, int x, int y, int width, int height, boolean hasCursor) {
        super(name, x, y, width, height, true);
        this.hasCursor = hasCursor;

        if (hasCursor) {
            cursor = new MovingGraphic("cursor", Database.getCursorData().getImages(), 0, 0,
                    50);
            add(cursor, 1);
        }
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
            setCursor("drag");
        }
        super.mouseDragged(mouseX, mouseY);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY) {
        if (hasCursor) {
            setCursor("normal");
        }
        super.mouseReleased(mouseX, mouseY);
    }

    protected void setCursor(String typeName) {
        int posX = cursor.getX();
        int posY = cursor.getY();
        cursor.setImages(Database.getCursorData().getImages(typeName));
    }

    public abstract void start();
}