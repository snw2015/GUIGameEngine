package card;

import static snw.engine.core.Engine.*;

public class Card {
    public static void main(String[] args) {
        initialize();
        setTitle("Cards are in pairs, man alone");
        setProperty("size","2000x1000");
        resize();

        setProperty("card_type_num", "4");
        setProperty("card_rank_num", "13");
        setProperty("card_width", "68");
        setProperty("card_height", "90");

        setProperty("images_path", "file/image/card/");
        setProperty("sounds_path", "file/audio/card/");

        addState("main_board", Panel.class);
        loadState("main_board");
        setSEVol(0.5f);

        start();
    }
}