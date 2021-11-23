package interface_adapter.Controller;

import entity.Card;
import use_case.input_boundaries.CardInputBoundary;
import use_case.input_boundaries.ReviewInputBoundary;

import java.util.ArrayList;

public class ReviewController {
    private final ReviewInputBoundary rg;
    private final CardInputBoundary cm;

    public ReviewController(ReviewInputBoundary reviewInputBoundary, CardInputBoundary cardInputBoundary) {
        this.rg = reviewInputBoundary;
        this.cm = cardInputBoundary;
    }

// Presenter will take over this
//    public String reviewDisplay(Card c) {
//        return c.getDefinition();
//    }

    /**
     * get a list of reviewable cards
     *
     * @return a list of reviewable cards
     */
    public ArrayList<Card> reviewableCardList() {
        return this.rg.getDoCardList();

    }

    /**
     * update the proficiency of the card that user currently reviewing
     *
     * @param opt user's option of the quality of reviewing
     * @param c   the card that user currently learning
     */
    public void updateMemProficiency(String opt, Card c) {
        this.cm.setCurrCard(c);
        if (opt.equals("1")) {
            this.cm.increaseProficiency();
        }

        if (opt.equals("3")) {
            this.cm.decreaseProficiency();
        }
    }

    /**
     * update the proficiency of the card that user currently testing
     *
     * @param opt user's correctness
     * @param c   the card that user currently learning
     */
    public void updateTestProficiency(String opt, Card c) {
        this.cm.setCurrCard(c);
        if (opt.equals("2")) {
            this.cm.decreaseProficiency();
            this.cm.decreaseProficiency();
        }
    }
}
