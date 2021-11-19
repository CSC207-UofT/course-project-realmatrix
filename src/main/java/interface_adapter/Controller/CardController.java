package interface_adapter.Controller;

import entity.Card;
import interface_adapter.gateway.IDataInOut;
import use_case.input_boundaries.CardInputBoundary;
import use_case.output_boundaries.ChangeOutputBoundary;

public class CardController {
    private final CardInputBoundary cardInputBoundary;
    private final IDataInOut dataInOut;

    public CardController(CardInputBoundary cardInputBoundary, IDataInOut dataInOut) {
        this.cardInputBoundary = cardInputBoundary;
        this.dataInOut = dataInOut;
    }

    /**
     * Methods also in CardManager.java
     */
    public Card createNewCard(String term, String definition) {
        return this.cardInputBoundary.createNewCard(term, definition);
    }

    public void changeCardTerm(String newTerm, ChangeOutputBoundary changeOutputBoundary) {
        this.cardInputBoundary.changeCardTerm(newTerm, changeOutputBoundary);
        // TODO: how to save to database
    }

    public void changeCardDefinition(String newDefinition) {
        this.cardInputBoundary.changeCardDefinition(newDefinition);
        // TODO: how to save to database
    }

    public void increaseProficiency() {
        this.cardInputBoundary.increaseProficiency();
    }

    public void decreaseProficiency() {
        this.cardInputBoundary.decreaseProficiency();
    }

    //TODO: the following two methods may not be needed, since we have ProgramState
    public Card getCurrCard() {
        return this.cardInputBoundary.getCurrCard();
    }

    public void setCurrCard(Card card) {
        this.cardInputBoundary.setCurrCard(card);
    }
}
