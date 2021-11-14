package input_boundaries;

import entity.Card;
import output_boundaries.ChangeOutputBoundary;

/**
 * An input boundary that connects CardManager and CardController.
 * **CardManager must implement this interface**
 */
public interface CardInputBoundary {
    Card createNewCard(String term, String definition);
    void changeCardTerm(String newTerm, ChangeOutputBoundary changeOutputBoundary);
    void changeCardDefinition(String newDefinition);
    void increaseProficiency();
    void decreaseProficiency();
    Card getCurrCard(); //May not be needed
    void setCurrCard(Card card);    //May not be needed
}
