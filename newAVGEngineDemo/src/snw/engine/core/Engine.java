package snw.engine.core;

import snw.engine.audio.AudioManager;
import snw.engine.component.Component;
import snw.engine.component.TopLevelComponent;
import snw.engine.database.*;
import snw.engine.frame.EngineFrame;
import snw.engine.game.Game;
import snw.engine.game.GameState;
import snw.file.FileIOHelper;
import snw.math.VectorInt;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.*;
import java.util.HashMap;

public final class Engine {
    private static Game GAME;
    private static ImageBufferData IMAGE_BUFFER_DATA;
    private static AudioBufferData AUDIO_BUFFER_DATA;
    private static AudioManager AUDIO_MANAGER;
    private static EngineProperties ENGINE_PROPERTIES;
    private static EngineData ENGINE_DATA;
    private static EngineFrame ENGINE_FRAME;

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
    public synchronized static Game getGame() {
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
        setSize(size.x, size.y);
    }

    public static void setSize(int width, int height) {
        getPanel().setSize(width, height);
        getFrame().resize();
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

    public static void addState(String name, Class panelClass) {
        addState(new GameState(name, panelClass));
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
    public synchronized static ImageBufferData getImageBufferData() {
        if (IMAGE_BUFFER_DATA == null) {
            IMAGE_BUFFER_DATA = ImageBufferData.getInstance();
        }
        return IMAGE_BUFFER_DATA;
    }

    /**
     * decorative method
     *
     * @see ImageBufferData#store(String name)
     */
    public static boolean storeImage(String name) {
        return getImageBufferData().store(name);
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
     */
    public static boolean storeImage(String name, Image image) {
        return getImageBufferData().store(name, image);
    }

    /**
     * decorative method
     *
     * @see ImageBufferData#release(String name)
     */
    public static boolean releaseImage(String name) {
        return getImageBufferData().release(name);
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
     * @see ImageBufferData#get(String name)
     */
    public static Image getImage(String name) {
        return getImageBufferData().get(name);
    }

    public static Image[] getImages(String... names) {
        Image[] images = new Image[names.length];
        for (int i = 0; i < names.length; i++) {
            images[i] = getImage(names[i]);
        }
        return images;
    }

    /**
     * decorative method
     *
     * @see ImageBufferData#attain(String name)
     */
    public static Image attainImage(String name) {
        return getImageBufferData().attain(name);
    }

    public static void clearImageBufferData() {
        getImageBufferData().clear();
    }


    /*
     AudioBufferData methods
     */
    public synchronized static AudioBufferData getAudioBufferData() {
        if (AUDIO_BUFFER_DATA == null) {
            AUDIO_BUFFER_DATA = AudioBufferData.getInstance();
        }
        return AUDIO_BUFFER_DATA;
    }

    public static boolean storeAudio(String name) {
        return getAudioBufferData().store(name);
    }

    public static boolean storeAudio(String... names) {
        boolean b = true;
        for (String name : names) {
            b = b && storeAudio(name);
        }
        return b;
    }

    public static boolean storeAudio(String name, Clip clip) {
        return getAudioBufferData().store(name, clip);
    }

    public static boolean releaseAudio(String name) {
        return getAudioBufferData().release(name);
    }

    public static boolean releaseAudio(String... names) {
        boolean b = true;
        for (String name : names) {
            b = b && releaseAudio(name);
        }
        return b;
    }

    public static Clip getClip(String name) {
        return getAudioBufferData().get(name);
    }

    public static Clip[] getClips(String... names) {
        Clip[] clips = new Clip[names.length];
        for (int i = 0; i < names.length; i++) {
            clips[i] = getClip(names[i]);
        }
        return clips;
    }

    public static Clip attainClip(String name) {
        return getAudioBufferData().attain(name);
    }

    public static void clearAudioBufferData() {
        getAudioBufferData().clear();
    }


    /*
     AudioManager methods
     */

    public synchronized static AudioManager getAudioManager() {
        if (AUDIO_MANAGER == null) {
            AUDIO_MANAGER = AudioManager.getInstance();
        }
        return AUDIO_MANAGER;
    }

    public static float getBGMVol() {
        return getAudioManager().getBGMVol();
    }

    public static void setBGMVol(float BGMVol) {
        getAudioManager().setBGMVol(BGMVol);
    }

    public static float getSEVol() {
        return getAudioManager().getSEVol();
    }

    public static void setSEVol(float SEVol) {
        getAudioManager().setSEVol(SEVol);
    }

    public static float getMasterVol() {
        return getAudioManager().getMasterVol();
    }

    public static void setMasterVol(float masterVol) {
        getAudioManager().setMasterVol(masterVol);
    }

    public static String getCurrentBGM() {
        return getAudioManager().getCurrentBGM();
    }

    public static void playBGM(String name) {
        getAudioManager().playBGM(name);
    }

    public static void playBGM(String name, int loopTime) {
        getAudioManager().playBGM(name, loopTime);
    }

    public static void stopBGM() {
        getAudioManager().stopBGM();
    }

    public static void stopBGM(String name) {
        getAudioManager().stopBGM(name);
    }

    public static void fadeInBGM(String name) {
        getAudioManager().fadeInBGM(name);
    }

    public static void fadeInBGM(String name, float speed) {
        getAudioManager().fadeInBGM(name, speed);
    }

    public static void fadeOutBGM() {
        getAudioManager().fadeOutBGM();
    }

    public static void fadeOutBGM(String name) {
        getAudioManager().fadeOutBGM(name);
    }

    public static void fadeOutBGM(float speed) {
        getAudioManager().fadeOutBGM(speed);
    }

    public static void fadeOutBGM(String name, float speed) {
        getAudioManager().fadeOutBGM(name, speed);
    }

    public static void playSE(String name) {
        getAudioManager().playSE(name);
    }

    public static void playSE(String name, int loopTime) {
        getAudioManager().playSE(name, loopTime);
    }

    public static void stopSE(String name) {
        getAudioManager().stopSE(name);
    }


    /*
     EngineProperties methods
     */

    public synchronized static EngineProperties getEngineProperties() {
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

    public static int getPropertyInt(String name) {
        return Integer.parseInt(getProperty(name).trim());
    }

    public static double getPropertyDouble(String name) {
        return Double.parseDouble(getProperty(name).trim());
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
    public synchronized static EngineData getEngineData() {
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


    /*
     EngineFrame methods
     */

    //TODO thread security
    public synchronized static EngineFrame getFrame() {
        if (ENGINE_FRAME == null) {
            ENGINE_FRAME = new EngineFrame();
        }
        return ENGINE_FRAME;
    }

    public static void setDecorated(boolean decorated) {
        getFrame().setUndecorated(!decorated);
    }

    public static void setCursor() {
        //TODO
    }

    public static void setVisible(boolean visible) {
        getFrame().setVisible(visible);
    }

    public static void setEnable(boolean enable) {
        getFrame().setEnabled(true);
    }

    public static void setTitle(String title) {
        getFrame().setTitle(title);
    }

    /*
     Timer kits
     */
    public static void sleep(long timeSecond) {
        sleepMs(timeSecond * 1000);
    }

    public static void sleepMs(long timeMilliSecond) {
        try {
            Thread.sleep(timeMilliSecond);
        } catch (InterruptedException e) {
            //TODO
            e.printStackTrace();
        }
    }


    private static long tickTimer = 0;

    public static long tick() {
        return tickMs() / 1000;
    }

    public static long tickMs() {
        if (tickTimer != 0) return -1;

        tickTimer = System.currentTimeMillis();
        return tickTimer;
    }

    public static long tock() {
        return tockMs() / 1000;
    }

    public static long tockMs() {
        if (tickTimer <= 0) return -1;

        long time = System.currentTimeMillis() - tickTimer;
        tickTimer = 0;
        return time;
    }


    public static Thread runNewThread(Runnable runnable) {
        Thread t = new Thread(runnable);
        t.start();
        return t;
    }

    public static Thread runNewLoop(Runnable runnable, long timeMillis) {
        Thread t = new Thread(() -> {
            while (true) {
                runnable.run();
                sleepMs(timeMillis);
            }
        });
        t.start();
        return t;
    }

    public static Thread runNewLoop(Runnable runnable) {
        Thread t = new Thread(() -> {
            while (true) {
                runnable.run();
            }
        });
        t.start();
        return t;
    }

    /*
     Engine methods
     */
    public static void clearEngine() {
        clearGame();
        clearImageBufferData();
        clearAudioBufferData();
        clearEngineData();
        clearEngineProperties();
    }

    public static int getFPS() {
        return getPropertyInt("fps");
    }

    public static int getWidth() {
        return Integer.parseInt(getProperty("size").split("x|X")[0].trim());
    }

    public static int getHeight() {
        return Integer.parseInt(getProperty("size").split("x|X")[1].trim());
    }

    public static void startPainting() {
        Thread threadPaint = new Thread(() -> {
            int delay = 1000 / getFPS();
            while (true) {
                sleepMs(delay);
                getFrame().repaint();
            }
        });
        threadPaint.start();
    }

    public static void startUpdating() {
        Thread threadUpdate = new Thread(() -> {
            int delay = 1000 / getFPS();
            while (true) {
                sleepMs(delay);
                getPanel().update();
            }
        });
        threadUpdate.start();
    }

    public static void initialize() {
        clearEngine();
        System.setProperty("sun.java2d.opengl", "true");
    }

    public static void start() {
        startUpdating();
        startPainting();
        setEnable(true);
        setVisible(true);
    }

    public static void exit() {
        System.exit(0);
    }

    public static void main(String[] args) {
        //TEST
    }

}