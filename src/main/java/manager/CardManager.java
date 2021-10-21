package manager;

import entity.Card;

import java.util.HashMap;

/**
 * A CardManager contains all cards in the system.
 */
public class CardManager extends Manager<Card> {

    public CardManager() {
        super();
    }

    //TODO: check number of characters for a card term/def?

    /**
     * Create and return a new card with defined term and definition.
     * Stores id and the corresponding card in idToCard.
     *
     * @param term       The term of the card
     * @param definition The definition of the term
     * @return           The newly-created card
     */
    public Card createNewCard(String term, String definition) {
        String id = generateId();
        Card c = new Card(id, term, definition);
        idToItem.put(id, c);
        return c;
    }

    /**
     *
     * @param id id of the card
     * @param newTerm the new term the card should change to
     * @return true if successfully changed, false otherwise
     */
    public boolean editCardTerm(String id, String newTerm) {
        if (idToItem.containsKey(id)) {
            ((Card) idToItem.get(id)).editTerm(newTerm);
            return true;
        }
        return false;
    }

    /**
     *
     * @param id id of the card
     * @param newDefinition the new definition the card should change to
     * @return true if successfully changed, false otherwise
     */
    public boolean editCardDefinition(String id, String newDefinition) {
        if (idToItem.containsKey(id)) {
            ((Card) idToItem.get(id)).editTerm(newDefinition);
            return true;
        }
        return false;
    }
}
