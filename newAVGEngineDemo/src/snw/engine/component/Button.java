package snw.engine.component;

import java.awt.Image;
import java.awt.event.KeyEvent;

import snw.engine.component.reaction.*;
import snw.math.VectorInt;

/**
 * Need refactor constructors.
 */
public class Button extends FrameComponent {
    private Graphic background = null;
    private Text text = null;
    private Reaction<VectorInt> reactionClicked = null;
    private Reaction<VectorInt> reactionIn = null;
    private Reaction<VectorInt> reactionOut = null;
    private VectorInt borderSize = new VectorInt(0, 0);

    private int clickKey = KeyEvent.VK_ENTER;

    public Button(String name, int x, int y, int width, int height) {
        super(name, x, y, width, height);
    }

    public Button(String name, int x, int y, int width, int height, String rawtext) {
        super(name, x, y, width, height);

        setText(rawtext);

        add(text);
    }

    public Button(String name, int x, int y, Image backgroundImg, String rawtext,
                  int borderX, int borderY) {
        super(name, x, y, backgroundImg.getWidth(null), backgroundImg.getHeight(null));
        borderSize.x = borderX;
        borderSize.y = borderY;

        setBackground(backgroundImg);
        setText(rawtext);

        add(background);
        add(text);
    }

    public Button(String name, int x, int y, int width, int height, Image backgroundImg) {
        super(name, x, y, width, height);

        setBackground(backgroundImg);

        add(background);
    }

    public Button(String name, int x, int y, int width, int height, Image backgroundImg,
                  String rawtext, int borderX, int borderY) {
        super(name, x, y, width, height);
        borderSize.x = borderX;
        borderSize.y = borderY;

        setBackground(backgroundImg);
        setText(rawtext);

        add(background);
        add(text);
    }

    public Button(String name, int x, int y, int width, int height, Image backgroundImg,
                  String rawtext) {
        super(name, x, y, width, height);

        setBackground(backgroundImg);
        setText(rawtext);

        add(background);
        add(text);
    }

    public Button(String name, int x, int y, Image backgroundImg) {
        super(name, x, y, backgroundImg.getWidth(null), backgroundImg.getHeight(null));

        setBackground(backgroundImg);

        add(background);
    }

    public Button(String name, int x, int y, Image backgroundImg, String rawtext) {
        super(name, x, y, backgroundImg.getWidth(null), backgroundImg.getHeight(null));

        setBackground(backgroundImg);
        setText(rawtext);

        add(background);
        add(text);
    }

    public Button(String name, int x, int y, Graphic background, String rawtext) {
        super(name, x, y, background.getWidth(), background.getHeight());

        setText(rawtext);
        this.background = background;

        add(background);
        add(text);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        if (reactionClicked != null) {
            reactionClicked.react(new VectorInt(mouseX, mouseY));
        }
    }

    @Override
    public void mouseEntered() {
        super.mouseEntered();
        if (reactionIn != null) {
            reactionIn.react(null);
        }
    }

    @Override
    public void mouseExited() {
        super.mouseExited();
        if (reactionOut != null) {
            reactionOut.react(null);
        }
    }

    @Override
    public void keyPressed(int key) {
        if (key == clickKey) {
            if (reactionClicked != null) {
                reactionClicked.react(null);
            }
        }
    }

    public Graphic getBackground() {
        return background;
    }

    public void setBackground(Image image) {
        background = new Graphic(name + "_background", image, 0, 0, width,
                height, false);
    }

    public Text getText() {
        return text;
    }

    public void setText(String rawtext) {
        Text comText = new Text(name + "_text", rawtext, width / 2, height / 2,
                width - borderSize.x * 2, height - borderSize.y * 2);
        comText.setAlignment(ALIGNMENT_CENTER);
        text = comText;
    }

    public void setReactionClicked(Reaction<VectorInt> reactionClicked) {
        this.reactionClicked = reactionClicked;
    }

    public void setReactionIn(Reaction<VectorInt> reactionIn) {
        this.reactionIn = reactionIn;
    }

    public void setReactionOut(Reaction<VectorInt> reactionOut) {
        this.reactionOut = reactionOut;
    }

    public int getClickKey() {
        return clickKey;
    }

    public void setClickKey(int clickKey) {
        this.clickKey = clickKey;
    }

}
