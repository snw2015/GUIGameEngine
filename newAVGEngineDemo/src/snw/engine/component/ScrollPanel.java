package snw.engine.component;

import java.awt.*;

public class ScrollPanel extends FrameComponent {
    private Component contents;

    private int fullX;
    private ScrollBar bar;

    public ScrollPanel(String name, int x, int y, int width, int height, int fullX, Component contents, ScrollBar bar, boolean focusable) {
        super(name, x, y, width, height, focusable);
        this.contents = contents;
        this.fullX = fullX;
        this.bar = bar;

        bar.setReaction((double value) -> {
            setPanelVision((int) ((fullX - width) * value), 0);
        });

        add(contents);
        add(bar);
    }

    public ScrollPanel(String name, int x, int y, int width, int height, int fullX, ScrollBar bar, Component contents) {
        super(name, x, y, width, height, true);
        this.contents = contents;
        this.fullX = fullX;
        this.bar = bar;

        bar.setReaction((double value) -> {
            setPanelVision((int) ((fullX - width) * value), 0);
        });

        add(contents);
        add(bar);
    }

    public void setPanelVision(int startX, int startY) {
        contents.setPaintedPosition(startX, startY);
    }
}