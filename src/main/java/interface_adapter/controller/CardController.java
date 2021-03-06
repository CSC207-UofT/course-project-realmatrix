package interface_adapter.controller;

import interface_adapter.gateway.IDataInOut;
import use_case.input_boundaries.CardInputBoundary;
import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.DatabaseErrorOutputBoundary;
import use_case.output_boundaries.SortSearchCardOutputBoundary;

/**
 * This class controls all basic operations with cards in a pack:
 * add/sort/search/delete cards.
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
     *
     * @param term              the new card's term
     * @param definition        the new card's definition.
     * @param addOutputBoundary an output boundary that gets the result of adding: fail or success.
     */
    public void addNewCard(String term, String definition, IDataInOut dataInOut, AddOutputBoundary addOutputBoundary) {
        if (this.cardInputBoundary.addNewCard(term, definition, addOutputBoundary)) {
            this.cardInputBoundary.write(dataInOut, databaseErrorOutputBoundary);
        }
    }

    /**
     * Change a new card with specified term.
     * If success, write the updated card into database.
     *
     * @param oldTerm              the original term
     * @param newTerm              the new term
     * @param changeOutputBoundary an output boundary that gets the result of changing: fail or success.
     */
    public void changeCardTerm(String oldTerm, String newTerm, IDataInOut dataInOut, ChangeOutputBoundary changeOutputBoundary) {
        if (this.cardInputBoundary.changeCardTerm(newTerm, changeOutputBoundary)) {
            this.cardInputBoundary.write(oldTerm, dataInOut, databaseErrorOutputBoundary);
        }
    }

    /**
     * Change a new card with specified definition.
     * Write the updated card into database.
     *
     * @param newDefinition the new definition of term
     */
    public void changeCardDefinition(String newDefinition, IDataInOut dataInOut) {
        this.cardInputBoundary.changeCardDefinition(newDefinition);
        this.cardInputBoundary.write(dataInOut, databaseErrorOutputBoundary);
    }

    /**
     * Delete a specific card in the current pack and save it into database.
     * If success, delete the card in database
     */
    public void deleteCard(String cardTerm, IDataInOut dataInOut) {
        if (this.cardInputBoundary.deleteCard(cardTerm)) {
            this.cardInputBoundary.delete(dataInOut, databaseErrorOutputBoundary);
        }
    }

    /**
     * Search card with specified keyword.
     *
     * @param keyword                      the keyword entered by user
     * @param sortSearchCardOutputBoundary a search/sort output boundary that gets the result of qualified cards
     */
    public void searchCard(String keyword, SortSearchCardOutputBoundary sortSearchCardOutputBoundary) {
        this.cardInputBoundary.searchCard(keyword, sortSearchCardOutputBoundary);
    }

    /**
     * Old-to-new is the order of cards shown to the user by default.
     *
     * @param sortSearchCardOutputBoundary an output boundary that gets the result of sorted cards.
     */
    public void sortOldToNew(SortSearchCardOutputBoundary sortSearchCardOutputBoundary) {
        this.cardInputBoundary.sortOldToNew(sortSearchCardOutputBoundary);
    }

    /**
     * Sort cards by alphabetic order (A-Z).
     *
     * @param sortSearchCardOutputBoundary an output boundary that gets the result of sorted cards.
     */
    public void sortAToZ(SortSearchCardOutputBoundary sortSearchCardOutputBoundary) {
        this.cardInputBoundary.sortAtoZ(sortSearchCardOutputBoundary);
    }

    /**
     * This is the order of cards shown to the user by default.
     *
     * @param sortSearchCardOutputBoundary an output boundary that gets the result of sorted cards.
     */
    public void sortProLowToHigh(SortSearchCardOutputBoundary sortSearchCardOutputBoundary) {
        this.cardInputBoundary.sortProLowToHigh(sortSearchCardOutputBoundary);
    }

}
