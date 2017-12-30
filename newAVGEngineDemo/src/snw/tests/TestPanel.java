package snw.tests;

import snw.engine.animation.AnimationData;
import snw.engine.component.Button;
import snw.engine.component.FrameComponent;
import snw.engine.component.Graphic;
import snw.engine.component.TopLevelComponent;
import snw.engine.core.Engine;
import snw.math.VectorInt;

import java.awt.*;

public class TestPanel extends FrameComponent {
    public TestPanel(){
        super("test",0,0, Engine.getWidth(),Engine.getHeight());
    }

    @Override
    public void load(String msg){
        add(new Graphic("g1",Color.WHITE,0,0,getWidth(),getHeight()));
        Button button = new Button("button1",100,100,200,100,"Button!");
        add(button);
        button.setReactionClicked((VectorInt pos)->{
            System.out.println("Clicked!");
        });
    }
}