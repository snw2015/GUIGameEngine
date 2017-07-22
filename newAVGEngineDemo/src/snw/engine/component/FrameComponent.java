package snw.engine.component;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import snw.math.VectorInt;

public class FrameComponent extends Component
{
	private ArrayList<Component> subComponents = new ArrayList<Component>();
	protected Component componentFocus = null;

	public FrameComponent(String name, int x, int y, int width, int height)
	{
		super(name, x, y, width, height);
	}

	protected void add(Component sub)
	{
		synchronized (this)
		{
			subComponents.add(sub);
		}
	}

	protected void add(int index, Component sub)
	{
		synchronized (this)
		{
			subComponents.add(index, sub);
		}
	}

	@Deprecated
	protected boolean addAfter(String name, Component sub)
	{
		synchronized (this)
		{
			int index = 0;
			Component object = subComponents.get(index);

			while (index < subComponents.size() && !object.getName().equals(name))
			{
				object = subComponents.get(++index);
			}
			if (index < subComponents.size())
			{
				subComponents.add(index, sub);
				return (true);
			}
			return (false);
		}
	}

	@Deprecated
	protected boolean remove(String name)
	{
		synchronized (this)
		{
			int index = 0;

			while (index < subComponents.size())
			{
				if (subComponents.get(index).getName().equals(name))
				{
					break;
				}
				index++;
			}
			if (index < subComponents.size())
			{
				remove(index);
				return (true);
			}
			return (false);

		}
	}

	@Deprecated
	protected boolean remove(Component sub)
	{
		synchronized (this)
		{
			int index = 0;

			while (index < subComponents.size())
			{
				if (subComponents.get(index) == sub)
				{
					break;
				}
				index++;
			}
			if (index < subComponents.size())
			{
				remove(index);
				return (true);
			}
			return (false);
		}
	}

	protected boolean remove(int index)
	{
		synchronized (this)
		{
			if (subComponents.size() > index)
			{
				subComponents.get(index).exit();
				subComponents.remove(index);
				componentFocus.mouseExited();
				componentFocus = null;
				return (true);
			}
			return (false);
		}
	}

	@Override
	public void paint(Graphics g)
	{
		synchronized (this)
		{
			for (Component sub : subComponents)
			{
				Graphics2D g2d = (Graphics2D) g;
				if (sub != null)
				{
					g2d.setComposite(
							AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
					sub.render(g);
				}
			}
		}
	}

	@Override
	public void keyTyped(char keyChar)
	{
		if (componentFocus != null)
		{
			componentFocus.keyTyped(keyChar);
		}
	}

	@Override
	public void keyPressed(int key)
	{
		if (componentFocus != null)
		{
			componentFocus.keyPressed(key);
		}
	}

	@Override
	public void keyReleased(int key)
	{
		if (componentFocus != null)
		{
			componentFocus.keyReleased(key);
		}
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY)
	{
		if (componentFocus != null)
		{
			Component sub = componentFocus;
			sub.mouseClicked(mouseX - sub.getX(), mouseY - sub.getY());
		}
	}

	@Override
	public boolean mouseMoved(int mouseX, int mouseY)
	{
		// print("move in " + name);
		Component sub = null;
		for (int i = subComponents.size() - 1; i >= 0; i--)
		{
			sub = subComponents.get(i);
			if (sub != null && new VectorInt(mouseX, mouseY).inBound(sub.getBound()))
			{
				sub.mouseMoved(mouseX - sub.getAlignedX(), mouseY - sub.getAlignedY());
				boolean changed = componentFocus != sub;
				if (changed)
				{
					if (componentFocus != null)
					{
						componentFocus.mouseExited();
					}
					sub.mouseEntered();
					componentFocus = sub;
				}
				return (changed);
			}
		}
		if (componentFocus != null)
		{
			componentFocus.mouseExited();
			componentFocus = null;
			return (true);
		}
		return (false);

	}

	@Override
	public void mouseExited()
	{
		componentFocus = null;
	}

	public String toString()
	{
		return (name);
	}
}
