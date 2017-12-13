package snw.engine.game;

import snw.engine.animation.AnimationData;
import snw.engine.component.Button;
import snw.engine.component.Component;
import snw.engine.component.TopLevelComponent;
import snw.engine.componentAVG.MainGameScreenC;
import snw.engine.componentAVG.MainMenuC;
import snw.math.VectorInt;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static snw.engine.debug.Log.*;

public class Game {
    static final private Game game = new Game();

    //TODO database, you know
    private static final int DEFAULT_WIDTH = 1680;
    private static final int DEFAULT_HEIGHT = 1050;
    final private TopLevelComponent panel = new TopLevelComponent("topPanel", 0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);

    final private HashMap<String, GameState> stateMap = new HashMap<>();

    private Component loadingComponent = null;

    private Game() {
    }

    static Game getInstance() {
        return game;
    }

    public static TopLevelComponent getPanel() {
        return game.panel;
    }

    public static void setSize(VectorInt size) {
        getPanel().setSize(size);
    }

    public static void setSize(int width, int height) {
        getPanel().setSize(width, height);
    }

    public void setLoading(Component loading) {
        loadingComponent = loading;
    }

    public Component getLoading() {
        return loadingComponent;
    }

    public void addStates(GameState... states) {
        for (GameState state : states) {
            addState(state);
        }
    }

    public void addState(GameState state) {
        stateMap.put(state.getName(), state);
    }

    public void removeAllStates() {
        for (String name : stateMap.keySet()) {
            removeState(name);
        }
    }

    public void removeState(String name) {
        if (stateMap.containsKey(name)) {
            panel.remove(getState(name).getComponent());
            releaseState(name);
            stateMap.remove(name);
        }
    }

    private GameState getState(String name) {
        return stateMap.get(name);
    }

    public boolean loadState(String name) {
        return loadState(name, "");
    }

    public boolean loadState(String name, String msg) {
        GameState state = getState(name);
        if (state == null) return false;
        state.load(msg);
        return true;
    }

    public boolean resumeState(String name) {
        return resumeState(name, "");
    }

    public boolean resumeState(String name, String msg) {
        GameState state = getState(name);
        if (state == null) return false;
        state.resume(msg);
        return true;
    }


    public boolean releaseState(String name) {
        return releaseState(name, "");
    }

    public boolean releaseState(String name, String msg) {
        GameState state = getState(name);
        if (state == null) return false;
        state.release(msg);
        return true;
    }

    public boolean suspendState(String name) {
        return suspendState(name, "");
    }

    public boolean suspendState(String name, String msg) {
        GameState state = getState(name);
        if (state == null) return false;
        state.suspend(msg);
        return true;
    }

    public boolean showState(String name) {
        GameState state = getState(name);
        if (state == null) return false;
        panel.add(state.getComponent(), state.getType() == GameState.TYPE_LOAD ? 1 : 0);
        return true;
    }

    public boolean hideState(String name) {
        //TODO
        GameState state = getState(name);
        if (state == null) return false;
        panel.remove(state.getComponent());
        return true;
    }

    public String[] getStateList() {
        String[] list = new String[stateMap.size()];

        int i = 0;
        for (GameState state : stateMap.values()) {
            list[i++] = state.toString();
        }

        return list;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame() {
            @Override
            public void paint(Graphics g) {
                println(game.panel.getSubs());
                game.panel.render((Graphics2D) g, new AnimationData(AffineTransform.getTranslateInstance(0, 0)));
            }
        };
        frame.setBounds(0, 0, 2000, 1200);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        game.addState(new GameState("1", MainMenuC.class));
        game.addState(new GameState("2", MainGameScreenC.class));

        game.loadState("1");
        game.showState("1");

        frame.repaint();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        game.hideState("1");
        frame.repaint();
    }
}