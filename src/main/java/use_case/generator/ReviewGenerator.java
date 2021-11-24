package use_case.generator;

import entity.Card;
import entity.Pack;
import use_case.input_boundaries.ReviewInputBoundary;
import use_case.manager.CardManager;
import use_case.output_boundaries.ReviewOutputBoundary;

/**
 * Review a pack by going over all the cards in the pack, marking the unfamiliar ones, and reviewing those cards over
 * and over. Update cards' proficiency along the way. Only the term part is shown but user can choose to reveal the
 * definition part.
 */
public class ReviewGenerator extends TaskGenerator implements ReviewInputBoundary {
    private boolean cantRecall = false;
    private final ReviewOutputBoundary reviewOB;

    public ReviewGenerator(Pack pack, ReviewOutputBoundary reviewOB) {
        super(pack);
        this.reviewOB = reviewOB;
    }

    @Override
    public Card next() {
        if (cantRecall) {
            cards.add(currCard);
            // This is because user will recall that card eventually so the net effect on proficiency is
            // (-2 * n + 1 < 0)
            currCard.setProficiency(Math.min(0, currCard.getProficiency() - 2));
        } else {
            currCard.setProficiency(Math.max(5, currCard.getProficiency() + 1));
        }
        Card nextCard = cards.poll();
        if (nextCard != null) {
            nextCard.hideDefinition();
            currCard = nextCard;
            cantRecall = false;
            return nextCard;
        } else {
            return null;
        }
    }

    public void setShowDefinition() {
        currCard.unhideDefinition();
    }

    public void setCantRecall() {
        cantRecall = true;
    }

    @Override
    public boolean taskCompleted() {
        reviewOB.setReviewCompleted();
        return cards.peek() == null;
    }

    @Override
    public Card getCurrCard() {
        reviewOB.setCurrCardStrRep(currCard.toString());
        return currCard;
    }
}
