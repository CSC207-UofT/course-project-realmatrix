package use_case.input_boundaries;

import entity.Card;

import java.util.ArrayList;

public interface ReviewInputBoundary {
    void next();

    void setShowDefinition();

    void setCantRecall();

    /**
     * This method locates current card to update its proficiency in database in controller call.
     * GUI should use the method in review output boundary to get the string representation of curr card.
     * @return current card
     */
    Card getCurrCard();
}
