package use_case;

import entity.Card;
import entity.Pack;
import constants.Constants;
import input_boundaries.ReviewInputBoundary;

import java.util.Random;

import java.util.ArrayList;

public class ReviewGenerator extends TaskGenerator implements ReviewInputBoundary {

    public ReviewGenerator(Pack pack) {
        super(pack);
    }


    /**
     * Return a list of cards needs to be reviewed.
     */
    private ArrayList<Card> doable() {
        for (Card c : this.pack.getCards()) {
            if (c.getProficiency() <= Constants.REVIEW_PROFICIENCY_MAX && c.getProficiency() > 0) {
                this.cardList.add(c);
            }
        }
        return this.cardList;
    }

    /**
     * Return a list of doable cards, each card is repeated for some times according to its proficiency.
     */
    public ArrayList<Card> withProficiencyBasedCards(){
        ArrayList<Card> newCards = new ArrayList<>();
        for (Card c : this.doable()){
            int prof = c.getProficiency();
            for (int i = 0; i < (Constants.REVIEW_PROFICIENCY_MAX - prof); i++) {
                newCards.add(c);
            }
        }
        return newCards;
    }

    public ArrayList<Card> dailyReviewCards(){

        ArrayList<Card> temp = this.doable();
        ArrayList<Card> daily = new ArrayList<>(temp.size() * 2);
        Random random = new Random();
        for (int i = 0; i < temp.size() * 2; i++) {
            int index = random.nextInt(temp.size());
            daily.add(this.withProficiencyBasedCards().get(index));
            temp.remove(index);
        }
        return daily;
    }

}

