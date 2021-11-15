import entity.Card;
import use_case.manager.CardManager;
import org.junit.Before;
import org.junit.Test;
import presenters.ChangePresenter;

import static org.junit.Assert.*;

public class CardManagerTest {
    CardManager cm;
    Card c;
    Card d;
    ChangePresenter cp;

    @Before
    public void createCardManager() {
        cp = new ChangePresenter();
        cm = new CardManager();
        String term = "Homoncular Fallacy";
        String definition = "Assuming a mental fallacy";
        c = cm.createNewCard(term, definition);

        cm.setCurrCard(c);
    }

    @Test(timeout = 50)
    public void TestCreateNewCard() {
        String term = "Homoncular Fallacy";
        String definition = "Assuming a mental fallacy";
        assertEquals(term, c.getTerm());
        assertEquals(0, c.getProficiency());
        assertEquals(definition, c.getDefinition());
    }

    @Test(timeout = 50)
    public void TestEditCardTerm() {
        String newTerm = "Equivocation";
        cm.changeCardTerm(newTerm, cp);
        assertEquals(newTerm, c.getTerm());
    }

    @Test(timeout = 50)
    public void TestEditCardDefinition() {
        String newDefinition = "leading misunderstanding.";
        cm.changeCardDefinition(newDefinition);
        assertEquals(newDefinition, c.getDefinition());
    }

    @Test(timeout = 50)
    public void TestIncreaseProficiencyInRange() {
        System.out.println(c.getProficiency());
        cm.increaseProficiency();
        cm.increaseProficiency();

        assertEquals(2, c.getProficiency());
    }

    @Test(timeout = 50)
    public void TestDecreaseProficiencyInRange() {
        cm.increaseProficiency();
        cm.increaseProficiency();
        cm.decreaseProficiency();
        assertEquals(1, c.getProficiency());
    }


    @Test(timeout = 50)
    public void TestIncreaseProficiencyOutOfRange() {
        for (int i = 1; i <= 5; i++) {
            cm.increaseProficiency();
        }
        assertSame(5, c.getProficiency());
    }

    @Test(timeout = 50)
    public void TestDecreaseProficiencyOutOfRange() {
        for (int i = 1; i <= 5; i++) {
            cm.decreaseProficiency();
        }
        assertSame(1, c.getProficiency());
    }

    @Test(timeout = 50)
    public void TestGetCurrCard() {
        assertEquals(c, cm.getCurrCard());
    }

    @Test(timeout = 50)
    public void TestSetCurrCard(){
        cm.setCurrCard(d);
        assertEquals(d, cm.getCurrCard());
    }
}

