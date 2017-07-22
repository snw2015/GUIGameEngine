package snw.engine.componentAVG;

import snw.engine.component.FrameComponent;
import snw.engine.component.Graphic;
import snw.engine.component.MovingTextBox;
import snw.engine.component.OptionBox;
import snw.engine.component.reaction.ReactionCommand;
import snw.engine.database.Database;

public class MainAVGC extends FrameComponent
{
	private Graphic background;
	private FrameComponent graphicComponent;
	private MovingTextBox textbox;
	private OptionBox optionbox;
	private ReactionCommand reaction = null;

	public MainAVGC()
	{
		super("mainScreen1680x1050", 0, 0, 1680, 1050);

		preLoadImageNames = new String[] { "background_main_screen", "option_common" };
		preLoadImages();

		background = new Graphic("background", getImage("background_main_screen"), 0, 0,
				width, height);
		add(background);

		graphicComponent = new FrameComponent("graphics", 0, 0, width, height);
		add(graphicComponent);

		textbox = new MovingTextBox("textbox", getImage("textbox_common"), width / 2,
				height - 15);
		textbox.setAlignment(ALIGNMENT_BOTTOMMID);
		textbox.setBackgroundAlpha(Database.getTextboxAlpha());
		add(textbox);

		add(optionbox);
	}

	public void setReaction(ReactionCommand reaction)
	{
		this.reaction = reaction;
	}
}
