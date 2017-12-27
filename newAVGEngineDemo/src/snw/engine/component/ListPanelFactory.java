package snw.engine.component;

public class ListPanelFactory {

    public static final int NO_LOOP = 0;
    public static final int ALL_LOOP = 1;
    public static final int HORIZONTAL_LOOP = 2;
    public static final int VERTICAL_LOOP = 3;
    public static final int HORIZONTAL_CON = 4;
    public static final int HORIZONTAL_CON_LOOP = 5;
    public static final int HORIZONTAL_CON_LOOP_LINE = 6;
    public static final int VERTICAL_CON = 7;
    public static final int VERTICAL_CON_LOOP = 8;
    public static final int VERTICAL_CON_LOOP_COL = 9;

    public static ListPanel getHorizontalInstance(String name, int x, int y, int width, int height,
                                                  Component[] contents, boolean isLoop) {
        ListPanel panel = new ListPanel(name, x, y, width, height);
        int size = contents.length;
        if (size > 1) {
            for (int i = 0; i < size - 1; i++) {
                panel.add(contents[i], ListPanel.RIGHT, contents[i + 1]);
                panel.setSuccess(contents[i + 1], ListPanel.LEFT, contents[i]);
            }
            panel.add(contents[size - 1]);
            if (isLoop) {
                panel.setSuccess(contents[size - 1], ListPanel.RIGHT, contents[0]);
                panel.setSuccess(contents[0], ListPanel.LEFT, contents[size - 1]);
            }
        } else {
            panel.add(contents[0]);
        }
        panel.setSuccess(null, ListPanel.RIGHT, contents[0]);
        panel.setSuccess(null, ListPanel.LEFT, contents[size - 1]);
        return (panel);
    }

    public static ListPanel getHorizontalInstance(String name, int x, int y, int width, int height,
                                                  Component[] contents) {
        return (getHorizontalInstance(name, x, y, width, height, contents, true));
    }

    public static ListPanel getVerticalInstance(String name, int x, int y, int width, int height, Component[] contents,
                                                boolean isLoop) {
        ListPanel panel = new ListPanel(name, x, y, width, height);
        int size = contents.length;
        if (size > 1) {
            for (int i = 0; i < size - 1; i++) {
                panel.add(contents[i], ListPanel.DOWN, contents[i + 1]);
                panel.setSuccess(contents[i + 1], ListPanel.UP, contents[i]);
            }
            panel.add(contents[size - 1]);
            if (isLoop) {
                panel.setSuccess(contents[size - 1], ListPanel.DOWN, contents[0]);
                panel.setSuccess(contents[0], ListPanel.UP, contents[size - 1]);
            }
        } else {
            panel.add(contents[0]);
        }
        panel.setSuccess(null, ListPanel.DOWN, contents[0]);
        panel.setSuccess(null, ListPanel.UP, contents[size - 1]);
        return (panel);
    }

    public static ListPanel getVerticalInstance(String name, int x, int y, int width, int height,
                                                Component[] contents) {
        return (getVerticalInstance(name, x, y, width, height, contents, true));
    }

