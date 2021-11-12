package Controller;

import entity.Card;
import entity.Pack;
import manager.CardManager;
import use_case.ReviewGenerator;

import java.util.ArrayList;

public class ReviewController {
    public String reviewDisplay(Card c) {
        return c.getDefinition();
    }

    /**
     * get a list of reviewable cards
     *
     * @param p the pack need to be reviewed
     * @return a list of reviewable cards
     * @throws Exception if no card need to be reviewed
     */
    public ArrayList<Card> reviewableCardList(Pack p) throws Exception {
        ReviewGenerator rg = new ReviewGenerator(p);
        if (rg.dailyReviewCards().size() != 0) {
            return rg.dailyReviewCards();
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
        CardManager cm = new CardManager();
        cm.setCurrCard(c);
        if (opt.equals("1")) {
            cm.increaseProficiency();
        }

        if (opt.equals("3")) {
            cm.decreaseProficiency();
        }
    }

    /**
     * update the proficiency of the card that user currently testing
     *
     * @param opt user's correctness
     * @param c   the card that user currently learning
     */
    public void updateTestProficiency(String opt, Card c) {
        CardManager cm = new CardManager();
        cm.setCurrCard(c);
        if (opt.equals("2")) {
            cm.decreaseProficiency();
            cm.decreaseProficiency();
        }
    }
}
