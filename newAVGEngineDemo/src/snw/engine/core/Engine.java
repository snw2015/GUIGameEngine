package snw.engine.core;

import snw.engine.component.Component;
import snw.engine.component.Graphic;
import snw.engine.component.TopLevelComponent;
import snw.engine.componentAVG.MainGameScreenC;
import snw.engine.database.EngineData;
import snw.engine.database.EngineProperties;
import snw.engine.database.ImageBufferData;
import snw.engine.game.Game;
import snw.engine.game.GameState;
import snw.math.VectorInt;

import java.awt.*;
import java.util.HashMap;

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

    public static EngineProperties getEngineProperties() {
        return EngineProperties.getInstance();
    }

    public static HashMap<String, String> getEnginePropertiesMap() {
        return getEngineProperties().getProperties();
    }

    public static String[] getEnginePropertiesStr() {
        return getEngineProperties().getPropertiesStr();
    }

    public static String getEngineProperty(String name) {
        return getEngineProperties().get(name);
    }

    public static boolean setEngineProperty(String name, String value) {
        return getEngineProperties().set(name, value);
    }

    public static boolean loadEngineProperty(String propertyStr) {
        return getEngineProperties().load(propertyStr);
    }

    public static boolean loadEngineProperties(String... propertiesStr) {
        return getEngineProperties().loadAll(propertiesStr);
    }

    public static boolean loadEngineProperties(String propertiesStr) {
        return getEngineProperties().loadAll(propertiesStr);
    }

    public static boolean removeEngineProperty(String name) {
        return getEngineProperties().remove(name);
    }

    //TODO
    /*
    load file
    auto load (in Properties)
    reload
    save
     */


    public static EngineData getEngineData() {
        return EngineData.getInstance();

    }

    public static HashMap<String, Object> getDataMap() {
        return getEngineData().getDataMap();
    }

    public static String[] getDataMapList() {
        return getEngineData().getDataMapList();
    }

    public static String getDataMapStr() {
        return getEngineData().getDataMapStr();
    }

    public static Object getData(String name) {
        return getEngineData().get(name);
    }

    public static Object[] getData(String[] names) {
        return getEngineData().get(names);
    }

    public static boolean setData(String name, Object value) {
        return getEngineData().set(name, value);
    }

    public static boolean setData(String name, long value) {
        return getEngineData().set(name, value);
    }

    public static boolean setData(String name, int value) {
        return getEngineData().set(name, value);
    }

    public static boolean setData(String name, short value) {
        return getEngineData().set(name, value);
    }

    public static boolean setData(String name, char value) {
        return getEngineData().set(name, value);
    }

    public static boolean setData(String name, byte value) {
        return getEngineData().set(name, value);
    }

    public static boolean setData(String name, double value) {
        return getEngineData().set(name, value);
    }

    public static boolean setData(String name, float value) {
        return getEngineData().set(name, value);
    }

    public static boolean setData(String name, boolean value) {
        return getEngineData().set(name, value);
    }

    public static boolean setData(String[] names, Object[] values) {
        return getEngineData().set(names, values);
    }

    public static boolean loadData(String dataInfo) {
        return getEngineData().load(dataInfo);
    }

    public static boolean loadDataList(String... dataInfo) {
        return getEngineData().loadList(dataInfo);
    }

    public static boolean loadDataListStr(String dataInfoList) {
        return getEngineData().loadDataListStr(dataInfoList);
    }

    public static boolean removeData(String name) {
        return getEngineData().remove(name);
    }

    public static void removeAllData() {
        getEngineData().removeAll();
    }

    //TODO
    /*
     load file
     save file
     */


    public static void main(String[] args) {
        //Test method
        /*System.out.println(getGame());
        addState(new GameState("1", MainGameScreenC.class, GameState.TYPE_LOAD));
        System.out.println(getGame());
        loadState("1");
        showState("1");
        System.out.println(getGame());*/

//        System.out.println(getEngineProperties());
//
//        System.out.println(getEngineProperty("etc"));
//
//        System.out.println(loadEngineProperties("1:2", "2: 1231231"));
//
//        System.out.println(getEngineProperties());

        System.out.println(getEngineData());
        setData("hi", 12f);
        System.out.println(getEngineData().getAllDataInfoStr());
    }
}