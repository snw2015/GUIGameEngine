package snw.engine.component;

import java.awt.*;
import java.awt.image.BufferedImage;

import snw.engine.animation.AnimationData;
import snw.math.VectorInt;

public class Graphic extends Component {
    protected Image image;
    protected Shape shape;
    private Color color;
    private int mode;

    public static final int MODE_IMAGE = 0;
    public static final int MODE_SHAPE = 1;

    public Graphic(String name, Image image, int x, int y, boolean focusable) {
        super(name, x, y, image.getWidth(null), image.getHeight(null), focusable);

        this.image = image;
        mode = MODE_IMAGE;
    }

    public Graphic(String name, Shape shape, int x, int y, boolean focusable) {
        super(name, x, y, (int) shape.getBounds().getWidth(),
                (int) shape.getBounds().getHeight(), focusable);
        color = Color.BLACK;
        this.shape = shape;
        mode = MODE_SHAPE;
    }

    public Graphic(String name, Color color, Shape shape, int x, int y, boolean focusable) {
        super(name, x, y, (int) shape.getBounds().getWidth(),
                (int) shape.getBounds().getHeight(), focusable);
        this.color = color;
        this.shape = shape;
        mode = MODE_SHAPE;
    }

    public Graphic(String name, Image image, int x, int y, int width, int height, boolean focusable) {
        // TODO Auto-generated constructor stub
        super(name, x, y, width, height, focusable);

        BufferedImage iBuffer = new BufferedImage(width, height,
                BufferedImage.TYPE_4BYTE_ABGR);
        Graphics iGraphics = iBuffer.getGraphics();
        iGraphics.drawImage(image, 0, 0, width, height, null);

        this.image = iBuffer;
        mode = MODE_IMAGE;
    }

    @Override
    public void paint(Graphics2D g, AnimationData appliedData) {
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, appliedData.getAlphaFloat()));

        switch (mode) {
            case MODE_IMAGE:
                g.drawImage(image, appliedData.getTransformation(), null);
                break;
            case MODE_SHAPE:
                g.setColor(color);
                g.fill(appliedData.getTransformation().createTransformedShape(shape));
                break;
            default:
                print("graphic mode erred");
        }

    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
