package snw.tests;

import snw.engine.animation.Animation;
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
    Button button1 = new Button("button1", 100, 100, 200, 100, "Button!");
    Button button2 = new Button("button2", 300, 100, 200, 100, "Button either!");

    public TestPanel() {
        super("test", 0, 0, Engine.getWidth(), Engine.getHeight());
    }

    @Override
    public void load(String msg) {
        Engine.storeImage("a", "a_3", "button_common");

        add(new Graphic("bg", Color.WHITE, 0, 0, getWidth(), getHeight()));

        button1.setBackground(getImage("a"));
        add(button1);
        button1.setAlignment(ALIGNMENT_CENTER);
        button1.setReactionClicked((VectorInt pos) -> {
            System.out.println("Clicked!");
        });
        button1.setReactionIn((VectorInt pos) -> {
            button1.setBackground(getImage("a_3"));
        });
        button1.setReactionOut((VectorInt pos) -> {
            button1.setBackground(getImage("a"));
        });

        add(button2);
        button2.setBackground(getImage("button_common"));
        button2.setReactionClicked((VectorInt pos) -> {
            System.out.println("Clicked2!");
        });

        setAnimation(new Animation(20, "moveright"));
    }

    int counter = 0;

    @Override
    public void refresh() {
        button1.setTransform(AffineTransform.getRotateInstance(-(counter * Math.PI / 100), button1.getWidth() / 2, button1.getHeight() / 2));
        button2.setTransform(AffineTransform.getRotateInstance(-(counter++ * Math.PI / 50)));
        counter %= 200;
    }
}