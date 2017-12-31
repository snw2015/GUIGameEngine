package card;

import static snw.engine.core.Engine.*;

public class Card {
    public static void main(String[] args) {
        initialize();
        setTitle("Cards are in pairs, man alone");
        setProperty("card_type_num", "4");
        setProperty("card_rank_num", "3");
        setProperty("images_path", "file/image/card/");
        setProperty("sounds_path", "file/audio/card/");

        addState("main_board", Panel.class);
        loadState("main_board");
        setSEVol(0.5f);

        start();
    }
}