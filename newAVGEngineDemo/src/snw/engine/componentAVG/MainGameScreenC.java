package snw.engine.componentAVG;

import java.awt.event.KeyEvent;

import snw.engine.component.FrameComponent;
import snw.engine.component.Graphic;
import snw.engine.component.MovingTextBox;
import snw.engine.component.SelectionBox;
import snw.engine.component.reaction.Reaction;
import snw.engine.database.Database;
import snw.engine.script.AVGScreen;
import snw.engine.script.ScriptProcessor;

public class MainGameScreenC extends PanelC implements AVGScreen {
    private Graphic background;
    private FrameComponent graphicLayer;
    private MovingTextBox textbox;
    private SelectionBox selectionbox;
    private Reaction<Integer> reaction = null;
    private ScriptProcessor processor;

    public static final int COMMAND_END = 0;
    public static final int COMMAND_SAVE = 1;

    public MainGameScreenC() {
        super("mainGameScreen");
    }

    @Override
    public void preProcess() {
        preLoadImageNames = new String[]{"background_main_screen", "option_common"};
        super.preProcess();

        addBackground();
        addGraphicLayer();
        addTextbox();
    }

    private void addBackground() {
        background = new Graphic("background", getImage("background_main_screen"), 0, 0,
                width, height, false);
        add(background);
    }

    private void addGraphicLayer() {
        graphicLayer = new FrameComponent("graphicLayer", 0, 0, width, height);
        add(graphicLayer);
    }

    private void addTextbox() {
        textbox = new MovingTextBox("textbox", getImage("textbox_common"), width / 2,
                height - 15);
        textbox.setAlignment(ALIGNMENT_BOTTOMMID);
        textbox.setBackgroundAlpha(Database.getTextboxAlpha());
        add(textbox);
    }

    private void setTextBoxContent(String rawText) {
        textbox.setTextContent(rawText);
        textbox.resetRender();
    }

    private void displaySelection(String titleText, String[] selections) {
        remove("selectionbox");
        selectionbox = new SelectionBox("selectionbox", getImage("textbox_common"),
                titleText, width / 2, 250, 525, 675, selections);
        selectionbox.setAlignment(ALIGNMENT_TOPMID);
        add(selectionbox);
    }

    public void setReaction(Reaction<Integer> reaction) {
        this.reaction = reaction;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        super.mouseClicked(mouseX, mouseY);
        if (processor != null) {
            processor.process();
        }
    }

    @Override
    public void keyPressed(int key) {
        if (reaction != null) {
            if (key == KeyEvent.VK_ESCAPE) {
                reaction.react(COMMAND_END);
            } else if (key == KeyEvent.VK_S) {
                reaction.react(COMMAND_SAVE);
            }
        } else {
            super.keyPressed(key);
        }
    }

    private void setTextboxVisible(boolean isVisible) {
        textbox.setVisible(isVisible);
    }

    private void setTextboxBackAlpha(float alpha) {
        textbox.setBackgroundAlpha(alpha);
    }

    @Override
    public void say(String speakerName, String content) {
    }

    @Override
    public void displaySelection(String[] contains) {
    }

    @Override
    public void setBackground(String imageName) {

    }

    @Override
    public void displayGraphic(String imageName, int position) {

    }

    @Override
    public void setBGM(String BGMName) {

    }

    @Override
    public void displaySound(String soundName) {

    }

    @Override
    public void end(String endName) {
        reaction.react(COMMAND_END);
    }

    @Override
    public int getSelectedValue() {
        return 0;
    }

    @Override
    public boolean getBooleanVar(int type, int index) {
        return false;
    }

    @Override
    public int getIntVar(int type, int index) {
        return 0;
    }

    @Override
    public String getStringVar(int type, int index) {
        return null;
    }
}
