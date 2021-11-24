package use_case.input_boundaries;

import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.SearchCardOutputBoundary;
import use_case.output_boundaries.SortCardOutputBoundary;

/**
 * An input boundary that connects CardManager and CardController.
 * **CardManager must implement this interface**
 */
public interface CardInputBoundary extends ManagerInputBoundary {
    boolean addNewCard(String term, String definition, AddOutputBoundary addOutputBoundary);

    boolean deleteCard(String term);

    boolean changeCardTerm(String newTerm, ChangeOutputBoundary changeOutputBoundary);

    void changeCardDefinition(String newDefinition);

    void searchCard(String keyword, SearchCardOutputBoundary searchCardOutputBoundary);

    void sortOldToNew(SortCardOutputBoundary sortOutputBoundary);

    void sortAtoZ(SortCardOutputBoundary sortCardOutputBoundary);

//    void sortZtoA(SortCardOutputBoundary sortCardOutputBoundary);

    void sortProLowToHigh(SortCardOutputBoundary sortCardOutputBoundary);
//
//    ArrayList<Card> sortProHighToLow();

    void sortRandom(SortCardOutputBoundary sortCardOutputBoundary);
}
