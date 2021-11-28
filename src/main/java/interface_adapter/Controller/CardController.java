package interface_adapter.Controller;

import use_case.input_boundaries.CardInputBoundary;
import use_case.output_boundaries.*;

/**
 * This class controls all basic operations with cards in a pack:
 *      add/sort/search/delete cards.
 */
public class CardController {
    private final CardInputBoundary cardInputBoundary;
    private final DatabaseErrorOutputBoundary databaseErrorOutputBoundary;

    public CardController(CardInputBoundary cardInputBoundary, DatabaseErrorOutputBoundary databaseErrorOutputBoundary) {
        this.cardInputBoundary = cardInputBoundary;
        this.databaseErrorOutputBoundary = databaseErrorOutputBoundary;
    }

    /**
     * Add a new card with specified term and definition.
     * If success, write the new card into database.
     * @param term the new card's term
     * @param definition the new card's definition.
     * @param addOutputBoundary an output boundary that gets the result of adding: fail or success.
     */
    public void addNewCard(String term, String definition, AddOutputBoundary addOutputBoundary) {
        if (this.cardInputBoundary.addNewCard(term, definition, addOutputBoundary)) {
            this.cardInputBoundary.write(databaseErrorOutputBoundary);
        }
    }

    /**
     * Change a new card with specified term.
     * If success, write the updated card into database.
     * @param oldTerm the original term
     * @param newTerm the new term
     * @param changeOutputBoundary an output boundary that gets the result of changing: fail or success.
     */
    public void changeCardTerm(String oldTerm, String newTerm, ChangeOutputBoundary changeOutputBoundary) {
        if (this.cardInputBoundary.changeCardTerm(newTerm, changeOutputBoundary)) {
            this.cardInputBoundary.write(oldTerm, databaseErrorOutputBoundary);
        }
    }

    /**
     * Change a new card with specified definition.
     * Write the updated card into database.
     * @param newDefinition the new definition of term
     */
    public void changeCardDefinition(String newDefinition) {
        this.cardInputBoundary.changeCardDefinition(newDefinition);
        this.cardInputBoundary.write(databaseErrorOutputBoundary);
    }

    /**
     * Delete a specific card in the current pack.
     * If success, delete the card in database
     */
    public void deleteCard(String cardTerm) {
        if (this.cardInputBoundary.deleteCard(cardTerm)) {
            this.cardInputBoundary.delete(databaseErrorOutputBoundary);
        }
    }

    /**
     * Search card with specified keyword.
     * @param keyword the keyword entered by user
     * @param searchCardOutputBoundary a search output boundary that gets the result of qualified cards
     */
    public void searchCard(String keyword, SearchCardOutputBoundary searchCardOutputBoundary) {
        this.cardInputBoundary.searchCard(keyword, searchCardOutputBoundary);
    }

    /**
     * Old-to-new is the order of cards shown to the user by default.
     * @param sortCardOutputBoundary an output boundary that gets the result of sorted cards.
     */
    public void sortOldToNew(SortCardOutputBoundary sortCardOutputBoundary) {
        this.cardInputBoundary.sortOldToNew(sortCardOutputBoundary);
    }

    /**
     * Sort cards by alphabetic order (A-Z).
     * @param sortCardOutputBoundary an output boundary that gets the result of sorted cards.
     */
    public void sortAToZ(SortCardOutputBoundary sortCardOutputBoundary) {
        this.cardInputBoundary.sortAtoZ(sortCardOutputBoundary);
    }

    /**
     * This is the order of cards shown to the user by default.
     * @param sortCardOutputBoundary an output boundary that gets the result of sorted cards.
     */
    public void sortRandom(SortCardOutputBoundary sortCardOutputBoundary) {
        this.cardInputBoundary.sortRandom(sortCardOutputBoundary);
    }

    /**
     * This is the order of cards shown to the user by default.
     * @param sortCardOutputBoundary an output boundary that gets the result of sorted cards.
     */
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
