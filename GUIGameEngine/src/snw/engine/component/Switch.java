package snw.engine.component;

import snw.engine.component.reaction.Reaction;

import java.awt.Image;
import java.awt.event.KeyEvent;

public class Switch extends FrameComponent {
    private Graphic graphicOn;
    private Graphic graphicOff;

    public static final int STATE_ON = 0;
    public static final int STATE_OFF = 1;

    private int state = STATE_ON;

    public int[] clickKey = new int[]{KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,
            KeyEvent.VK_ENTER};
    private Reaction<Boolean> reaction = null;

    public Switch(String name, int x, int y, int width, int height, Image imageOn,
                  Image imageOff, int initialState) {
        super(name, x, y, width, height);
        // TODO Auto-generated constructor stub
        graphicOn = new Graphic(name + "_graphic_on", imageOn, width / 2, height / 2, false);
        graphicOn.setAlignment(ALIGNMENT_CENTER);
        graphicOff = new Graphic(name + "_graphic_off", imageOff, width / 2, height / 2, false);
        graphicOff.setAlignment(ALIGNMENT_CENTER);
        add(graphicOn);
        add(graphicOff);
        if (initialState == STATE_ON) {
            graphicOn.setAlpha(1);
            graphicOff.setAlpha(0);
        } else {
            graphicOn.setAlpha(0);
            graphicOff.setAlpha(1);
        }
    }

    public Switch(String name, int x, int y, Image imageOn, Image imageOff,
                  int initialState) {
        super(name, x, y, imageOn.getWidth(null), imageOn.getHeight(null));
        // TODO Auto-generated constructor stub
        graphicOn = new Graphic(name + "_graphic_on", imageOn, width / 2, height / 2, false);
        graphicOn.setAlignment(ALIGNMENT_CENTER);
        graphicOff = new Graphic(name + "_graphic_off", imageOff, width / 2, height / 2, false);
        graphicOff.setAlignment(ALIGNMENT_CENTER);

        add(graphicOn);
        add(graphicOff);
        if (initialState == STATE_ON) {
            graphicOn.setAlpha(1);
            graphicOff.setAlpha(0);
        } else {
            graphicOn.setAlpha(0);
            graphicOff.setAlpha(1);
        }
    }

    public Switch(String name, int x, int y, int width, int height, Image imageOn,
                  Image imageOff) {
        super(name, x, y, width, height);
        // TODO Auto-generated constructor stub
        graphicOn = new Graphic(name + "_graphic_on", imageOn, width / 2, height / 2, false);
        graphicOn.setAlignment(ALIGNMENT_CENTER);
        graphicOff = new Graphic(name + "_graphic_off", imageOff, width / 2, height / 2, false);
        graphicOff.setAlignment(ALIGNMENT_CENTER);
        add(graphicOn);
        add(graphicOff);
        graphicOn.setAlpha(1);
        graphicOff.setAlpha(0);
    }

    public Switch(String name, int x, int y, Image imageOn, Image imageOff) {
        super(name, x, y, imageOn.getWidth(null), imageOn.getHeight(null));
        // TODO Auto-generated constructor stub
        graphicOn = new Graphic(name + "_graphic_on", imageOn, width / 2, height / 2, false);
        graphicOn.setAlignment(ALIGNMENT_CENTER);
        graphicOff = new Graphic(name + "_graphic_off", imageOff, width / 2, height / 2, false);
        graphicOff.setAlignment(ALIGNMENT_CENTER);

        add(graphicOn);
        add(graphicOff);
        graphicOn.setAlpha(1);
        graphicOff.setAlpha(0);
    }

    @Override
    public void keyPressed(int key) {
        if (key == clickKey[0]) {
            switchOn();
        } else if (key == clickKey[1]) {
            switchOff();
        } else if (key == clickKey[2]) {
            if (state == STATE_ON) {
                switchOff();
            } else {
                switchOn();
            }
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        if (mouseX < width / 2) {
            switchOn();
        } else {
            switchOff();
        }
    }

    private void switchOn() {
        // TODO Auto-generated method stub
        if (state == STATE_OFF) {
            graphicOn.setAlpha(1);
            graphicOff.setAlpha(0);
            if (reaction != null) {
                reaction.react(true);
            }
            state = STATE_ON;
        }
    }

    private void switchOff() {
        // TODO Auto-generated method stub

        if (state == STATE_ON) {
            graphicOn.setAlpha(0);
            graphicOff.setAlpha(1);
            if (reaction != null) {
                reaction.react(false);
            }
            state = STATE_OFF;
        }
    }

    public void setReaction(Reaction<Boolean> reaction) {
        this.reaction = reaction;
    }
}
