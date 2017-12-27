package snw.engine.component;

import snw.engine.animation.AnimationData;

import java.awt.*;
import java.awt.geom.AffineTransform;

public interface EasyPaintable extends Paintable {
    public void paint(Graphics2D g, int x, int y, float alpha);

    public default void paint(Graphics2D g, AnimationData appliedData) {
        AffineTransform transform = appliedData.getTransformation();
        paint(g, (int) transform.getTranslateX(), (int) transform.getTranslateY(), appliedData.getAlphaFloat());
    }
}
