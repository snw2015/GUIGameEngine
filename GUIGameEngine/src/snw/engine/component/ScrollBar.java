package snw.engine.component;

import snw.engine.component.reaction.Reaction;

import java.awt.*;

public class ScrollBar extends FrameComponent {
    private Graphic background;
    private Graphic slider;
    private int sliderWidth;
    private int sliderHeight;

    private double barValue;

    private Reaction<Double> reaction;

    public ScrollBar(String name, Image backgroundImg, Image sliderImg, int x, int y, int width,
                     int height, int sliderWidth, int sliderHeight) {
        super(name, x, y, width, height);
        // TODO Auto-generated constructor stub
        this.background = new Graphic(name + "_background", backgroundImg, 0, 0, width, height, false);

        this.sliderWidth = sliderWidth;
        this.sliderHeight = sliderHeight;
        this.slider = new Graphic(name + "_slider", sliderImg, 0, 0, sliderWidth, sliderHeight, false);

        add(background);
        add(slider);
    }

    public double getBarValue() {
        return barValue;
    }

    public void setBarValue(double barValue) {
        this.barValue = barValue;
        if (reaction != null) {
            reaction.react(barValue);
        }
    }

    public void setReaction(Reaction<Double> reaction) {
        this.reaction = reaction;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        if (mouseX > width - sliderWidth / 2) {
            mouseX = width - sliderWidth / 2;
        } else if (mouseX < sliderWidth / 2) {
            mouseX = sliderWidth / 2;
        }
        setBarValue((double) (mouseX - sliderWidth / 2) / (double) (width - sliderWidth));

        slider.setPos(mouseX - sliderWidth / 2, 0);

        super.mousePressed(mouseX, mouseY);
    }

    @Override
    public void mouseDragged(int mouseX, int mouseY) {
        mouseClicked(mouseX, mouseY);
    }
}