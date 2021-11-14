import entity.Card;
import entity.Pack;
import manager.CardManager;
import manager.PackManager;
import org.junit.Before;
import org.junit.Test;
import output_boundaries.AddOutputBoundary;
import use_case.LearnGenerator;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LearnGeneratorTest {
    LearnGenerator lg;
    CardManager cm = new CardManager();
    PackManager pm = new PackManager();
    Pack p1;
    Card c1;
    Card c2;
    Card c3;
    AddOutputBoundary AddOutputBoundary;

    /**
     * Create a LearnGenerator with Pack of one card with proficiency > 0 and two card with proficiency equals 0.
     * @throws Exception if two or more cards in c1, c2, c3 have the same term.
     */
    @Before
    public void createLearnGenerator() throws Exception {
        c1 = cm.createNewCard("c1Term", "c1Definition");
        c2 = cm.createNewCard("c2Term", "c2Definition");
        c3 = cm.createNewCard("c3Term", "c3Definition");
        cm.setCurrCard(c2);
        cm.increaseProficiency();
        p1 = pm.createNewPack("pack1Name");
        pm.setCurrPack(p1);
        pm.addCard(c1, AddOutputBoundary);
        pm.addCard(c2, AddOutputBoundary);
        pm.addCard(c3, AddOutputBoundary);
        lg = new LearnGenerator(p1);
    }

    /**
     * Test the LearnGenerator created to return the list of card with c1 and c2.
     */
    @Test
    public void testDoable(){
        ArrayList<Card> expectedList = new ArrayList<>();
        expectedList.add(c1);
        expectedList.add(c3);
        assertEquals(expectedList, lg.doable());
    }
}
