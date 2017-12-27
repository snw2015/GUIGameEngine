package snw.engine.component;

import snw.engine.animation.AnimationData;

import java.awt.*;
import java.awt.image.BufferedImage;

//Untested
public class BufferredImagePaintable implements Paintable {
    private ImagePaintable content;
    private final BufferedImage image;
    private final int width;
    private final int height;

    public BufferredImagePaintable(int width, int height, ImagePaintable content) {
        this.content = content;
        this.width = width;
        this.height = height;
        image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
    }

    @Override
    public void paint(Graphics2D g, AnimationData appliedData) {
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, appliedData.getAlphaFloat()));

        Graphics2D bufferredG = (Graphics2D) image.getGraphics();
        bufferredG.clearRect(0, 0, width, height);

        content.paint(bufferredG);

        g.drawImage(image,appliedData.getTransformation(),null);
    }

    @Override
    public void finalize() {
        if (image != null) {
            image.getGraphics().dispose();
            image.flush();
        }
    }
}
