package use_case.generator;

import entity.Card;
import entity.Pack;
import use_case.input_boundaries.LearnInputBoundary;
import use_case.output_boundaries.LearnOutputBoundary;

/**
 * Learn a pack by going over all the cards once, showing both term and definition.
 */
public class LearnGenerator extends TaskGenerator implements LearnInputBoundary {
    private final LearnOutputBoundary learnOB;

    public LearnGenerator(Pack pack, LearnOutputBoundary learnOB) {
        super(pack);
        this.learnOB = learnOB;
    }

    @Override
    public Card next() {
        Card nextCard = cards.poll();
        if (nextCard != null) {
            currCard = nextCard;
            return nextCard;
        } else {
            learnOB.setLearnCompleted();
            return null;
        }
    }

    @Override
    public boolean taskCompleted() {
        if (cards.peek() == null) {
            learnOB.setLearnCompleted();
        }
        return cards.peek() == null;
    }

    @Override
    public Card getCurrCard() {
        learnOB.setCurrCardStrRep(currCard.toString());
        return currCard;
    }
}