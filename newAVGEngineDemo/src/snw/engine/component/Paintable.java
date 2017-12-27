package snw.engine.component;

import snw.engine.animation.AnimationData;

import java.awt.*;

public interface Paintable {
    public void paint(Graphics2D g, AnimationData appliedData);
}