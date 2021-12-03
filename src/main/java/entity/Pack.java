package entity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A pack that stores cards.
 */

public class Pack {

    private final ArrayList<Card> cardList; // oldest cards in the front, newly added ones at the end
    private String name;

    /**
     * Construct a pack with given id and name.
     *
     * @param name name of the pack
     */
    public Pack(String name) {
        this.name = name;
        this.cardList = new ArrayList<>();
    }

    /**
     * Add a new Card into the pack's cardList.
     *
     * @param card a Card item
     */
    public void addCard(Card card) {
        this.cardList.add(card);
    }

    /**
     * Remove a Card in a pack.
     *
     * @param card the card to be removed
     */
    public void deleteCard(Card card) {
        this.cardList.remove(card);
    }

    // A bunch of getters.
    public String getName() {
        return name;
    }

    public ArrayList<Card> getCardList() {
        return this.cardList;
    }

    /**
     * Return a card map that maps card term to card, allows more flexible use in other classes.
     * @return a hah map of card term to card object.
     */
    public HashMap<String, Card> getCardMap() {
        HashMap<String, Card> nameToCard = new HashMap<>();
        for (Card c : this.cardList) {
            nameToCard.put(c.getTerm(), c);
        }
        return nameToCard;
    }

    /**
     * Change the name of the pack.
     */
    public void changeName(String newName) {
        this.name = newName;
    }
}