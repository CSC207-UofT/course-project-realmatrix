package interface_adapter.Controller;

import entity.Card;
import entity.ProgramState;
import interface_adapter.gateway.IDataInOut;
import use_case.input_boundaries.CardInputBoundary;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.SearchCardOutputBoundary;
import use_case.output_boundaries.SortCardOutputBoundary;

import java.io.IOException;
import java.util.ArrayList;

public class CardController {
    private final CardInputBoundary cardInputBoundary;

    public CardController(CardInputBoundary cardInputBoundary) {
        this.cardInputBoundary = cardInputBoundary;
    }

    /**
     * Methods also in CardManager.java
     */
    public void addNewCard(String term, String definition, AddOutputBoundary addOutputBoundary) throws IOException {
        if (this.cardInputBoundary.addNewCard(term, definition, addOutputBoundary)) {
            this.cardInputBoundary.write();
        }
    }

    public void changeCardTerm(String newTerm, ChangeOutputBoundary changeOutputBoundary) throws IOException {
        if (this.cardInputBoundary.changeCardTerm(newTerm, changeOutputBoundary)) {
            this.cardInputBoundary.write();
        }
    }

    public void changeCardDefinition(String newDefinition) throws IOException {
        this.cardInputBoundary.changeCardDefinition(newDefinition);
        this.cardInputBoundary.write();
    }

    /**
     * Delete a specific card in the current pack.
     */
    public void deleteCard(String cardTerm) throws IOException {
        if (this.cardInputBoundary.deleteItem(cardTerm)) {
            this.cardInputBoundary.archive();
        }
    }

    public void searchCard(String keyword, SearchCardOutputBoundary searchCardOutputBoundary) {
        this.cardInputBoundary.searchCard(keyword, searchCardOutputBoundary);
    }

    public void sortAtoZ(SortCardOutputBoundary sortCardOutputBoundary) {
        this.cardInputBoundary.sortAtoZ(sortCardOutputBoundary);
    }

    public void sortZtoA(SortCardOutputBoundary sortCardOutputBoundary) {
        this.cardInputBoundary.sortZtoA(sortCardOutputBoundary);
    }

//    public void increaseProficiency() {
//        this.cardInputBoundary.increaseProficiency();
//    }
//
//    public void decreaseProficiency() {
//        this.cardInputBoundary.decreaseProficiency();
//    }
}
