package entity;

import java.util.*;

/**
 * A pack that stores cards.
 */

public class Pack {

    private ArrayList<Card> cardList; // oldest cards in the front, newly added ones at the end
    private final String id;
    private String name;
    private ArrayList<String> cardId;

    /**
     * Construct a pack with given id and name.
     * @param id    a unique id of the pack
     * @param name  name of the pack
     */
    public Pack(String id, String name) {
        this.id = id;
        this.name = name;
        this.cardList = new ArrayList<>();
        this.cardId = new ArrayList<>();
    }

    public void cardID(){
        for (Card card : cardList){
            cardId.add(card.getId());
        }

    }

    /**
     * Add a new Card into the pack's cardList.
     * Throws an exception if the new card's term already exists in the pack.
     *
     * @param card  a Card item
     */
    public void addCard(Card card) throws Exception {
        for (Card c : this.cardList) {
            if (c.getTerm().equals(card.getTerm())) {
                throw new Exception("Had this card already~ Try change a term.");
            }
        }
        this.cardList.add(card);
    }

    /**
     * Remove a Card in a pack.
     * @param card the card to be removed
     * @return return true if successfully removed, otherwise false
     */
    public boolean deleteCard(Card card) throws Exception {
        String id = card.getId();
        for (int i = 0; i < this.cardList.size(); i++) {
            if (Objects.equals(this.cardList.get(i).getId(), id)) {
                this.cardList.remove(i);
                return true;
            }
        }
        throw new Exception("No such card.");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getCards() {
        return this.cardList;
    }

    public ArrayList<String> getCardID() {
        return this.cardId;
    }

    public void changeName(String newName) {
        this.name = newName;
    }
}