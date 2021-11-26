package interface_adapter;

import entity.Card;
import entity.Pack;
import interface_adapter.Controller.LearnController;
import interface_adapter.presenters.LearnPresenter;
import org.junit.Before;
import org.junit.Test;

import use_case.generator.LearnGenerator;
import use_case.input_boundaries.LearnInputBoundary;
import use_case.output_boundaries.LearnOutputBoundary;

import java.util.Objects;

import static org.junit.Assert.*;

/**
 * This junit test simulates the interaction of learn components.
 */
public class LearnTest {
    private LearnOutputBoundary learnOutputBoundary;
    private LearnController learnController;
    private Card card1;
    private Card card2;
    private Card card3;
    private Card card4;
    private Card card5;
    private Card card6;

    @Before
    public void setup() throws Exception {
        // Create a pack for learning. In GUI pack should come from ProgramState. I didn't use ProgramState here
        // because this test only tests the correctness of learn components (unit test), not their dependencies.
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

        learnOutputBoundary = new LearnPresenter();
        LearnInputBoundary learnInputBoundary = new LearnGenerator(pack, learnOutputBoundary);
        learnController = new LearnController(learnInputBoundary);
    }

    /**
     * Simulate when user goes through learning a package.
     */
    @Test
    public void testLearn() {
        // counter to count how many times each card appears in the learn process
        int card1Num = 0;
        int card2Num = 0;
        int card3Num = 0;
        int card4Num = 0;
        int card5Num = 0;
        int card6Num = 0;

        // TODO: use in GUI to go to the next card
        learnController.next();
        // TODO: use this in GUI to get curr card string representation
        String currCardStrRep = learnOutputBoundary.getCurrCardStrRep();
        // TODO: use this logic in GUI to check if learn is completed and continue creating new card frames
        while (!learnOutputBoundary.getLearnCompleted()) {
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
            learnController.next();
            currCardStrRep = learnOutputBoundary.getCurrCardStrRep();
        }
        assertTrue(learnOutputBoundary.getLearnCompleted());
        // test that current card points to null
        assertNull(learnOutputBoundary.getCurrCardStrRep());
        // test that each card appears exactly once
        assertEquals(card1Num, 1);
        assertEquals(card2Num, 1);
        assertEquals(card3Num, 1);
        assertEquals(card4Num, 1);
        assertEquals(card5Num, 1);
        assertEquals(card6Num, 1);
    }

    private boolean representThisCard(String strRep, Card card) {
        return Objects.equals(strRep, card.toString());
    }
}
