package snw.engine.script;

public interface AVGScreen
{
	public void say(String speakerName, String content);

	public void displaySelection(String[] contains);

	public void displayGraphic(String imageName, int position);

	public void displayBGM(String BGMName);

	public void displaySound(String soundName);

	public int getSelectedValue();

	public boolean getBooleanVar(int type, int index);

	public int getIntVar(int type, int index);

	public String getStringVar(int type, int index);

}