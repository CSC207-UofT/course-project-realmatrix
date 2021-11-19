package use_case.input_boundaries;

import entity.Card;
import use_case.output_boundaries.ChangeOutputBoundary;

import java.io.IOException;

/**
 * An input boundary that connects CardManager and CardController.
 * **CardManager must implement this interface**
 */
public interface CardInputBoundary {
    Card createNewCard(String term, String definition);

    void changeCardTerm(String newTerm, ChangeOutputBoundary changeOutputBoundary) throws IOException;

    void changeCardDefinition(String newDefinition) throws IOException;

    void increaseProficiency() throws IOException;

    void decreaseProficiency() throws IOException;

    Card getCurrCard(); //May not be needed

    void setCurrCard(Card card);    //May not be needed
}
