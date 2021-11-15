package interface_adapter.Controller;

import entity.Card;
import entity.Pack;
import use_case.input_boundaries.CardInputBoundary;
import use_case.input_boundaries.ReviewInputBoundary;
import use_case.ReviewGenerator;
import use_case.manager.CardManager;

import java.util.ArrayList;

public class ReviewController {
    private final ReviewInputBoundary rg;
    private final CardInputBoundary cm;

    public ReviewController(Pack p) {
        this.rg = new ReviewGenerator(p);
        this.cm = new CardManager();
    }

    public String reviewDisplay(Card c) {
        return c.getDefinition();
    }

    /**
     * get a list of reviewable cards
     *
     * @return a list of reviewable cards
     * @throws Exception if no card need to be reviewed
     */
    public ArrayList<Card> reviewableCardList() throws Exception {
        if (this.rg.getDoCardList().size() != 0) {
            return this.rg.getDoCardList();
        } else {
            throw new Exception("no card need to be reviewed");
        }

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
