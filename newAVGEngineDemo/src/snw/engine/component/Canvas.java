package snw.engine.component;

import snw.engine.animation.AnimationData;

import java.awt.*;

//Untested
public class Canvas extends Component {
    private Paintable content;

    public Canvas(String name, int x, int y, int width, int height, boolean focusable, Paintable content) {
        super(name, x, y, width, height, focusable);
        this.content = content;
    }

    @Override
    public void paint(Graphics2D g, AnimationData appliedData) {
        content.paint(g,appliedData);
    }
}