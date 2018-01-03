package snw.engine.component;

import java.awt.Image;

public class MovingGraphic extends Graphic {
    private boolean isEnded = false;
    private boolean isLoop = true;
    private int counter = 0;
    private int frameNum = 0;
    private int updateSpeed = 0;// 0~100

    public void setImages(Image[] images) {
        this.images = images;
        if (!isEnded) {
            counter = 0;
            frameNum = 0;
        }
    }

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
        super(name, images[0], x, y, width, height, false);
        // TODO Auto-generated constructor stub
        this.images = images;
        this.updateSpeed = speed;
    }

    public MovingGraphic(String name, Image[] images, int x, int y, int speed) {
        super(name, images[0], x, y, false);
        // TODO Auto-generated constructor stub
        this.images = images;
        this.updateSpeed = speed;
    }

    @Override
    public void update() {
        super.update();
        if (updateSpeed != 0 && !isEnded) {
            int maxmumFrame = 100 / updateSpeed;
            if (++counter >= maxmumFrame) {
                counter = 0;
                if (++frameNum >= images.length) {
                    if (isLoop) {
                        frameNum = 0;
                    } else {
                        isEnded = true;
                    }
                }

            }
        }
        image = images[frameNum];
    }

    public int getSpeed() {
        return updateSpeed;
    }

    public void setSpeed(int speed) {
        this.updateSpeed = speed;
    }

    public int getFrameNum() {
        return frameNum;
    }

    public void setFrameNum(int frameNum) {
        this.frameNum = frameNum;
    }

    public void setLoop(boolean isLoop) {
        this.isLoop = isLoop;
    }

}
