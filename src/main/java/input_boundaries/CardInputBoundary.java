package input_boundaries;

import entity.Card;

public interface CardInputBoundary {
    Card createNewCard(String term, String definition);
    void editCardTerm(String newTerm);
    void editCardDefinition(String newDefinition);
    void increaseProficiency();
    void decreaseProficiency();
    Card getCurrCard();
    void setCurrCard(Card card);
}
