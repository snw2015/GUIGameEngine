package snw.engine.componentAVG;

import snw.engine.component.FrameComponent;
import snw.engine.component.MessageBox;
import snw.engine.component.reaction.ReactionMessageClicked;

public class PanelC extends FrameComponent
{
	private MessageBox msgBox;
	private boolean isMsg = false;

	public PanelC(String name)
	{
		super(name, 0, 0, 1680, 1050);
		// TODO Auto-generated constructor stub
	}

	protected void showMessageBox(String messageContent,
			ReactionMessageClicked reactionOfMessageBox)
	{
		remove(msgBox);
		msgBox = new MessageBox("messageBox", getImage("textbox_common"), messageContent,
				width / 2, height / 2, 650, 450, "Yes");
		msgBox.setAlignment(ALIGNMENT_CENTER);
		msgBox.setReactionClicked(() ->
		{
			if (reactionOfMessageBox != null)
			{
				reactionOfMessageBox.react();
			}
			removeMessageBox();
		});
		isMsg = true;
		componentFocus = msgBox;
		add(msgBox);
	}

	private void removeMessageBox()
	{
		remove(msgBox);
		msgBox = null;
		componentFocus = null;
		isMsg = false;
	}

	@Override
	public boolean mouseMoved(int mouseX, int mouseY)
	{
		if (isMsg)
		{
			msgBox.mouseMoved(mouseX - msgBox.getAlignedX(),
					mouseY - msgBox.getAlignedY());
			return (false);
		} else
		{
			return (super.mouseMoved(mouseX, mouseY));
		}
	}
}
