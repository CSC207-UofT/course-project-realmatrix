import entity.Card;
import entity.Pack;
import interface_adapter.presenters.AddPresenter;
import use_case.manager.CardManager;
import use_case.manager.PackManager;
import org.junit.Before;
import org.junit.Test;
import use_case.output_boundaries.AddOutputBoundary;
import use_case.generator.LearnGenerator;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LearnGeneratorTest {
    LearnGenerator lg;
    final CardManager cm = new CardManager();
    final PackManager pm = new PackManager();
    Pack p1;
    Card c1;
    Card c2;
    Card c3;
    final AddOutputBoundary addOutputBoundary = new AddPresenter();

    /**
     * Create a LearnGenerator with Pack of one card with proficiency > 0 and two card with proficiency equals 0.
     */
    @Before
    public void createLearnGenerator() {
        c1 = cm.createNewCard("c1Term", "c1Definition");
        c2 = cm.createNewCard("c2Term", "c2Definition");
        c3 = cm.createNewCard("c3Term", "c3Definition");
        cm.setCurrCard(c2);
        cm.increaseProficiency();
        p1 = pm.createNewPack("pack1Name");
        pm.setCurrPack(p1);
        pm.addCard(c1, addOutputBoundary);
        pm.addCard(c2, addOutputBoundary);
        pm.addCard(c3, addOutputBoundary);
        lg = new LearnGenerator(p1);
    }

    /**
     * Test the LearnGenerator created to return the list of card with c1 and c3, each with 2 occurrences.
     */
    @Test
    public void testGetDoCardList(){
        ArrayList<Card> cardLearnList = new ArrayList<>();
        cardLearnList.add(c1);
        cardLearnList.add(c3);
        ArrayList<Card> actual = lg.getDoCardList();
        for (Card c : cardLearnList) {
            if (java.util.Collections.frequency(actual, c) != 2) {
                fail("Wrong learning lists.");
            }
        }
        assertTrue("Proper learning lists.", true);
    }
}
