package snw.tests;

import snw.engine.core.Engine;
import snw.engine.game.GameState;

public class EngineTests {
    public static void main(String[] args) {
        Engine.initialize();

        Engine.addState(new GameState("state1",TestPanel.class));

        Engine.loadState("state1");
        Engine.showState("state1");

        Engine.start();
    }
}
