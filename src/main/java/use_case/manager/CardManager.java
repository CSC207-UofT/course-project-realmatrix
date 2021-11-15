package use_case.manager;

import entity.Card;
import input_boundaries.CardInputBoundary;
import output_boundaries.ChangeOutputBoundary;

/**
 * A CardManager contains all cards in the system.
 */
public class CardManager extends Manager<Card> implements CardInputBoundary {
    private Card currCard = null; // Initialize to the state where the user is not in a card

    public CardManager() {
        super();
    }
    // TODO: idToItem stores all the cards in this pack.

    /**
     * Create and return a new card with defined term and definition.
     * Stores id and the corresponding card in idToItem.
     *
     * @param term       The term of the card
     * @param definition The definition of the term
     * @return The newly-created card
     */
    public Card createNewCard(String term, String definition) {
        String id = generateId();
        Card c = new Card(id, term, definition);
        this.idToItem.put(id, c);
        return c;
    }

    /**
     * Change the card's term to a new term specified by user.
     *
     * @param newTerm              the new term the card should change to
     * @param changeOutputBoundary the output boundary for getting whether change is successful or not
     */
    public void changeCardTerm(String newTerm, ChangeOutputBoundary changeOutputBoundary) {
        if (uniqueCardTerm(newTerm)) {
            this.currCard.setTerm(newTerm);
            changeOutputBoundary.setChangeResult(true);
        } else {
            changeOutputBoundary.setChangeResult(false);
        }
    }

    /**
     * Helper method of changeCardTerm.
     * Check if this newTerm already exists.
     *
     * @param newTerm the new term
     * @return true if the new term does not exist; false otherwise
     */
    private boolean uniqueCardTerm(String newTerm) {
        for (Card c : this.idToItem.values()) {
            if (newTerm.equals(c.getTerm())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Change the card's definition to a new definition specified by user.
     *
     * @param newDefinition the new definition the card should change to
     */
    public void changeCardDefinition(String newDefinition) {
        this.currCard.setDefinition(newDefinition);
    }

    /**
     * Increase the proficiency of the card by 1.
     */
    public void increaseProficiency() {
        this.currCard.setProficiency(Math.min(this.currCard.getProficiency() + 1, 5));
    }

    /**
     * Decrease the proficiency of the card by 1.
     */
    public void decreaseProficiency() {
        this.currCard.setProficiency(Math.max(this.currCard.getProficiency() - 1, 1));
    }

    /**
     * Getter for the current card the user is in.
     *
     * @return the current card.
     */
    public Card getCurrCard() {
        return this.currCard;
    }

    /**
     * Change to the current card the user is in.
     */
    public void setCurrCard(Card card) {
        this.currCard = card;
    }
}
