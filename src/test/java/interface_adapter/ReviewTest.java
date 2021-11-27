package interface_adapter;

import entity.Card;
import entity.Pack;
import entity.ProgramState;
import entity.User;
import interface_adapter.Controller.ReviewController;
import interface_adapter.presenters.ReviewPresenter;
import org.junit.Before;
import org.junit.Test;
import use_case.generator.ReviewGenerator;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.input_boundaries.ReviewInputBoundary;
import use_case.manager.ProgramStateManager;
import use_case.output_boundaries.ReviewOutputBoundary;

import java.io.IOException;
import java.util.Objects;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * This junit test simulates the interaction of review components.
 */
public class ReviewTest {
    private ReviewInputBoundary reviewInputBoundary;
    private ReviewOutputBoundary reviewOutputBoundary;
    private ReviewController reviewController;
    private Card card1;
    private Card card2;
    private Card card3;
    private Card card4;
    private Card card5;
    private Card card6;

    /**
     * Create a pack for reviewing. In GUI pack should come from ProgramState. I didn't use ProgramState (to get pack)
     * here because this test only tests the correctness of review components (unit test), not their dependencies.
     * @throws Exception
     */
    @Before
    public void setup() throws Exception {
        Pack pack = new Pack("vocabulary");
        card1 = new Card("big", "large in size");
        card1.setProficiency(3);
        card2 = new Card("small", "having comparatively little size or slight dimensions");
        card2.setProficiency(2);
        card3 = new Card("good", "of high quality");
        card3.setProficiency(3);
        card4 = new Card("bad", "failing to reach an acceptable standard");
        card4.setProficiency(0);
        card5 = new Card("hello", "used as a greeting");
        card5.setProficiency(1);
        card6 = new Card("goodbye", "a taking of leave");
        card6.setProficiency(3);
        for (Card card : new Card[]{card1, card2, card3, card4, card5, card6}) {
            pack.addCard(card);
        }

        reviewOutputBoundary = new ReviewPresenter();
        // notice in the line of code below I didn't get pack from program state
        reviewInputBoundary = new ReviewGenerator(pack, reviewOutputBoundary);
        ProgramStateInputBoundary state = new ProgramStateManager();
        state.setCurrUser(new User("xing", "password"));
        state.setCurrPack(pack.getName()); // at a review session, program state should be at (xing/vocabulary/null)
        reviewController = new ReviewController(reviewInputBoundary, state);
    }

    /**
     * Simulate when user doesn't click on show definition button or can't recall button, and goes straight through the
     * cards, in reviewing a package.
     * @throws IOException
     */
    @Test
    public void testReviewNoShowDefinitionNoCantRecall() throws IOException {
        // counter to count how many times each card appears in the review process
        int card1Num = 0;
        int card2Num = 0;
        int card3Num = 0;
        int card4Num = 0;
        int card5Num = 0;
        int card6Num = 0;

        // TODO: use in GUI to go to the next card
        reviewController.next();
        // TODO: use this in GUI to get curr card string representation
        String currCardStrRep = reviewOutputBoundary.getCurrCardStrRep();
        // TODO: use this logic in GUI to check if review is completed and continue creating new card frames
        while (!reviewOutputBoundary.getReviewCompleted()) {
            if (representThisCard(currCardStrRep, card1)) {
                card1Num += 1;
            }
            if (representThisCard(currCardStrRep, card2)) {
                card2Num += 1;
            }
            if (representThisCard(currCardStrRep, card3)) {
                card3Num += 1;
            }
            if (representThisCard(currCardStrRep, card4)) {
                card4Num += 1;
            }
            if (representThisCard(currCardStrRep, card5)) {
                card5Num += 1;
            }
            if (representThisCard(currCardStrRep, card6)) {
                card6Num += 1;
            }
            reviewController.next();
            currCardStrRep = reviewOutputBoundary.getCurrCardStrRep();
        }
        assertTrue(reviewOutputBoundary.getReviewCompleted());
        // test that current card points to null
        assertNull(reviewOutputBoundary.getCurrCardStrRep());
        // test that each card appears (REVIEW_PROFICIENCY_MAX - card.getProficiency() + 1) number of times
        assertEquals(card1Num, 1);
        assertEquals(card2Num, 2);
        assertEquals(card3Num, 1);
        assertEquals(card4Num, 4);
        assertEquals(card5Num, 3);
        assertEquals(card6Num, 1);
        // test that each card's proficiency is increased to maximum proficiency
        assertEquals(card1.getProficiency(), 3);
        assertEquals(card2.getProficiency(), 3);
        assertEquals(card3.getProficiency(), 3);
        assertEquals(card4.getProficiency(), 3);
        assertEquals(card5.getProficiency(), 3);
        assertEquals(card6.getProficiency(), 3);
    }

    private boolean representThisCard(String strRep, Card card) {
        // strRep only has the term part because by default definition is hidden
        return Objects.equals(strRep, "Term: " + card.getTerm());
    }

    /**
     * Simulate when user clicks on the show definition button.
     * @throws IOException
     */
    @Test
    public void testReviewShowDefinition() throws IOException {
        reviewController.next();
        // TODO: use the following TWO lines in GUI to update current card's string representation (with definition)
        reviewController.setShowDefinition();
        String currCardStrRep = reviewOutputBoundary.getCurrCardStrRep();
        // test that after user reveals the definition, the definition is indeed displayed
        assertTrue(currCardStrRep.contains("Definition"));
        reviewController.next();
        String nextCardStrRep = reviewOutputBoundary.getCurrCardStrRep();
        // test that when use goes to the next card, the card's definition is hidden as usual
        assertFalse(nextCardStrRep.contains("Definition"));
    }

    /**
     * Simulate when user clicks on the can't recall button.
     * @throws IOException
     */
    @Test
    public void testReviewCantRecall() throws IOException {
        reviewController.next();
        Card thatCardICantRecall = reviewInputBoundary.getCurrCard();
        int oldProficiency = thatCardICantRecall.getProficiency();
        reviewController.setCantRecall();
        // update of current card's proficiency (in the database) is triggered by another next() call
        reviewController.next();
        int newProficiency = thatCardICantRecall.getProficiency();
        // test that proficiency is updated correctly
        assertEquals(newProficiency, Math.max(0, oldProficiency - 2));
    }
}
