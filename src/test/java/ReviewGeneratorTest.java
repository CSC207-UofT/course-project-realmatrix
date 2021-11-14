import constants.Constants;
import entity.Card;
import entity.Pack;
import manager.CardManager;
import manager.PackManager;
import org.junit.Before;
import org.junit.Test;
import output_boundaries.AddOutputBoundary;
import use_case.ReviewGenerator;

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
    AddOutputBoundary AddOutputBoundary;

    /**
     * Create rg with card1 of proficiency 2, card2 of proficiency 3, card3 of proficiency 0, card4 of proficiency 5.
     */
    @Before
    public void createReviewGenerator() {
        c1 = cm.createNewCard("card1Term", "card1Definition");
        cm.setCurrCard(c1);
        cm.increaseProficiency();
        cm.increaseProficiency();
        c2 = cm.createNewCard("card2Term", "card2Definition");
        cm.setCurrCard(c2);
        cm.increaseProficiency();
        cm.increaseProficiency();
        cm.increaseProficiency();
        c3 = cm.createNewCard("card3Term", "card3Definition");
        c4 = cm.createNewCard("card4Term", "card4Definition");
        cm.setCurrCard(c4);
        cm.increaseProficiency();
        cm.increaseProficiency();
        cm.increaseProficiency();
        cm.increaseProficiency();
        cm.increaseProficiency();
        p1 = pm.createNewPack("packName");
        pm.setCurrPack(p1);
        pm.addCard(c1, AddOutputBoundary);
        pm.addCard(c2, AddOutputBoundary);
        pm.addCard(c3, AddOutputBoundary);
        pm.addCard(c4, AddOutputBoundary);
        rg = new ReviewGenerator(p1);
    }

    /**
     * Test doable based on the created ReviewGenerator, which should return a list of c1, c2, c3 and c4.
     */
    @Test
    public void testDoable(){
        ArrayList<Card> expectedList = new ArrayList<>();
        expectedList.add(c1);
        expectedList.add(c2);
        expectedList.add(c4);
        assertEquals(expectedList, rg.doable());
    }

    /**
     * Test withProficiencyBasedCards based on the created ReveiwGenerator.
     */
    @Test
    public void testWithProficiencyBasedCards(){
        ArrayList<Card> expectedList = new ArrayList<>();
        for (int i = 0; i < (Constants.REVIEW_PROFICIENCY_MAX - 2); i++) {
            expectedList.add(c1);
        }
        for (int i = 0; i < (Constants.REVIEW_PROFICIENCY_MAX - 3); i++) {
            expectedList.add(c2);
        }
        assertEquals(expectedList, rg.withProficiencyBasedCards());
    }
}
