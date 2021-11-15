package Controller;

import entity.Card;
import entity.Pack;
import org.junit.Before;
import org.junit.Test;
import use_case.manager.CardManager;
import use_case.manager.PackManager;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class LearnControllerTest {
    LearnController lc;
    PackManager pm;
    Pack p1;
    Card c1;
    Card c2;
    CardManager cm;

    @Before
    public void createLearnController() throws Exception {
        pm = new PackManager();
        p1 = pm.createNewPack("CSC207");
        cm = new CardManager();
        c1 = cm.createNewCard("Card1Term", "Card1Definition");
        lc = new LearnController(p1);
        c2 = cm.createNewCard("Card2Term", "Card2Definition");
        p1.addCard(c1);
        p1.addCard(c2);
    }

    @Test
    public void testLearnDisplayOnTerm() {
        assertEquals("Card1Term", lc.learnDisplay("t", c1));
    }

    @Test
    public void testLearnDisplayOnDefinition() {
        assertEquals("Card2Definition", lc.learnDisplay("d", c2));
    }

    @Test
    public void testLearnableCardListAllLearnable() {
        ArrayList<Card> expectedList = new ArrayList<>();
        expectedList.add(c1);
        expectedList.add(c2);
        assertEquals(expectedList, lc.learnableCardList());
    }

    @Test
    public void testLearnableCardListNotAllLearnable() {
        ArrayList<Card> expectedList = new ArrayList<>();
        cm.setCurrCard(c1);
        cm.increaseProficiency();
        expectedList.add(c2);
        assertEquals(expectedList, lc.learnableCardList());
    }

    @Test
    public void testUpdateMemProficiency() {
        lc.updateMemProficiency("1", c1);
        lc.updateMemProficiency("2", c1);
        lc.updateMemProficiency("2", c2);
        assertEquals(3, c1.getProficiency());
        assertEquals(1, c2.getProficiency());
    }


}