package use_case.generator;

import entity.Card;
import entity.Pack;

import java.util.LinkedList;
import java.util.Queue;

// TODO: before you change anything in this file, consult with Xing
/**
 * An abstract task generator.
 */
public abstract class TaskGenerator {
    protected final Pack pack;
    protected Queue<Card> cards;
    protected Card currCard; // current card the user is learning/reviewing

    public TaskGenerator(Pack pack) {
        this.pack = pack;
        this.cards = new LinkedList<>();
        this.currCard = null;
    }

    /**
     * Dequeue the next card.
     * @return the next card, or null if there's no card in the queue
     */
    public abstract void next();

    /**
     * Whether all the cards are dequeued.
     * @return true if no cards in the queue, false otherwise
     */
    public abstract boolean taskCompleted();

    /**
     * Show the current card in progress.
     * @return the card the user is at
     */
    public abstract Card getCurrCard();
}
