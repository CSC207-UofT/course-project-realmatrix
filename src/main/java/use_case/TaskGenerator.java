package use_case;

import entity.Card;
import entity.Pack;

import java.util.ArrayList;

/**
 * This class is a test generator that raise a collection of
 * cards for user to learn and review
 */
public abstract class TaskGenerator {
    protected Pack pack;
    protected ArrayList<Card> cardList;

    public TaskGenerator(Pack pack) {
        this.pack = pack;
        this.cardList = new ArrayList<>();
    }

    /**
     * Generate a card list containing all cards eligible for learning/reviewing
     * with random order and multiple occurrences of each card.
     *
     * @return an arraylist of card
     */
    public abstract ArrayList<Card> getDoCardList();

    /**
     * Helper method for getDoCardList()
     * Get a card list containing all cards that are eligible to be learnt or reviewed.
     */
    protected abstract void doable();

}
