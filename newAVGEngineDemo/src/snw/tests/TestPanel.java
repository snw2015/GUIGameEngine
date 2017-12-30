package snw.tests;

import snw.engine.animation.AnimationData;
import snw.engine.component.Button;
import snw.engine.component.FrameComponent;
import snw.engine.component.Graphic;
import snw.engine.component.TopLevelComponent;
import snw.engine.core.Engine;
import snw.math.VectorInt;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class TestPanel extends FrameComponent {
    Graphic g1 = new Graphic("g1", getImage("a"), 500, 200);
    Button button1 = new Button("button1", 100, 100, 200, 100, getImage("button_common"), "Button!");
    Button button2 = new Button("button2", 300, 100, 200, 100, "Button either!");

    public TestPanel() {
        super("test", 0, 0, Engine.getWidth(), Engine.getHeight());
    }

    @Override
    public void load(String msg) {
        Engine.storeImage("a", "button_common");

        add(new Graphic("bg", Color.WHITE, 0, 0, getWidth(), getHeight()));

        add(g1);

        add(button1);
        button1.setReactionClicked((VectorInt pos) -> {
            System.out.println("Clicked!");
        });

        add(button2);
        button2.setReactionClicked((VectorInt pos) -> {
            System.out.println("Clicked2!");
        });
    }

    int counter = 0;

    @Override
    public void refresh() {
        button1.setTransform(AffineTransform.getRotateInstance(-(counter * Math.PI / 100)));
        button2.setTransform(AffineTransform.getRotateInstance(-(counter * Math.PI / 50)));
        g1.setTransform(AffineTransform.getRotateInstance((counter++ * Math.PI / 100)));
        counter %= 200;
    }
}