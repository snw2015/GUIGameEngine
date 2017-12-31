package card;

import snw.engine.component.Button;
import snw.engine.component.Component;
import snw.engine.component.Graphic;
import snw.engine.component.demo.normalPanel;
import snw.engine.core.Engine;
import snw.math.VectorInt;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

public class Panel extends normalPanel {
    private int cardTypeNum;
    private int cardRankNum;
    private Button[][] cards;
    private VectorInt[][] positions;
    private VectorInt cardSize;

    private int state;

    public static final int PRIORITY_BUTTON = 5;
    public static final int PRIORITY_CARD = 3;
    public static final int PRIORITY_BOARD = 1;

    public static final int STATE_STOP = 0;
    public static final int STATE_CARD0 = 1;
    public static final int STATE_CARD1 = 2;
    public static final int STATE_CARD2 = 3;


    private Button last = null;
    private int lastRank;
    private int numLast;

    @Override
    public void load(String msg) {
        System.out.println("loading");
        initialize();
        System.out.println("initializing");
        setCards();
        setBoard();
        setBoardButton();
        System.out.println("finished load");

        Engine.showState("main_board");
    }

    private void setCards() {
        Random rnd = new Random();

        cards = new Button[cardTypeNum][cardRankNum];
        positions = new VectorInt[cardTypeNum][cardRankNum];

        double collideDis = (cardSize.x + cardSize.y) * (cardSize.x + cardSize.y);

        for (int i = 1; i <= cardTypeNum; i++) {
            for (int j = 1; j <= cardRankNum; j++) {
                String cardName = "card" + "_" + i + "_" + j;
                Button card = new Button(cardName, 0, 0, cardSize.x, cardSize.y, getImage("card_back"));
                Engine.storeImage(cardName);
                card.setAlignment(Component.ALIGNMENT_CENTER);

                int finalJ = j;
                card.setReactionClicked((VectorInt pos) -> {
                    switch (state) {
                        case STATE_CARD0:
                            card.setBackground(getImage(cardName));
                            last = card;
                            lastRank = finalJ;
                            state = STATE_CARD1;
                            Engine.playSE("flip");
                            break;
                        case STATE_CARD1:
                            if (card != last) {
                                card.setBackground(getImage(cardName));
                                state = STATE_CARD2;
                                Engine.playSE("flip");
                                Engine.sleepMs(500);
                                if (lastRank == finalJ) {
                                    Engine.playSE("correct");
                                    Engine.sleep(3);
                                    remove(last);
                                    remove(card);
                                    numLast -= 2;
                                } else {
                                    Engine.playSE("wrong");
                                    Engine.sleep(3);
                                }
                                last.setBackground(getImage("card_back"));
                                card.setBackground(getImage("card_back"));
                                state = STATE_CARD0;
                                checkWin();
                            }
                            break;
                        case STATE_CARD2:
                        case STATE_STOP:
                        default:
                    }
                });

                Graphic effect = new Graphic("effect", getImage("card_effect"), cardSize.x / 2, cardSize.y / 2);
                effect.setAlignment(ALIGNMENT_CENTER);
                card.setReactionIn((VectorInt pos) -> {
                    card.add(effect, 1);
                });
                card.setReactionOut((VectorInt pos) -> {
                    card.remove(effect);
                });


                cards[i - 1][j - 1] = card;
                VectorInt position = getNextPos(rnd, getWidth() * 9 / 10, (getHeight() - 100) * 9 / 10, collideDis).add(new VectorInt(getWidth() / 20, (getHeight() - 100) / 20));
                positions[i - 1][j - 1] = position;
            }
        }
    }

    private void checkWin() {
        if (numLast <= 0) {
            System.out.println("Win!");
            Engine.playSE("win");
            state = STATE_STOP;
        }
    }

    private VectorInt getNextPos(Random rnd, int width, int height, double dis) {
        VectorInt pos = new VectorInt(rnd.nextInt(width), rnd.nextInt(height));
        while (collide(pos, dis)) {
            pos = new VectorInt(rnd.nextInt(width), rnd.nextInt(height));
        }
        return pos;
    }

    private boolean collide(VectorInt pos, double dis) {
        for (VectorInt[] posTypes : positions) {
            for (VectorInt posOther : posTypes) {
                if (posOther == null) return false;
                if (posOther.distance2(pos) <= dis) return true;
            }
        }
        return false;
    }

    private void setBoard() {
        Graphic board = new Graphic("board", getImage("background"), 0, 0, getWidth(), getHeight());
        add(board, PRIORITY_BOARD);
    }

    private void setBoardButton() {
        Button start = new Button("start_button", getWidth() / 2, getHeight() - 50, 180, 60,
                getImage("button1"), "start");
        start.setAlignment(Component.ALIGNMENT_BOTTOMMID);

        start.setReactionClicked((VectorInt pos) -> {
            if (state == STATE_STOP) {
                putCards();
                state = STATE_CARD0;
            }
        });
        start.setReactionIn((VectorInt pos) -> {
            start.setBackground(getImage("button2"));
        });
        start.setReactionOut((VectorInt pos) -> {
            start.setBackground(getImage("button1"));
        });

        add(start, PRIORITY_BUTTON);
    }

    private void putCards() {
        //TODO
        numLast = cardTypeNum * cardRankNum;

        Random rnd = new Random();
        for (int i = 0; i < cardTypeNum; i++) {
            for (int j = 0; j < cardRankNum; j++) {
                double angle = rnd.nextDouble() * Math.PI * 2;

                int finalI = i;
                int finalJ = j;

                Engine.runNewThread(() -> {
                    putCard(finalI, finalJ, angle);
                });

                Engine.sleepMs(200);
            }
        }
    }

    private void putCard(int i, int j, double angle) {
        Engine.playSE("swish");
        add(cards[i][j], PRIORITY_CARD);
        VectorInt startPos = VectorInt.getRandom(getWidth() * 8 / 10, 10).add(new VectorInt(getWidth() / 10, -cardSize.y));
        double rotateSpeed = new Random().nextDouble() * 10;
        VectorInt directVector = positions[i][j].minus(startPos);
        for (double t = 0; t < 1; t += 0.02) {
            cards[i][j].setPos(positions[i][j].add(directVector.scale(t - 1)));
            cards[i][j].setTransform(AffineTransform.getRotateInstance(angle + (t - 1) * rotateSpeed,
                    (double) cardSize.x / 2, (double) cardSize.y / 2));
            Engine.sleepMs(10);
        }
    }

    @Override
    public void refresh() {
        //TODO?
    }

    private void initialize() {
        state = STATE_STOP;
        cardTypeNum = Engine.getPropertyInt("card_type_num");
        cardRankNum = Engine.getPropertyInt("card_rank_num");
        Engine.storeImage("button1", "button2", "card_back", "card_effect");
        Engine.storeAudio("correct", "wrong", "win", "swish");

        Image ruler = getImage("card_1_1");
        cardSize = new VectorInt(ruler.getWidth(null), ruler.getHeight(null));
    }
}