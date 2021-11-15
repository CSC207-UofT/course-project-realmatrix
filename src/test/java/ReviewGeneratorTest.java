import entity.Card;
import entity.Pack;
import use_case.manager.CardManager;
import use_case.manager.PackManager;
import org.junit.Before;
import org.junit.Test;
import use_case.output_boundaries.AddOutputBoundary;
import interface_adapter.presenters.AddPresenter;
import use_case.generator.ReviewGenerator;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class ReviewGeneratorTest {
    CardManager cm = new CardManager();
    PackManager pm = new PackManager();
    Pack p1;
    Card c1;
    Card c2;
    Card c3;
    Card c4;
    ReviewGenerator rg;
    AddOutputBoundary AddOutputBoundary = new AddPresenter();

    /**
     * Create rg with card1 of proficiency 2, card2 of proficiency 3, card3 of proficiency 0, card4 of proficiency 5.
     */
    @Before
    public void createReviewGenerator() {
        c1 = cm.createNewCard("card1Term", "card1Definition");
        cm.setCurrCard(c1);
        cm.increaseProficiency();
        cm.increaseProficiency();   // c1 prof = 2
        c2 = cm.createNewCard("card2Term", "card2Definition");
        cm.setCurrCard(c2);
        cm.increaseProficiency();
        cm.increaseProficiency();
        cm.increaseProficiency();   // c2 prof = 3
        c3 = cm.createNewCard("card3Term", "card3Definition");  // c3 prof = 0
        c4 = cm.createNewCard("card4Term", "card4Definition");
        cm.setCurrCard(c4);
        cm.increaseProficiency();
        cm.increaseProficiency();
        cm.increaseProficiency();
        cm.increaseProficiency();
        cm.increaseProficiency();   // c4 prof = 5
        p1 = pm.createNewPack("packName");
        pm.setCurrPack(p1);
        pm.addCard(c1, AddOutputBoundary);
        pm.addCard(c2, AddOutputBoundary);
        pm.addCard(c3, AddOutputBoundary);
        pm.addCard(c4, AddOutputBoundary);
        rg = new ReviewGenerator(p1);
    }

    /**
     * Test getDoCardList based on the created ReviewGenerator, which should return a list of c1, c2, c3 and c4.
     */
    @Test
    public void testGetDoCardList(){
        // c1: prof = 2, need 3 occurrences
        // c2: prof = 3, need 2 occurrences
        // c4: prof = 5, need 1 occurrence
        ArrayList<Card> actual = rg.getDoCardList();

        if (java.util.Collections.frequency(actual, c1) != 3) {
            fail("Wrong reviewing lists.");
        } else if (java.util.Collections.frequency(actual, c2) != 2) {
            fail("Wrong reviewing lists.");
        }else if (java.util.Collections.frequency(actual, c4) != 1) {
            fail("Wrong reviewing lists.");
        }
        assertTrue("Proper reviewing lists.", true);
    }

//    /**
//     * Test withProficiencyBasedCards based on the created ReveiwGenerator.
//     */
//    @Test
//    public void testWithProficiencyBasedCards(){
//        ArrayList<Card> expectedList = new ArrayList<>();
//        for (int i = 0; i < (Constants.REVIEW_PROFICIENCY_MAX - 2); i++) {
//            expectedList.add(c1);
//        }
//        for (int i = 0; i < (Constants.REVIEW_PROFICIENCY_MAX - 3); i++) {
//            expectedList.add(c2);
//        }
//        assertEquals(expectedList, rg.withProficiencyBasedCards());
//    }
}
