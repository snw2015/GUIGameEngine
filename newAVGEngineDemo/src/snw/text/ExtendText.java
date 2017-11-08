package snw.text;

import snw.text.structure.LengthList;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ExtendText implements Cloneable {
    private String contents;
    private LengthList<Integer> colorList;
    private LengthList<Integer> sizeList;
    private LengthList<Integer> fontList;
    private ArrayList<Integer> contentsWidth;
    private int next = 0;
    //TODO default width and height
    private static final Graphics g = new BufferedImage(100, 100, BufferedImage.TYPE_4BYTE_ABGR).getGraphics();

    private class ExtendChar {
        private char contents;
        private Color color;
        private int size;
        private Font font;
        private int width;

        private ExtendChar(char contents, Color color, int size, Font font) {
            this.contents = contents;
            this.color = color;
            this.size = size;
            this.font = font;
            this.width = ExtendText.getWidth(contents, size, font);
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

        public Font getFont() {
            return font;
        }

        public void setFont(Font font) {
            this.font = font;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }

    private static int getWidth(char c, int size, Font font) {
        FontMetrics fm = g.getFontMetrics(font);
        return getWidth(c, size, fm);
    }

    private static int getWidth(char c, int size, FontMetrics fm) {
        return fm.charWidth(c);
    }

    public ExtendText(String rawText) {
        //TODO
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

    public void append(String rawText) {
        this.append(new ExtendText(rawText));
    }

    public void append(ExtendText text) {
        this.contents += text.contents;
        this.colorList.append(text.colorList);
        this.sizeList.append(text.sizeList);
        this.fontList.append(text.fontList);
        this.contentsWidth.addAll(text.contentsWidth);
    }

    public void append(ExtendChar extendChar) {
        //TODO
    }

    public ExtendText add(String rawText) {
        //TODO
        return null;
    }

    public ExtendText add(ExtendText text) {
        //TODO
        return null;
    }

    public ExtendText add(ExtendChar extendChar) {
        //TODO
        return null;
    }

    public void insert(int index, String rawText) {
        //TODO
    }

    public void insert(int index, ExtendText text) {
        //TODO
    }

    public void remove(int index) {
        //TODO
    }

    public void removeAll(int startIndex, int endIndex) {
        //TODO
    }

    public void removeAll(int startIndex) {
        removeAll(startIndex, length());
    }

    public ExtendText subText(int beginIndex, int endIndex) {
        //TODO
        return null;
    }

    public ExtendText subText(int beginIndex) {
        return subText(beginIndex, length());
    }

    /**
     * Note: Slow. Use {@link #nextChar()} instead if possible.
     *
     * @param index
     * @return
     */
    public ExtendChar charAt(int index) {
        //TODO
        return null;
    }

    public ExtendChar firstChar() {
        //TODO
        return null;
    }

    public ExtendChar nextChar() {
        //TODO
        return null;
    }

    public ExtendText restChars() {
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
}
