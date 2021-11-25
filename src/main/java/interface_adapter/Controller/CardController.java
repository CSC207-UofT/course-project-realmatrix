package interface_adapter.Controller;

import use_case.input_boundaries.CardInputBoundary;
import use_case.output_boundaries.*;

import java.io.IOException;

public class CardController {
    private final CardInputBoundary cardInputBoundary;
    private final DatabaseErrorOutputBoundary databaseErrorOutputBoundary;

    public CardController(CardInputBoundary cardInputBoundary, DatabaseErrorOutputBoundary databaseErrorOutputBoundary) {
        this.cardInputBoundary = cardInputBoundary;
        this.databaseErrorOutputBoundary = databaseErrorOutputBoundary;
    }

    /**
     * Methods also in CardManager.java
     */
    public void addNewCard(String term, String definition, AddOutputBoundary addOutputBoundary) {
        if (this.cardInputBoundary.addNewCard(term, definition, addOutputBoundary)) {
            this.cardInputBoundary.write(databaseErrorOutputBoundary);
        }
    }

    public void changeCardTerm(String oldTerm, String newTerm, ChangeOutputBoundary changeOutputBoundary) {
        if (this.cardInputBoundary.changeCardTerm(newTerm, changeOutputBoundary)) {
            this.cardInputBoundary.write(oldTerm, databaseErrorOutputBoundary);
        }
    }

    public void changeCardDefinition(String newDefinition) {
        this.cardInputBoundary.changeCardDefinition(newDefinition);
        this.cardInputBoundary.write(databaseErrorOutputBoundary);
    }

    /**
     * Delete a specific card in the current pack.
     */
    public void deleteCard(String cardTerm) {
        if (this.cardInputBoundary.deleteCard(cardTerm)) {
            this.cardInputBoundary.archive(databaseErrorOutputBoundary);
        }
    }

    public void searchCard(String keyword, SearchCardOutputBoundary searchCardOutputBoundary) {
        this.cardInputBoundary.searchCard(keyword, searchCardOutputBoundary);
    }

    public void sortOldToNew(SortCardOutputBoundary sortCardOutputBoundary) {
        this.cardInputBoundary.sortOldToNew(sortCardOutputBoundary);
    }

    public void sortAtoZ(SortCardOutputBoundary sortCardOutputBoundary) {
        this.cardInputBoundary.sortAtoZ(sortCardOutputBoundary);
    }

    public void sortRandom(SortCardOutputBoundary sortCardOutputBoundary) {
        this.cardInputBoundary.sortRandom(sortCardOutputBoundary);
    }

    public void sortProLowToHigh(SortCardOutputBoundary sortCardOutputBoundary) {
        this.cardInputBoundary.sortProLowToHigh(sortCardOutputBoundary);
    }

//    public void sortZtoA(SortCardOutputBoundary sortCardOutputBoundary) {
//        this.cardInputBoundary.sortZtoA(sortCardOutputBoundary);
//    }

//    public void increaseProficiency() {
//        this.cardInputBoundary.increaseProficiency();
//    }
//
//    public void decreaseProficiency() {
//        this.cardInputBoundary.decreaseProficiency();
//    }
}
