package snw.engine.component;

import java.awt.Image;

public class MovingTextBox extends TextBox {
    private boolean isEnded = false;
    private int counter = 0;
    private int updateSpeed = 100;// 0~100

    public MovingTextBox(String name, Image background, int x, int y, int width,
                         int height) {
        super(name, background, x, y, width, height);
        text.setRenderLength(0);
        // TODO Auto-generated constructor stub
    }

    public MovingTextBox(String name, Image background, int x, int y) {
        super(name, background, x, y);
        text.setRenderLength(0);
        // TODO Auto-generated constructor stub
    }

    public MovingTextBox(String name, Image background, String rawText, int x, int y) {
        super(name, background, rawText, x, y);
        text.setRenderLength(0);
        // TODO Auto-generated constructor stub
    }

    public MovingTextBox(String name, Image background, String rawText) {
        super(name, background, rawText);
        text.setRenderLength(0);
        // TODO Auto-generated constructor stub
    }

    public MovingTextBox(String name, Image background) {
        super(name, background);
        text.setRenderLength(0);
        // TODO Auto-generated constructor stub
    }

    public MovingTextBox(String name, Image background, String rawText, int x, int y,
                         int width, int height) {
        super(name, background, rawText, x, y, width, height);
        text.setRenderLength(0);
        // TODO Auto-generated constructor stub
    }

    public MovingTextBox(String name, Image background, String rawText, int x, int y,
                         int width, int height, int speed) {
        super(name, background, rawText, x, y, width, height);
        text.setRenderLength(0);
        this.updateSpeed = speed;
        // TODO Auto-generated constructor stub
    }

    @Override
    public void update() {
        super.update();
        if (updateSpeed != 0 && !isEnded) {
            if (++counter > 100 / updateSpeed) {
                counter = 0;
                if (!text.addRenderLength()) {
                    isEnded = true;
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

    @Override
    public void setTextContent(String rawText) {
        super.setTextContent(rawText);
    }

    public void resetRender() {
        text.setRenderLength(0);
        isEnded = false;
        counter = 0;
    }
}
