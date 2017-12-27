package snw.engine.core;

import snw.engine.component.Component;
import snw.engine.component.Graphic;
import snw.engine.component.TopLevelComponent;
import snw.engine.componentAVG.MainGameScreenC;
import snw.engine.database.ImageBufferData;
import snw.engine.game.Game;
import snw.engine.game.GameState;
import snw.math.VectorInt;

import java.awt.*;

public final class Engine {
    private static Game GAME;
    private static ImageBufferData IMAGE_BUFFER_DATA;

    /**
     * Get the Game of this engine
     */
    public static Game getGame() {
        if (GAME == null) {
            GAME = Game.getInstance();
        }
        return GAME;
    }

    /**
     * Get the usable panel of this engine
     */
    public static TopLevelComponent getPanel() {
        return getGame().getPanel();
    }

    public static void refocusMouse() {
        getGame().refocusMouse();
    }

    public static void setSize(VectorInt size) {
        getPanel().setSize(size);
    }

    public static void setSize(int width, int height) {
        getPanel().setSize(width, height);
    }

    public static void setLoading(Component loading) {
        getGame().setLoading(loading);
    }

    public static Component getLoading() {
        return getGame().getLoading();
    }

    public static void addStates(GameState... states) {
        for (GameState state : states) {
            addState(state);
        }
    }

    public static void addState(GameState state) {
        getGame().addState(state);
    }

    public static void removeAllStates() {
        getGame().removeAllStates();
    }

    public static void removeState(String name) {
        getGame().removeState(name);
    }

    public static boolean loadState(String name) {
        return loadState(name, "");
    }

    public static boolean loadState(String name, String msg) {
        return getGame().loadState(name, msg);
    }

    public static boolean resumeState(String name) {
        return resumeState(name, "");
    }

    public static boolean resumeState(String name, String msg) {
        return getGame().resumeState(name, msg);
    }

    public static boolean releaseState(String name) {
        return releaseState(name, "");
    }

    public static boolean releaseState(String name, String msg) {
        return getGame().releaseState(name, msg);
    }

    public static boolean suspendState(String name) {
        return suspendState(name, "");
    }

    public static boolean suspendState(String name, String msg) {
        return getGame().suspendState(name, msg);
    }

    public static boolean showState(String name) {
        return getGame().showState(name);
    }

    public static boolean hideState(String name) {
        return getGame().hideState(name);
    }

    public static String[] getStateList() {
        return getGame().getStateListStr();
    }

    /**
     * Get the ImageBufferData of this engine
     */
    public static ImageBufferData getImageBufferData() {
        if (IMAGE_BUFFER_DATA == null) {
            IMAGE_BUFFER_DATA = ImageBufferData.getInstance();
        }
        return IMAGE_BUFFER_DATA;
    }

    /**
     * decorative method
     *
     * @see ImageBufferData#storeImage(String name)
     */
    public static boolean storeImage(String name) {
        return getImageBufferData().storeImage(name);
    }

    public static boolean storeImage(String... names) {
        boolean b = true;
        for (String name : names) {
            b = b && storeImage(name);
        }
        return b;
    }

    /**
     * decorative method
     *
     * @see ImageBufferData#storeImage(String name, Image image)
     */
    public static boolean storeImage(String name, Image image) {
        return getImageBufferData().storeImage(name, image);
    }

    /**
     * decorative method
     *
     * @see ImageBufferData#releaseImage(String name)
     */
    public static boolean releaseImage(String name) {
        return getImageBufferData().releaseImage(name);
    }

    public static boolean releaseImage(String... names) {
        boolean b = true;
        for (String name : names) {
            b = b && releaseImage(name);
        }
        return b;
    }

    /**
     * decorative method
     *
     * @see ImageBufferData#getImage(String name)
     */
    public static Image getImage(String name) {
        return getImageBufferData().getImage(name);
    }

    /**
     * decorative method
     *
     * @see ImageBufferData#attainImage(String name)
     */
    public static Image attainImage(String name) {
        return getImageBufferData().attainImage(name);
    }

    public static void main(String[] args) {
        //Test method
        System.out.println(getGame());
        addState(new GameState("1", MainGameScreenC.class, GameState.TYPE_LOAD));
        System.out.println(getGame());
        loadState("1");
        showState("1");
        System.out.println(getGame());
    }
}