    /**
     * loopMode: ALL_LOOP: loop from every edge to the opposite HORIZONTAL_LOOP:
     * link left to right VERTICAL_LOOP: link bottom to top HORIZONTAL_CON: link
     * right to next bottom HORIZONTAL_CON_LOOP: link right to next left and
     * bottom-right to top-left HORIZONTAL_CON_LOOP_LINE: link right to next
     * left, bottom-right to top-left and bottom to top VERTICAL_CON: link
     * bottom to next top VERTICAL_CON_LOOP: link link bottom to next top and
     * bottom-right to top-left VERTICAL_CON_LOOP_COL: link bottom to next top,
     * bottom-right to top-left and right to left
     */
    public static ListPanel getGridInstance(String name, int x, int y, int width, int height, Component[][] contents,
                                            int loopMode) {
        ListPanel panel = new ListPanel(name, x, y, width, height);
        int gridWidth = contents[0].length;
        int gridHeight = contents.length;
        for (int i = 0; i < gridHeight; i++) {
            for (int j = 0; j < gridWidth; j++) {
                panel.add(contents[i][j]);
                if (i > 0) {
                    setUndupleSuc(panel, contents[i][j], ListPanel.UP, contents[i - 1][j]);
                } else if (loopMode == ALL_LOOP || loopMode == VERTICAL_LOOP || loopMode == HORIZONTAL_CON_LOOP_LINE) {
                    setUndupleSuc(panel, contents[i][j], ListPanel.UP, contents[gridHeight - 1][j]);
                } else if (loopMode == VERTICAL_CON && j > 0) {
                    setUndupleSuc(panel, contents[i][j], ListPanel.UP, contents[gridHeight - 1][j - 1]);
                } else if (loopMode == VERTICAL_CON_LOOP || loopMode == VERTICAL_CON_LOOP_COL) {
                    setUndupleSuc(panel, contents[i][j], ListPanel.UP,
                            contents[gridHeight - 1][(j + gridWidth - 1) % gridWidth]);
                }
                if (i < gridHeight - 1) {
                    setUndupleSuc(panel, contents[i][j], ListPanel.DOWN, contents[i + 1][j]);
                } else if (loopMode == ALL_LOOP || loopMode == VERTICAL_LOOP || loopMode == HORIZONTAL_CON_LOOP_LINE) {
                    setUndupleSuc(panel, contents[i][j], ListPanel.DOWN, contents[0][j]);
                } else if (loopMode == VERTICAL_CON && j < gridWidth - 1) {
                    setUndupleSuc(panel, contents[i][j], ListPanel.DOWN, contents[0][j + 1]);
                } else if (loopMode == VERTICAL_CON_LOOP || loopMode == VERTICAL_CON_LOOP_COL) {
                    setUndupleSuc(panel, contents[i][j], ListPanel.DOWN, contents[0][(j + 1) % gridWidth]);
                }
                if (j > 0) {
                    setUndupleSuc(panel, contents[i][j], ListPanel.LEFT, contents[i][j - 1]);
                } else if (loopMode == ALL_LOOP || loopMode == HORIZONTAL_LOOP || loopMode == VERTICAL_CON_LOOP_COL) {
                    setUndupleSuc(panel, contents[i][j], ListPanel.LEFT, contents[i][gridWidth - 1]);
                } else if (loopMode == HORIZONTAL_CON && i > 0) {
                    setUndupleSuc(panel, contents[i][j], ListPanel.LEFT, contents[i - 1][gridWidth - 1]);
                } else if (loopMode == HORIZONTAL_CON_LOOP || loopMode == HORIZONTAL_CON_LOOP_LINE) {
                    setUndupleSuc(panel, contents[i][j], ListPanel.LEFT,
                            contents[(i - 1 + gridHeight) % gridHeight][gridWidth - 1]);
                }
                if (j < gridWidth - 1) {
                    setUndupleSuc(panel, contents[i][j], ListPanel.RIGHT, contents[i][j + 1]);
                } else if (loopMode == ALL_LOOP || loopMode == HORIZONTAL_LOOP || loopMode == VERTICAL_CON_LOOP_COL) {
                    setUndupleSuc(panel, contents[i][j], ListPanel.RIGHT, contents[i][0]);
                } else if (loopMode == HORIZONTAL_CON && i < gridHeight - 1) {
                    setUndupleSuc(panel, contents[i][j], ListPanel.RIGHT, contents[i + 1][0]);
                } else if (loopMode == HORIZONTAL_CON_LOOP || loopMode == HORIZONTAL_CON_LOOP_LINE) {
                    setUndupleSuc(panel, contents[i][j], ListPanel.RIGHT, contents[(i + 1) % gridHeight][0]);
                }
            }
        }

        panel.setSuccess(null, new Component[]{contents[gridHeight - 1][gridWidth - 1], contents[0][0],
                contents[gridHeight - 1][gridWidth - 1], contents[0][0]});

        return (panel);
    }

    public static ListPanel getGridInstance(String name, int x, int y, int width, int height, Component[][] contents) {
        return (getGridInstance(name, x, y, width, height, contents, NO_LOOP));
    }

    public static void setUndupleSuc(ListPanel panel, Component obj, int direction, Component suc) {
        if (obj != null && suc != null && obj != suc) {
            if (panel.getSuccess(obj, direction) == null) {
                panel.setSuccess(obj, direction, suc);
            }
        }
    }
}
