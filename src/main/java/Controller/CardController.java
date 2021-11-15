package Controller;

import entity.Card;
import input_boundaries.CardInputBoundary;
import output_boundaries.ChangeOutputBoundary;

public class CardController {
    private final CardInputBoundary cardInputBoundary;

    public CardController(CardInputBoundary cardInputBoundary) {
        this.cardInputBoundary = cardInputBoundary;
    }

    /**
     * Methods also in CardManager.java
     */
    public Card createNewCard(String term, String definition) {
        return this.cardInputBoundary.createNewCard(term, definition);
    }

    public void changeCardTerm(String newTerm, ChangeOutputBoundary changeOutputBoundary) {
        this.cardInputBoundary.changeCardTerm(newTerm, changeOutputBoundary);
    }

    public void changeCardDefinition(String newDefinition) {
        this.cardInputBoundary.changeCardDefinition(newDefinition);
    }

    public void increaseProficiency() {
        this.cardInputBoundary.increaseProficiency();
    }

    public void decreaseProficiency() {
        this.cardInputBoundary.decreaseProficiency();
    }

    public Card getCurrCard() {
        return this.cardInputBoundary.getCurrCard();
    }

    public void setCurrCard(Card card) {
        this.cardInputBoundary.setCurrCard(card);
    }
}
