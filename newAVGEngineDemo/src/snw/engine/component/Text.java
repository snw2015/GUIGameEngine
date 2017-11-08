package snw.engine.component;

import java.awt.*;
import java.util.Iterator;

import snw.engine.animation.AnimationData;
import snw.math.VectorInt;
import snw.text.ExtensibleTextOld;

public class Text extends Component {

    private ExtensibleTextOld content;
    private int font;
    private int color;
    private int lineWidth;
    private boolean hasProcessed = false;
    private int renderLength;

    public Text(String name, String rawText, int x, int y, int width, int height) {
        super(name, x, y, width, height);
        content = new ExtensibleTextOld(rawText, 0, 0, width);
        renderLength = content.getLength();
        font = 0;
        color = 0;
        this.lineWidth = width;
    }

    @Override
    public void paint(Graphics2D g, AnimationData finalData) {

        if (!hasProcessed) {
            VectorInt renderBound = content.processPos(g);
            setSize(renderBound);
            hasProcessed = true;
        }

        Iterator<Integer> itrci = content.getColorIndex().iterator();
        Iterator<Color> itrc = content.getColors().iterator();
        Iterator<Integer> itrfi = content.getFontIndex().iterator();
        Iterator<Font> itrf = content.getFonts().iterator();

        int indexc = itrci.next();
        int indexf = itrfi.next();

        String string = content.getContent();
        VectorInt[] pos = content.getLetterPos();

        for (int i = 0; i < renderLength; i++) {
            if (indexc == i) {
                g.setColor(itrc.next());
                if (itrci.hasNext()) {
                    indexc = itrci.next();
                }
            }
            if (indexf == i) {
                g.setFont(itrf.next());
                if (itrfi.hasNext()) {
                    indexf = itrfi.next();
                }
            }

            drawString(g, string.substring(i, i + 1), pos[i].x, pos[i].y, finalData);
        }
    }

    private void drawString(Graphics2D g, String string, int x, int y, AnimationData finalData) {
        Point pSrc = new Point(x, y);
        Point pDst = new Point(0, 0);

        finalData.getTransformation().transform(pSrc, pDst);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, finalData.getAlphaFloat()));

        g.drawString(string, pDst.x, pDst.y);
        print(g.getClipBounds());
        print(string + "," + pDst.x + "," + pDst.y);
    }

    public void addString(String raw) {
        content.addString(raw);
        hasProcessed = false;
    }

    public void setString(String rawText) {
        content = new ExtensibleTextOld(rawText, 0, 0, width);
        setRenderLength(content.getLength());
        hasProcessed = false;
    }

    public ExtensibleTextOld getContent() {
        return content;
    }

    public void setContent(ExtensibleTextOld newContent) {
        content = newContent;
    }

    public int getFont() {
        return font;
    }

    public void setFont(int font) {
        this.font = font;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
        content.setLineWidth(lineWidth);
    }

    public int getRenderLength() {
        return renderLength;
    }

    public void setRenderLength(int renderLength) {
        this.renderLength = renderLength;
    }

    public boolean addRenderLength() {
        if (renderLength < content.getLength()) {
            setRenderLength(getRenderLength() + 1);
            return (true);
        }
        return (false);
    }
}
