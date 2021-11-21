package interface_adapter.presenters;

import entity.Card;
import use_case.output_boundaries.ReviewOutputBoundary;

public class ReviewPresenter implements ReviewOutputBoundary {
    private boolean learnCompleted = false;
    private Card currCard = null;
    private boolean showDefinitionStatus = false;

    public void setReviewCompleted() {
        this.learnCompleted = true;
    }

    public boolean getReviewCompleted() {
        return learnCompleted;
    }

    public void setCurrCard(Card card) {
        this.currCard = card;
    }

    public Card getCurrCard() {
        return currCard;
    }

    public void setShowDefinitionStatus() {
        this.showDefinitionStatus = true;
    }

    public boolean getShowDefinitionStatus() {
        return this.showDefinitionStatus;
    }
}
