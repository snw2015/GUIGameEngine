package snw.engine.component;

import snw.engine.core.Engine;

import java.awt.*;
import java.util.Arrays;

public class Spirit extends FrameComponent {
    private Image[][] images;
    private MovingGraphic graphic;

    private int speed;
    private int stateIndex = 0;
    private String[] stateNames;

    public Spirit(String name, Image[][] images, int x, int y, int width, int height, int speed) {
        super(name, x, y, width, height);
        this.images = images;
        this.speed = speed;
        stateNames = new String[images.length];

        graphic = new MovingGraphic(name + "_graphic", images[0], 0, 0, width, height, speed);
        add(graphic);
    }

    public Spirit(String name, Image[][] images, int x, int y, int width, int height) {
        this(name, images, x, y, width, height, 5);
    }

    public Spirit(String name, Image[][] images, int x, int y, int speed) {
        this(name, images, x, y, images[0][0].getWidth(null), images[0][0].getHeight(null), speed);
    }

    public Spirit(String name, Image[][] images, int x, int y) {
        this(name, images, x, y, 5);
    }

    public void start() {
        graphic.start();
    }

    public void start(int stateIndex) {
        setState(stateIndex);
        start();
    }

    public void start(String stateName) {
        int stateIndex = getStateIndex(stateName);
        if (stateIndex >= 0) start(stateIndex);
    }

    public void loop() {
        graphic.loop();
    }

    public void loop(int stateIndex) {
        setState(stateIndex);
        loop();
    }

    public void loop(int stateIndex, int loopTime) {
        setState(stateIndex);
        graphic.loop(loopTime);
    }

    public void loop(String stateName) {
        int stateIndex = getStateIndex(stateName);
        if (stateIndex >= 0) loop(stateIndex);
    }

    public void loop(String stateName, int loopTime) {
        int stateIndex = getStateIndex(stateName);
        if (stateIndex >= 0) loop(stateIndex, loopTime);
    }

    public Image[][] getImages() {
        return images;
    }

    public void setImages(Image[][] images) {
        this.images = images;
        graphic.setImages(images[0]);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        graphic.setSpeed(speed);
    }

    public int getStateIndex(String stateName) {
        for (int i = 0; i < stateNames.length; i++) {
            if (stateNames[i] == stateName) return i;
        }
        return -1;
    }

    public String[] getStateNames() {
        return stateNames;
    }

    public void setStateNames(String... stateNames) {
        if (stateNames.length != this.stateNames.length) return;
        this.stateNames = stateNames;
    }

    public void setStateName(int index, String stateName) {
        if (index < stateNames.length)
            stateNames[index] = stateName;
    }

    public int getFramePos() {
        return graphic.getFramePos();
    }

    public void setFramePos(int framePos) {
        graphic.setFramePos(framePos);
    }

    public int getStateIndex() {
        return stateIndex;
    }

    public void setState(int stateIndex) {
        if (stateIndex < images.length && stateIndex >= 0) {
            this.stateIndex = stateIndex;
            graphic.setImages(images[stateIndex]);
        }
    }

    public void setState(String stateName) {
        setState(getStateIndex(stateName));
    }

    @Override
    public String toString() {
        return name + ": " + Arrays.toString(getStateNames());
    }

}