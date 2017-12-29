package snw.engine.text;

import snw.engine.database.Database;
import snw.engine.text.structure.LengthList;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class ExtendText implements Cloneable {
    public final static HashMap<String, Color> colorNameMap = new HashMap<>();
    static {
        colorNameMap.put("WHITE", Color.white);
        colorNameMap.put("white", Color.white);
        colorNameMap.put("BLACK", Color.black);
        colorNameMap.put("black", Color.black);
        colorNameMap.put("RED", Color.red);
        colorNameMap.put("red", Color.red);
        colorNameMap.put("BLUE", Color.blue);
        colorNameMap.put("blue", Color.blue);
        colorNameMap.put("GREEN", Color.green);
        colorNameMap.put("green", Color.green);
        colorNameMap.put("YELLOW", Color.yellow);
        colorNameMap.put("yellow", Color.yellow);
    }

    private String contents;
    private LengthList<Color> colorList;
    private LengthList<Integer> sizeList;
    private LengthList<String> fontList;
    private ArrayList<Integer> contentsWidth;

    private int next = 0;


    //TODO default width and height
    private static final Graphics g = new BufferedImage(100, 100, BufferedImage.TYPE_4BYTE_ABGR).getGraphics();

    private class ExtendChar {
        private char contents;
        private Color color;
        private int size;
        private String font;
        private int width;

        private ExtendChar(char contents, Color color, int size, String font) {
            this.contents = contents;
            this.color = color;
            this.size = size;
            this.font = font;
            this.width = ExtendText.getWidth(contents, size, font);
        }

        private ExtendChar(char contents, Color color, int size, String font, int width) {
            this.contents = contents;
            this.color = color;
            this.size = size;
            this.font = font;
            this.width = width;
        }

        public char getContents() {
            return contents;
        }

        public void setContents(char contents) {
            this.contents = contents;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getFont() {
            return font;
        }

        public void setFont(String font) {
            this.font = font;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public void render(Graphics g, int x, int y) {
            g.setFont(new Font(font, Font.PLAIN, size));
            g.setColor(color);

            g.drawString("" + contents, x, y);
        }

        @Override
        public String toString() {
            String s = "ExtendChar: " + contents + "\n";
            s += "Color: " + color + "\n";
            s += "Font: " + font + "\n";
            s += "Size: " + size + "\n";
            s += "Width: " + width + "\n";

            return s;
        }
    }

    private static int getWidth(char c, int size, String font) {
        FontMetrics fm = g.getFontMetrics(Font.decode(font + "-" + size));
        return getWidth(c, fm);
    }

    private static int getWidth(char c, FontMetrics fm) {
        return fm.charWidth(c);
    }

    public ExtendText(String rawText) {
        this.contents = "";
        this.colorList = new LengthList<>();
        this.sizeList = new LengthList<>();
        this.fontList = new LengthList<>();
        this.contentsWidth = new ArrayList<>();
        resolveRawText(rawText);
    }

    public ExtendText(ExtendText src) {
        this.contents = src.contents;
        this.colorList = src.colorList.clone();
        this.sizeList = src.sizeList.clone();
        this.fontList = src.fontList.clone();
        this.contentsWidth = src.contentsWidth;
    }

    public ExtendText() {
        this.contents = "";
        this.colorList = new LengthList<>();
        this.sizeList = new LengthList<>();
        this.fontList = new LengthList<>();
        this.contentsWidth = new ArrayList<>();
    }

    private void resolveRawText(String rawText) {
        //TODO
        colorList.append(Database.getDefaultColor(), 0);
        fontList.append(Database.getDefaultFont(), 0);
        sizeList.append(Database.getDefaultSize(), 0);

        resolveText(rawText, 0);
    }

    private void resolveText(String text, int depth) {
        if (depth >= text.length()) return;

        char symbol = text.charAt(depth);

        if (symbol == '\\') {
            resolveCommand(text, depth + 1);
        } else {
            append(symbol);
            resolveText(text, depth + 1);
        }
    }

    private void resolveCommand(String text, int depth) {
        //TODO throw exception
        if (depth >= text.length()) return;

        char symbol = text.charAt(depth);

        switch (symbol) {
            case '\\':
                append('\\');
                resolveText(text, depth + 1);
                break;
            case 'c':
                resolveColorCommand(text, depth + 1);
                break;
            case 'C':
                resolveColorCommand(text, depth + 1);
                break;
            case 'f':
                resolveFontCommand(text, depth + 1);
                break;
            case 'F':
                resolveFontCommand(text, depth + 1);
                break;
            case 's':
                resolveSizeCommand(text, depth + 1);
                break;
            case 'S':
                resolveSizeCommand(text, depth + 1);
                break;
            default:
                //TODO throw error
        }
    }

    private void resolveColorCommand(String text, int depth) {
        int commandEnd = text.indexOf('}', depth + 1);
        String[] command = text.substring(depth + 1, commandEnd).split(",");

        Color color = null;
        if (command.length == 1) {
            color = getColorByName(command[0]);
        } else if (command.length == 3) {
            color = new Color(Integer.parseInt(command[0]), Integer.parseInt(command[1]), Integer.parseInt(command[2]));
        } else {
            //TODO throw error
            return;
        }

        colorList.append(color, 0);

        resolveText(text, commandEnd + 1);
    }

    private Color getColorByName(String name) {
        if (colorNameMap.containsKey(name)) {
            return (colorNameMap.get(name));
        } else {
            return Database.getDefaultColor();
        }
    }

    private void resolveFontCommand(String text, int depth) {
        int commandEnd = text.indexOf('}', depth + 1);
        String font = text.substring(depth + 1, commandEnd);

        fontList.append(font, 0);

        resolveText(text, commandEnd + 1);
    }

    private void resolveSizeCommand(String text, int depth) {
        int commandEnd = text.indexOf('}', depth + 1);
        int size = Integer.parseInt(text.substring(depth + 1, commandEnd));

        sizeList.append(size, 0);

        resolveText(text, commandEnd + 1);
    }

    public void append(String rawText) {
        this.append(new ExtendText(rawText));
    }

    public void append(char symbol) {
        contents += symbol;
        colorList.increaseFinal(1);
        sizeList.increaseFinal(1);
        fontList.increaseFinal(1);

        contentsWidth.add(getWidth(symbol, sizeList.getLastContent(), fontList.getLastContent()));
    }

    public void append(ExtendText text) {
        contents += text.contents;
        colorList.append(text.colorList);
        sizeList.append(text.sizeList);
        fontList.append(text.fontList);
        contentsWidth.addAll(text.contentsWidth);
    }

    public ExtendText add(String rawText) {
        return add(new ExtendText(rawText));
    }

    public ExtendText add(ExtendText text) {
        ExtendText sumText = new ExtendText(this);

        sumText.append(text);

        return sumText;
    }

    public void insertString(int index, String text) {
        int textLength = text.length();
        contents = contents.substring(0, index) + text + contents.substring(index);
        colorList.increseIndices(textLength, colorList.firstOver(index));
        fontList.increseIndices(textLength, fontList.firstOver(index));
        sizeList.increseIndices(textLength, sizeList.firstOver(index));

        ExtendText textForWidth = new ExtendText(text);
        contentsWidth.addAll(index, textForWidth.contentsWidth);
    }

    public void remove(int index) {
        removeAll(index, index + 1);
    }

    public void removeAll(int beginIndex, int endIndex) {
        //TODO
    }

    public void removeAll(int startIndex) {
        removeAll(startIndex, length());
    }

    public void removeAllBy(int endIndex) {
        removeAll(0, endIndex);
    }

    public ExtendText subText(int beginIndex, int endIndex) {
        //TODO
        return null;
    }

    public ExtendText subText(int beginIndex) {
        //TODO
        return null;
    }

    /**
     * Note: Slow. Use {@link #nextChar()} instead if possible.
     *
     * @param index
     * @return the extendChar
     */
    public ExtendChar charAt(int index) {
        char c = contents.charAt(index);
        Color color = colorList.contentsOf(index);
        String font = fontList.contentsOf(index);
        int size = sizeList.contentsOf(index);

        ExtendChar exChar = new ExtendChar(c, color, size, font);

        return exChar;
    }

    public ExtendChar firstChar() {
        //TODO


        return null;
    }

    public ExtendChar nextChar() {
        //TODO
        return null;
    }

    public int length() {
        return contents.length();
    }

    @Override
    public ExtendText clone() {
        return (new ExtendText(this));
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof ExtendText) {
            return (this.contents.equals(((ExtendText) other).contents) &&
                    this.fontList.equals(((ExtendText) other).fontList) &&
                    this.colorList.equals(((ExtendText) other).colorList) &&
                    this.sizeList.equals(((ExtendText) other).sizeList));
        }
        return false;
    }

    @Override
    public String toString() {
        String result = "";

        result += contents + "\n";
        result += "Font: \n";
        result += fontList.toString() + "\n";
        result += "Color: \n";
        result += colorList.toString() + "\n";
        result += "Size: \n";
        result += sizeList.toString() + "\n";
        result += "Width: \n";
        result += contentsWidth.toString() + "\n";

        return result;
    }
}
