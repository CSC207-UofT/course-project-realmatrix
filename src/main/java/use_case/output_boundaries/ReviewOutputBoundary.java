package use_case.output_boundaries;

import entity.Card;

public interface ReviewOutputBoundary {
    public void setReviewCompleted();

    public boolean getReviewCompleted();

    public void setCurrCard(Card card);

    public Card getCurrCard();

    public void setShowDefinitionStatus();

    public boolean getShowDefinitionStatus();
}
