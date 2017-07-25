package snw.engine.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageReader;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import snw.engine.animation.Animation;
import snw.engine.script.DataSet;
import snw.engine.script.ScriptProcessor;
import snw.file.FileDirectReader;
import snw.math.VectorInt;

public class MainPanel extends TopLevelComponent
{
	VectorInt mousePos = new VectorInt();

	ScriptProcessor sp = null;

	public MainPanel(String name, int x, int y, int width, int height, JFrame frame)
	{
		super(name, x, y, width, height, frame);
		BufferedReader chapter1 = null;
		File file = new File("file/text1.scp");
		try
		{
			chapter1 = new BufferedReader(
					(new InputStreamReader(new FileInputStream(file))));
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sp = new ScriptProcessor("chapter1", new DataSet(), chapter1);
		try
		{
			chapter1.close();
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		MovingTextBox tb = new MovingTextBox("textbox1",
				FileDirectReader.getImage("file/a.png"),
				"Aq挖机配件区平均额ewqeefq, egqeqeve.\r\r\rSe,sewははｈadw,问为什么吧你\\f2\\c2吼吼吼\\c0\\f0wes,woeks.",
				0, 0, 300, 100);
		tb.setSpeed(50);
		tb.setBackgroundAlpha(0.8f);
		// add(tb);

		MovingGraphic gr = new MovingGraphic("mg1",
				FileDirectReader.getImageList("file/a.png", 5), 300, 200, 20);
		// add(gr);

		Animation anime1 = new Animation(100, "file/flash.anm");

		Button bt = new Button("button1", 300, 50, 200, 100,
				FileDirectReader.getImage("file/a.png"),
				"A model of button with long string", 15, 10);
		bt.setReactionClicked(() ->
		{
		});
		bt.setReactionIn(() ->
		{
			bt.setAnimation(anime1);
		});
		bt.setReactionOut(() ->
		{
			bt.setAnimation(null);
		});

		Graphic sp = new Graphic("shape1", new Rectangle(100, 100), 200, 200);
		sp.setAlignment(ALIGNMENT_BOTTOMMID);

		Switch sw1 = new Switch("switch1", 150, 400, 300, 120,
				FileDirectReader.getImage("file/switch_on.png"),
				FileDirectReader.getImage("file/switch_off.png"));
		sw1.setReaction((isOn) ->
		{
			print(isOn ? "switch on" : "switch off");
		});

		SetSwitch ss = new SetSwitch("setSwitch1", 100, 550, 300, 100,
				new String[] { "aaaaaaaa", "bbbb", "c" });

		ListPanel lp = ListPanelFactory
				.getGridInstance(
						"listPanelHorizontal1", 20, 20, 800, 800, new Component[][] {
								{ tb, bt }, { sp, gr }, { sw1, sw1 }, { ss, ss } },
						ListPanelFactory.ALL_LOOP);

		lp.setEffect(300, 300, Color.YELLOW);
		lp.setFlash(true);

		add(lp);
		componentFocus = lp;

		MessageBox mb = new MessageBox("messageBox1",
				FileDirectReader.getImage("file/a.png"),
				"test MessageBox! Just for test................................", 500,
				200, 500, 500, "Yes");
		add(mb);

		OptionBox ob = new OptionBox("optionBox1",
				FileDirectReader.getImage("file/a.png"),
				"test OptionBox! Just for test................................", 500, 200,
				500, 500, "Yes", "No");

		mb.setReactionClicked(() ->
		{
			remove(mb);
			add(ob);
		});

		SelectionBox sb = new SelectionBox("selectionBox1",
				FileDirectReader.getImage("file/a.png"), "Pls make your choice", 500, 150,
				400, 700, new String[] { "1,add", "2,minus", "3,death" });
		sb.setReactionClicked((index) ->
		{
			print(index + 1 + " has been selected");
			remove(sb);
		});

		ob.setReactionClicked((isAgreed) ->
		{
			if (isAgreed)
			{
				print("yes!");
			} else
			{
				print("nonono!");
			}
			remove(ob);
			add(sb);
		});
	}

	@Override
	public void keyPressed(int key)
	{
		if (key == KeyEvent.VK_SPACE)
		{
			snw.engine.script.ScriptProcessor.CommandInfo commandInfo = sp.process();
			if (commandInfo != null)
			{
				switch (commandInfo.command)
				{
				case ScriptProcessor.COMMAND_SAY:
					remove("textbox1");
					ImageIcon icon = new ImageIcon("file/a.png");
					if (commandInfo.inputs.length == 2)
					{
						commandInfo.inputs[0] = commandInfo.inputs[0] + " : "
								+ commandInfo.inputs[1];
					}
					MovingTextBox tb = new MovingTextBox("textbox1", icon.getImage(),
							commandInfo.inputs[0], 100, 100, 300, 100);
					tb.setSpeed(40);
					add(0, tb);
					break;
				case ScriptProcessor.COMMAND_END:
					remove("textbox1");

					ImageIcon icon2 = new ImageIcon("file/a.png");
					add(new MovingTextBox("textboxEnd", icon2.getImage(), "It's ended.",
							100, 100, 300, 100, 100));
					break;
				default:
					break;
				}
			}
		} else
		{
			super.keyPressed(key);
		}
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY)
	{
		print(mousePos.x + " , " + mousePos.y);
		super.mouseClicked(mousePos.x, mousePos.y);
	}

	@Override
	public boolean mouseMoved(int mouseX, int mouseY)
	{
		mousePos.x = mouseX;
		mousePos.y = mouseY;
		return (super.mouseMoved(mouseX, mouseY));
	}

}
