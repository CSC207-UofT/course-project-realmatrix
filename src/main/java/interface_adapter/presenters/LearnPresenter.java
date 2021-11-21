package interface_adapter.presenters;

import entity.Card;
import use_case.output_boundaries.LearnOutputBoundary;

public class LearnPresenter implements LearnOutputBoundary {
    private boolean learnCompleted = false;
    private Card currCard = null;

    public void setLearnCompleted() {
        this.learnCompleted = true;
    }

    public boolean getLearnCompleted() {
        return learnCompleted;
    }

    public void setCurrCard(Card card) {
        this.currCard = card;
    }

    public Card getCurrCard() {
        return currCard;
    }
}
