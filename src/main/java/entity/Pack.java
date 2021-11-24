package entity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A pack that stores cards.
 */

public class Pack {

    private final HashMap<String, Card> cardMap; // oldest cards in the front, newly added ones at the end
    private String name;

    /**
     * Construct a pack with given id and name.
     *
     * @param name name of the pack
     */
    public Pack(String name) {
        this.name = name;
        this.cardMap = new HashMap<>();
    }

    /**
     * Add a new Card into the pack's cardList.
     * Throws an exception if the new card's term already exists in the pack.
     *
     * @param card a Card item
     */
    public void addCard(Card card) {
        this.cardMap.put(card.getTerm(), card);
    }

    /**
     * Remove a Card in a pack.
     *
     * @param card the card to be removed
     */
    public void deleteCard(Card card) {
        this.cardMap.remove(card);
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Card> getCards() {
        return this.cardMap;
    }

    /**
     * Change the name of the pack.
     */
    public void changeName(String newName) {
        this.name = newName;
    }
}