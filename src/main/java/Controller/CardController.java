package Controller;

import entity.Card;
import input_boundaries.CardInputBoundary;
import manager.CardManager;

public class CardController {
    private CardInputBoundary cm;

    public CardController() {
        this.cm = new CardManager();
    }

    /**
     * Methods also in CardManager.java
     *
     */
    public Card getCurrCard() {
        return this.cm.getCurrCard();
    }

    public Card createNewCard(String term, String definition) {
        return this.cm.createNewCard(term, definition);
    }

    public void editCardTerm(String newTerm) {
        this.cm.editCardTerm(newTerm);
    }

    public void editCardDefinition(String newDefinition) {
        this.cm.editCardDefinition(newDefinition);
    }

    public void increaseProficiency() {
        this.cm.increaseProficiency();
    }

    public void decreaseProficiency() {
        this.cm.decreaseProficiency();
    }

    public void setCurrCard(Card card) {
        this.cm.setCurrCard(card);
    }
}
