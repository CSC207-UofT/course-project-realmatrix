package use_case.generator;

import entity.Card;
import entity.Pack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class is a test generator that raise a collection of
 * cards for user to learn and review
 */
public abstract class TaskGenerator {
    protected final Pack pack;
    protected Queue<Card> cards;
    protected Card currCard; // current card the user is learning/reviewing

    public TaskGenerator(Pack pack) {
        this.pack = pack;
        this.cards = new LinkedList<>();
        cards.addAll(pack.getCards());
        this.currCard = null;
    }

    public abstract Card next();

    public boolean taskCompleted() {
        return cards.peek() == null;
    }

    public Card getCurrCard() {
        return currCard;
    }
}
