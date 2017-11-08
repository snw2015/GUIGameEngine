package snw.engine.component;

import snw.engine.component.reaction.Reaction;

import java.awt.Image;
import java.awt.event.KeyEvent;

public class OptionBox extends FrameComponent {
    private Graphic graphicBG;
    private Text text;
    private Button buttonYes;
    private Button buttonNo;

    private int borderWidth = 18;
    private int borderHeight = 9;

    private int buttonWidth = 50;
    private int buttonHeight = 30;
    private Reaction<Boolean> reaction;

    public OptionBox(String name, Image imageBG, String rawText, int x, int y, int width,
                     int height, String yesText, String noText) {
        super(name, x, y, width, height);
        // TODO Auto-generated constructor stub
        graphicBG = new Graphic(name + "_background", imageBG, 0, 0, width, height, false);
        add(graphicBG);
        text = new Text(name + "_text", rawText, borderWidth, borderHeight,
                width - borderWidth * 2, height - borderHeight * 3 - buttonWidth);
        add(text);
        buttonYes = new Button(name + "_yesButton", width / 4, height - borderHeight,
                buttonWidth, buttonHeight, yesText);
        buttonYes.setAlignment(ALIGNMENT_BOTTOMMID);
        buttonYes.setReactionClicked((e) ->
        {
            if (reaction != null) {
                reaction.react(true);
            }
        });
        add(buttonYes);
        buttonNo = new Button(name + "_noButton", width * 3 / 4, height - borderHeight,
                buttonWidth, buttonHeight, noText);
        buttonNo.setAlignment(ALIGNMENT_BOTTOMMID);
        buttonNo.setReactionClicked((e) ->
        {
            if (reaction != null) {
                reaction.react(false);
            }
        });
        add(buttonNo);
    }

    public void setReactionClicked(Reaction<Boolean> reaction) {
        this.reaction = reaction;
    }

    @Override
    public void keyPressed(int key) {
        if (reaction != null) {
            if (key == KeyEvent.VK_ENTER) {
                reaction.react(true);
            } else if (key == KeyEvent.VK_ESCAPE) {
                reaction.react(false);
            }
        }
    }
}
