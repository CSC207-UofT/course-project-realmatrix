package use_case.input_boundaries;

import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.SortSearchCardOutputBoundary;

/**
 * An input boundary that connects CardManager and CardController.
 * **CardManager must implement this interface**
 */
public interface CardInputBoundary extends ManagerInputBoundary, SortInputBoundary<SortSearchCardOutputBoundary> {
    boolean addNewCard(String term, String definition, AddOutputBoundary addOutputBoundary);

    boolean deleteCard(String term);

    boolean changeCardTerm(String newTerm, ChangeOutputBoundary changeOutputBoundary);

    void changeCardDefinition(String newDefinition);

    void searchCard(String keyword, SortSearchCardOutputBoundary sortSearchCardOutputBoundary);

    void sortProLowToHigh(SortSearchCardOutputBoundary sortSearchCardOutputBoundary);
}
