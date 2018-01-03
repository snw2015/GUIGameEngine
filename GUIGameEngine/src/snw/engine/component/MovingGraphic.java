<<<<<<< HEAD:GUIGameEngine/src/snw/engine/component/MovingGraphic.java
package snw.engine.component;

import java.awt.Image;

public class MovingGraphic extends Graphic {
    private boolean isStopped = true;
    private int loopTime = -1;
    private int speedCounter = 0;
    private int framePos = 0;
    private int updateSpeed = 0;// 0~100


    private Image[] images;

    public MovingGraphic(String name, Image[] images, int x, int y, int width, int height,
                         int speed, boolean focusable) {
        super(name, images[0], x, y, width, height, focusable);
        // TODO Auto-generated constructor stub
        this.images = images;
        this.updateSpeed = speed;
    }

    public MovingGraphic(String name, Image[] images, int x, int y, int speed, boolean focusable) {
        super(name, images[0], x, y, focusable);
        // TODO Auto-generated constructor stub
        this.images = images;
        this.updateSpeed = speed;
    }

    public MovingGraphic(String name, Image[] images, int x, int y, int width, int height,
                         int speed) {
        this(name, images, x, y, width, height, speed, false);
    }

    public MovingGraphic(String name, Image[] images, int x, int y, int width, int height) {
        this(name, images, x, y, width, height, 5);
    }

    public MovingGraphic(String name, Image[] images, int x, int y, int speed) {
        this(name, images, x, y, speed, false);
    }

    public MovingGraphic(String name, Image[] images, int x, int y) {
        this(name, images, x, y, 5);
    }

    public void loop(int loopTime) {
        terminate();
        this.loopTime = loopTime;
        setStopped(false);
    }

    public void loop() {
        loop(-1);
    }

    public void start() {
        loop(1);
    }

    public void stop() {
        setStopped(true);
    }

    public void resume() {
        setStopped(false);
    }

    public void terminate() {
        stop();
        loopTime = -1;
        speedCounter = 0;
        framePos = 0;
    }

    @Override
    public void update() {
        super.update();
        if (loopTime != 0 && updateSpeed >= 0 && !isStopped()) {
            if (++speedCounter >= 100 / updateSpeed) {
                speedCounter = 0;
                image = images[framePos];
                if (++framePos >= images.length) {
                    if (loopTime == -1 || --loopTime > 0) {
                        framePos = 0;
                    } else {
                        terminate();
                    }
                }

            }
        }
    }

    public int getSpeed() {
        return updateSpeed;
    }

    public void setSpeed(int speed) {
        this.updateSpeed = speed;
    }

    public int getFramePos() {
        return framePos;
    }

    public void setFramePos(int framePos) {
        if (framePos >= images.length) framePos = images.length - 1;
        this.framePos = framePos;
    }

    public boolean isStopped() {
        return isStopped;
    }

    public void setStopped(boolean stopped) {
        isStopped = stopped;
    }

    public void setImages(Image[] images) {
        this.images = images;
        stop();
        speedCounter = 0;
        framePos = 0;
    }
}
=======
package snw.engine.component;

import snw.engine.core.Engine;
import snw.file.FileDirectReader;

import java.awt.Image;

public class MovingGraphic extends Graphic {
    private boolean isStopped = true;
    private int loopTime = -1;
    private int speedCounter = 0;
    private int framePos = 0;
    private int updateSpeed = 0;// 0~100


    private Image[] images;

    public MovingGraphic(String name, Image[] images, int x, int y, int width, int height,
                         int speed, boolean focusable) {
        super(name, images[0], x, y, width, height, focusable);
        // TODO Auto-generated constructor stub
        this.images = images;
        this.updateSpeed = speed;
    }

    public MovingGraphic(String name, Image[] images, int x, int y, int speed, boolean focusable) {
        super(name, images[0], x, y, focusable);
        // TODO Auto-generated constructor stub
        this.images = images;
        this.updateSpeed = speed;
    }

    public MovingGraphic(String name, Image[] images, int x, int y, int width, int height,
                         int speed) {
        this(name, images, x, y, width, height, speed, false);
    }

    public MovingGraphic(String name, Image[] images, int x, int y, int width, int height) {
        this(name, images, x, y, width, height, 5);
    }

    public MovingGraphic(String name, Image[] images, int x, int y, int speed) {
        this(name, images, x, y, speed, false);
    }

    public MovingGraphic(String name, Image[] images, int x, int y) {
        this(name, images, x, y, 5);
    }

    public void loop(int loopTime) {
        terminate();
        this.loopTime = loopTime;
        setStopped(false);
    }

    public void loop() {
        loop(-1);
    }

    public void start() {
        loop(1);
    }

    public void stop() {
        setStopped(true);
    }

    public void resume() {
        setStopped(false);
    }

    public void terminate() {
        stop();
        loopTime = -1;
        speedCounter = 0;
        framePos = 0;
    }

    @Override
    public void update() {
        super.update();
        if (loopTime != 0 && updateSpeed >= 0 && !isStopped()) {
            if (++speedCounter >= 100 / updateSpeed) {
                speedCounter = 0;
                image = images[framePos];
                if (++framePos >= images.length) {
                    if (loopTime == -1 || --loopTime > 0) {
                        framePos = 0;
                    } else {
                        terminate();
                    }
                }

            }
        }
    }

    public int getSpeed() {
        return updateSpeed;
    }

    public void setSpeed(int speed) {
        this.updateSpeed = speed;
    }

    public int getFramePos() {
        return framePos;
    }

    public void setFramePos(int framePos) {
        if (framePos >= images.length) framePos = images.length - 1;
        this.framePos = framePos;
    }

    public boolean isStopped() {
        return isStopped;
    }

    public void setStopped(boolean stopped) {
        isStopped = stopped;
    }

    public void setImages(Image[] images) {
        this.images = images;
        stop();
        speedCounter = 0;
        framePos = 0;
    }

    public static void main(String[] args) {
        Engine.initialize();

        MovingGraphic graphic = new MovingGraphic("testg", Engine.getImages("a_1", "a_2", "a_3", "a_4", "a_5"), 100, 100);
        Engine.getPanel().add(graphic);

        Engine.start();

        graphic.loop(10);
    }
}
>>>>>>> parent of 5b3b56b... version 0.1.0 pure reduction:newAVGEngineDemo/src/snw/engine/component/MovingGraphic.java
