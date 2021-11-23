package use_case.input_boundaries;

import entity.Card;
import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.SearchCardOutputBoundary;
import use_case.output_boundaries.SortCardOutputBoundary;

import java.util.ArrayList;

/**
 * An input boundary that connects CardManager and CardController.
 * **CardManager must implement this interface**
 */
public interface CardInputBoundary {
    boolean addNewCard(String term, String definition, AddOutputBoundary addOutputBoundary);

    boolean changeCardTerm(String newTerm, ChangeOutputBoundary changeOutputBoundary);

    void changeCardDefinition(String newDefinition);

    void searchCard(String keyword, SearchCardOutputBoundary searchCardOutputBoundary);

    void sortAtoZ(SortCardOutputBoundary sortCardOutputBoundary);

    void sortZtoA(SortCardOutputBoundary sortCardOutputBoundary);

    ArrayList<Card> sortProLowToHigh();

    ArrayList<Card> sortProHighToLow();

    ArrayList<Card> sortRandom(SortCardOutputBoundary sortCardOutputBoundary);
}
