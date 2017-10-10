package snw.engine.componentAVG;

import snw.engine.animation.Animation;
import snw.engine.component.Button;
import snw.engine.component.FrameComponent;
import snw.engine.component.Graphic;
import snw.engine.component.ListPanel;
import snw.engine.component.ListPanelFactory;
import snw.engine.component.ScrollBar;
import snw.engine.component.reaction.ReactionCommand;

public class MainMenuC extends PanelC {
    private Graphic background;

    private Button buttonStart;
    private Button buttonContinue;
    private Button buttonExit;

    private ListPanel list;

    private ReactionCommand reaction = null;
    public static final int COMMAND_START = 1;
    public static final int COMMAND_CONTINUE = 2;
    public static final int COMMAND_EXIT = 0;

    private final Animation flash = new Animation(100, "file/flash.anm");

    public MainMenuC() {
        super("mainMenu");
    }

    @Override
    public void preProcess() {
        preLoadImageNames = new String[]{"background_main_menu", "button_common"};

        super.preProcess();

        addBackground();
        addButtons();
        addList();
    }

    private void addBackground() {
        background = new Graphic("background", getImage("background_main_menu"), 0, 0,
                width, height, false);
        add(background);
    }

    private void addButtons() {
        buttonStart = new Button("buttonStart", 300, 800 - 425, getImage("button_common"),
                "New Game");
        buttonStart.setAlignment(ALIGNMENT_BOTTOMMID);
        buttonStart.setReactionClicked(() ->
        {
            if (reaction != null) {
                reaction.react(COMMAND_START);
            }
        });
        buttonStart.setReactionIn(() ->
        {
            buttonStart.setAnimation(flash);
        });
        buttonStart.setReactionOut(() ->
        {
            buttonStart.setAnimation(null);
        });

        buttonContinue = new Button("buttonContinue", 300, 800 - 225,
                getImage("button_common"), "Continue");
        buttonContinue.setAlignment(ALIGNMENT_BOTTOMMID);
        buttonContinue.setReactionClicked(() ->
        {
            if (reaction != null) {
                reaction.react(COMMAND_CONTINUE);
            }
        });
        buttonContinue.setReactionIn(() ->
        {
            buttonContinue.setAnimation(flash);
        });
        buttonContinue.setReactionOut(() ->
        {
            buttonContinue.setAnimation(null);
        });
        buttonExit = new Button("buttonExit", 300, 800 - 25, getImage("button_common"),
                "Exit");
        buttonExit.setAlignment(ALIGNMENT_BOTTOMMID);
        buttonExit.setReactionClicked(() ->
        {
            if (reaction != null) {
                reaction.react(COMMAND_EXIT);
            }
        });
        buttonExit.setReactionIn(() ->
        {
            buttonExit.setAnimation(flash);
        });
        buttonExit.setReactionOut(() ->
        {
            buttonExit.setAnimation(null);
        });
    }

    private void addList() {
        list = ListPanelFactory.getVerticalInstance("menuList", width / 2, height - 50,
                600, 800, new Button[]{buttonStart, buttonContinue, buttonExit});
        list.setAlignment(ALIGNMENT_BOTTOMMID);
        list.setEffectAlpha(0.2f);
        add(list);
    }

    public void setReaction(ReactionCommand reaction) {
        this.reaction = reaction;
    }
}