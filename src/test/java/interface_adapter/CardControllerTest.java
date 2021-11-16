package interface_adapter;

import entity.Card;
import interface_adapter.Controller.CardController;
import org.junit.Before;
import org.junit.Test;
import interface_adapter.presenters.ChangePresenter;
import use_case.manager.CardManager;

import static org.junit.Assert.assertEquals;

public class CardControllerTest {
    CardController cc;
    CardManager cm;
    final Card c1 = new Card("011", "closet", "a tall cupboard or wardrobe with a door, used for storage.");
    ChangePresenter cp;

    @Before
    public void createCardController() {
        cm = new CardManager();
        cc = new CardController(cm);
        cp = new ChangePresenter();
    }

    @Test
    public void testCreateNewCard() {
        Card newCard = cc.createNewCard("newTerm", "newDef");
        assertEquals("newTerm", newCard.getTerm());
        assertEquals("newDef", newCard.getDefinition());
    }

    @Test
    public void testSetCurrCard() {
        cc.setCurrCard(c1);
        assertEquals(c1, cc.getCurrCard());
    }

    @Test
    public void testChangeCardTerm() {
        cc.setCurrCard(c1);
        cc.changeCardTerm("c1NewTerm", cp);
        assertEquals("c1NewTerm", cc.getCurrCard().getTerm());
    }

    @Test
    public void testChangeCardDefinition() {
        cc.setCurrCard(c1);
        cc.changeCardDefinition("c1NewDef");
        assertEquals("c1NewDef", cc.getCurrCard().getDefinition());
    }

    @Test
    public void testGetCurrCard() {
        cc.setCurrCard(c1);
        assertEquals(c1, cc.getCurrCard());
    }

//    @Test
//    public void testEditCardTerm(){
//        cc.setCurrCard(c1);
//        cc.editCardTerm("mental analysis");
//        assertEquals(c1.getTerm(), "mental analysis");
//    }
//
//    @Test
//    public void testEditCardDefinition(){
//        cc.setCurrCard(c1);
//        cc.editCardDefinition("analysis with mental presumption");
//        assertEquals(c1.getDefinition(), "analysis with mental presumption");
//    }

    @Test
    public void testIncreaseProficiency() {
        cc.setCurrCard(c1);
        cc.increaseProficiency();
        assertEquals(c1.getProficiency(), 1);
        cc.increaseProficiency();
        assertEquals(c1.getProficiency(), 2);
    }

    @Test
    public void testDecreaseProficiency() {
        cc.setCurrCard(c1);

        cc.increaseProficiency();
        cc.decreaseProficiency();
        assertEquals(c1.getProficiency(), 1);
    }
}
