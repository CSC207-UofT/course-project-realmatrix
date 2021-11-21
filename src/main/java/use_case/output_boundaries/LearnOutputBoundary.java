package use_case.output_boundaries;

import entity.Card;

public interface LearnOutputBoundary {
    public void setLearnCompleted();

    public boolean getLearnCompleted();

    public void setCurrCard(Card card);

    public Card getCurrCard();
}
