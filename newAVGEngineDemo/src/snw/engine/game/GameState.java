package snw.engine.game;

import snw.engine.component.Component;

import static snw.engine.debug.Logger.*;

public class GameState {
    private final String name;
    private final String className;
    private final int type;

    private Component component = null;

    public Component getComponent() {
        return component;
    }

    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_LOAD = 1;
    public static final int TYPE_NOP = 2;


    public GameState(String name, String className) {
        this(name, className, TYPE_NORMAL);
    }

    public GameState(String name, Class c) {
        this(name, c, TYPE_NORMAL);
    }

    public GameState(String name, Class c, int type) {
        this(name, c.getName(), type);
    }

    public GameState(String name, String className, int type) {
        this.name = name;
        this.className = className;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public String getTypeString() {
        switch (type) {
            case TYPE_NORMAL:
                return "Type Normal";
            case TYPE_LOAD:
                return "Type Load";
            case TYPE_NOP:
                return "Type Nop";
            default:
                return "Unknown Type";
        }
    }

    void load(String msg) {
        if (component == null) {
            Class componentClass = null;
            try {
                componentClass = Class.forName(className);
                component = (Component) componentClass.newInstance();
            } catch (InstantiationException e) {
                println("The component must have a non-parametric constructor.");
                e.printStackTrace();
            } catch (Exception e) {
                //TODO
                e.printStackTrace();
            }

            switch (type) {
                case GameState.TYPE_LOAD:
                    Game.getInstance().setLoading(component);
                case GameState.TYPE_NORMAL:
                    component.load(msg);
                case GameState.TYPE_NOP:
                    // Do nothing
            }
        } else {
            //TODO error handling
            println("Cannot load an active state.");
        }
    }

    void resume(String msg) {
        if (component != null) {
            switch (type) {
                case GameState.TYPE_LOAD:
                    Game.getInstance().setLoading(component);
                case GameState.TYPE_NORMAL:
                    component.resume(msg);
                case GameState.TYPE_NOP:
                    // Do nothing
            }
        } else {
            //TODO error handling
            println("Cannot resume an inactive state.");
        }

    }

    void release(String msg) {
        if (component != null) {
            switch (type) {
                case GameState.TYPE_LOAD:
                    Game.getInstance().setLoading(null);
                case GameState.TYPE_NORMAL:
                    component.release(msg);
                case GameState.TYPE_NOP:
                    // Do nothing
            }
            component = null;
        } else {
            //TODO error handling
            println("Cannot release an inactive state.");
        }
    }

    void suspend(String msg) {
        if (component != null) {
            switch (type) {
                case GameState.TYPE_LOAD:
                    Game.getInstance().setLoading(null);
                case GameState.TYPE_NORMAL:
                    component.suspend(msg);
                case GameState.TYPE_NOP:
                    // Do nothing
            }
        } else {
            //TODO error handling
            println("Cannot suspend an inactive state.");
        }
    }

    @Override
    public String toString() {
        return "State " + name + ", type: " + getTypeString() + ", class: " + className;
    }
}