import Controller.CardController;
import manager.CardManager;
import entity.Card;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CardControllerTest {
    CardController cc;
    Card c1 = new Card("011", "closet", "a tall cupboard or wardrobe with a door, used for storage.");

    @Before
    public void createCardController(){
        cc = new CardController();
    }

    @Test
    public void testSetCurrCard(){
        cc.setCurrCard(c1);
        assertEquals(c1, cc.getCurrCard());
    }

    @Test
    public void testGetCurrCard(){
        cc.setCurrCard(c1);
        assertEquals(c1, cc.getCurrCard());
    }

    @Test
    public void testEditCardTerm(){
        cc.setCurrCard(c1);
        cc.editCardTerm("mental analysis");
        assertEquals(c1.getTerm(), "mental analysis");
    }

    @Test
    public void testEditCardDefinition(){
        cc.setCurrCard(c1);
        cc.editCardDefinition("analysis with mental presumption");
        assertEquals(c1.getDefinition(), "analysis with mental presumption");
    }

    @Test
    public void testIncreaseProficiency(){
        cc.setCurrCard(c1);
        cc.increaseProficiency();
        assertEquals(c1.getProficiency(),1);
        cc.increaseProficiency();
        assertEquals(c1.getProficiency(),2);
    }

    @Test
    public void testDecreaseProficiency(){
        cc.setCurrCard(c1);

        cc.increaseProficiency();
        cc.decreaseProficiency();
        assertEquals(c1.getProficiency(), 1);
    }
}
