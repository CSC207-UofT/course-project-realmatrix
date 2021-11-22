package interface_adapter;

import entity.Card;
import interface_adapter.Controller.CardController;
import entity.ProgramState;
import interface_adapter.gateway.DataInOut;
import interface_adapter.gateway.IDataInOut;
import interface_adapter.presenters.ChangePresenter;
import org.junit.Before;
import org.junit.Test;
import use_case.manager.CardManager;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CardControllerTest {
    CardController cc;
    CardManager cm;
    final Card c1 = new Card("closet", "a tall cupboard or wardrobe with a door, used for storage.");
    ChangePresenter cp;
    IDataInOut dataInOut = new DataInOut();
    ProgramState programState = new ProgramState();

    @Before
    public void createCardController() {
        cm = new CardManager();
        cc = new CardController(cm, dataInOut, programState);
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
    public void testChangeCardTerm() throws IOException {
        cc.setCurrCard(c1);
        cc.changeCardTerm("c1NewTerm", cp);
        assertEquals("c1NewTerm", cc.getCurrCard().getTerm());
    }

    @Test
    public void testChangeCardDefinition() throws IOException {
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
