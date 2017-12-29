package snw.engine.core;

import snw.engine.component.Component;
import snw.engine.component.Graphic;
import snw.engine.component.TopLevelComponent;
import snw.engine.componentAVG.MainGameScreenC;
import snw.engine.database.EngineData;
import snw.engine.database.EngineProperties;
import snw.engine.database.ImageBufferData;
import snw.engine.database.Reloadable;
import snw.engine.game.Game;
import snw.engine.game.GameState;
import snw.file.FileIOHelper;
import snw.math.VectorInt;
import snw.tests.TestAll;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public final class Engine {
    private static Game GAME;
    private static ImageBufferData IMAGE_BUFFER_DATA;
    private static EngineProperties ENGINE_PROPERTIES;
    private static EngineData ENGINE_DATA;

    /*
      File I/O methods
     */
    public static File readFile(String filePath) {
        return FileIOHelper.readFile(filePath);
    }

    public static BufferedReader getFileReader(String filePath) {
        return FileIOHelper.getFileReader(filePath);
    }

    public static String[] readFileStrArr(String filePath) {
        return FileIOHelper.readFileStrArr(filePath);
    }

    public static String readFileStr(String filePath) {
        return FileIOHelper.readFileStr(filePath);
    }

    public static BufferedWriter getFileWriter(String filePath) {
        return FileIOHelper.getFileWriter(filePath);
    }

    public static boolean writeFile(String filePath, Object... contents) {
        return FileIOHelper.writeFile(filePath, contents);
    }



    /*
     Game methods
     */

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

    public static void clearGame() {
        getGame().clear();
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


    /*
     ImageBufferData methods
     */

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

    public static void clearImageBufferData() {
        getImageBufferData().clear();
    }

    /*
     EngineProperties methods
     */

    public static EngineProperties getEngineProperties() {
        if (ENGINE_PROPERTIES == null) {
            ENGINE_PROPERTIES = EngineProperties.getInstance();
        }
        return ENGINE_PROPERTIES;
    }

    public static void clearEngineProperties() {
        getEngineProperties().clear();
    }

    public static String[] getProperties() {
        return getEngineProperties().getAllProperties();
    }

    public static String getPropertiesStr() {
        return getEngineProperties().getAllPropertiesStr();
    }

    public static String[] getDefaultProperties() {
        return getEngineProperties().getDefaultProperties();
    }

    public static String getDefaultPropertiesStr() {
        return getEngineProperties().getDefaultPropertiesStr();
    }

    public static String getProperty(String name) {
        return getEngineProperties().get(name);
    }

    public static String getDefaultProperty(String name) {
        return getEngineProperties().getDefault(name);
    }

    public static boolean setProperty(String name, String value) {
        return getEngineProperties().set(name, value);
    }

    public static boolean loadProperty(String propertyStr) {
        return getEngineProperties().load(propertyStr);
    }

    public static boolean loadProperties(String... propertiesStr) {
        return getEngineProperties().loadAll(propertiesStr);
    }

    public static boolean loadProperties(String propertiesStr) {
        return getEngineProperties().loadAll(propertiesStr);
    }

    public static boolean removeProperty(String name) {
        return getEngineProperties().remove(name);
    }

    public static void readPropertiesFile(String fileName) {
        getEngineProperties().readPropertiesFile(fileName);
    }

    public static void reloadProperties() {
        getEngineProperties().readPropertiesFile();
    }

    public static void savePropertiesFile(String fileName) {
        getEngineProperties().savePropertiesFile(fileName);
    }

    public static void saveProperties() {
        getEngineProperties().savePropertiesFile();
    }


    /*
     EngineData methods
     */
    public static EngineData getEngineData() {
        if (ENGINE_DATA == null) {
            ENGINE_DATA = EngineData.getInstance();
        }
        return ENGINE_DATA;
    }

    public static void clearEngineData() {
        getEngineData().clear();
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

    public static boolean saveDataFile(String fileName) {
        return writeFile(getProperty("data_path") + fileName + getProperty("data_form"), getEngineData().getAllDataInfoStr());
    }

    public static boolean saveDataFile(String fileName, String... dataNames) {
        return writeFile(getProperty("data_path") + fileName + getProperty("data_form"), getEngineData().getDataInfoStr(dataNames));
    }

    public static boolean loadDataFile(String fileName) {
        String dataList = readFileStr(getProperty("data_path") + fileName + getProperty("data_form"));
        if (dataList == null) return false;
        else return loadDataListStr(dataList);
    }

    public static boolean saveUserData(String fileName, Reloadable data) {
        return writeFile(getProperty("user_data_path") + fileName + getProperty("user_data_form"), data.getClass().getName() + ": " + data.save());
    }

    public static Reloadable loadUserDataStr(String dataContent) {
        String[] list = dataContent.split(":", 2);
        String className = list[0].trim();
        String reloadData = list[1].trim();
        Class c = null;
        try {
            c = Class.forName(className);
        } catch (ClassNotFoundException e) {
            //TODO
            e.printStackTrace();
        }

        Reloadable obj = null;
        try {
            obj = (Reloadable) c.newInstance();
        } catch (InstantiationException e) {
            //TODO
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        obj.reload(reloadData);
        return obj;
    }

    public static Reloadable loadUserData(String fileName) {
        return loadUserDataStr(readFileStr(getProperty("user_data_path") + fileName + getProperty("user_data_form")));
    }

    public static void clearEngine() {
        clearGame();
        clearImageBufferData();
        clearEngineData();
        clearEngineProperties();
    }

    public static void main(String[] args) {
        TestAll test = (TestAll) loadUserData("test");
        System.out.println(test);
    }
}