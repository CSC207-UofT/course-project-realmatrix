package use_case.generator;

import entity.Card;
import entity.Pack;
import use_case.input_boundaries.LearnInputBoundary;
import use_case.output_boundaries.LearnOutputBoundary;

import java.util.Collections;
import java.util.List;

// TODO: before you change anything in this file, consult with Xing
/**
 * Learn a pack by going over all the cards once, showing both term and definition.
 */
public class LearnGenerator extends TaskGenerator implements LearnInputBoundary {
    private final LearnOutputBoundary learnOB;

    /**
     * Each card appears once in random order.
     * @param pack pack where cards come from
     * @param learnOB output boundary
     */
    public LearnGenerator(Pack pack, LearnOutputBoundary learnOB) {
        super(pack);
        List<Card> cardList = pack.getCardList();
        Collections.shuffle(cardList);
        cards.addAll(cardList);
        this.learnOB = learnOB;
    }

    @Override
    public void next() {
        Card nextCard = cards.poll();
        if (nextCard != null) {
            currCard = nextCard;
            learnOB.setCurrCardStrRep(currCard.toString());
        } else {
            learnOB.setLearnCompleted();
            currCard = null;
            learnOB.setCurrCardStrRep(null);
        }
    }

    @Override
    public Card getCurrCard() {
        return currCard;
    }
}