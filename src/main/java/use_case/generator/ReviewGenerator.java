package use_case.generator;

import use_case.constants.Constants;
import entity.Card;
import entity.Pack;
import use_case.input_boundaries.ReviewInputBoundary;

import java.util.ArrayList;
import java.util.Collections;

public class ReviewGenerator extends TaskGenerator implements ReviewInputBoundary {

    public ReviewGenerator(Pack pack) {
        super(pack);
    }

    /**
     * Generate a card list containing all cards eligible for reviewing
     * with random order and multiple occurrences of each card.
     *
     * @return an arraylist of card
     */
    @Override
    public ArrayList<Card> getDoCardList() {
        doable();
        return this.withProficiencyBasedCards();
    }

    /**
     * Helper method for getDoCardList().
     * Add a list of cards needs to be reviewed into this.cardList.
     */
    @Override
    protected void doable() {
        for (Card c : this.pack.getCards()) {
            if (c.getProficiency() <= Constants.REVIEW_PROFICIENCY_MAX && c.getProficiency() > 0) {
                this.cardList.add(c);
            }
        }
    }

    /**
     * Helper method for getDoCardList().
     * Return a list of doable cards, each card is repeated for some times according to its proficiency.
     */
    private ArrayList<Card> withProficiencyBasedCards() {
        ArrayList<Card> newCards = new ArrayList<>();
        for (Card c : this.cardList) {
            int prof = c.getProficiency();
            switch (prof) {
                case 1:
                case 2: { // Cards with proficiency 1/2 has 3 occurrences.
                    newCards.add(c);
                    newCards.add(c);
                    newCards.add(c);
                    break;
                }
                case 3:
                case 4: { // Cards with proficiency 3/4 has 2 occurrences.
                    newCards.add(c);
                    newCards.add(c);
                    break;
                }
                case 5: {
                    newCards.add(c);
                    break;
                }
            }
        }
        Collections.shuffle(newCards);
        return newCards;
    }
}

