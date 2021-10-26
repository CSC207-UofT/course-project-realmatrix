import entity.Card;
import manager.CardManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardManagerTest {
    CardManager cm;
    Card c;
    Card d;

    @Before
    public void createCardManager() {
        cm = new CardManager();
        String term = "Homoncular Fallacy";
        String definition = "Assuming a mental fallacy";
        c = cm.createNewCard(term, definition);

        cm.setCurrPack(c);
    }

    @Test(timeout = 50)
    public void TestCreateNewCard() {
        String term = "Homoncular Fallacy";
        String definition = "Assuming a mental fallacy";
        assertEquals(term, c.getTerm());
        assertEquals(1, c.getProficiency());
        assertEquals(definition, c.getDefinition());
    }

    @Test(timeout = 50)
    public void TestEditCardTerm() {
        String newTerm = "Equivocation";
        cm.editCardTerm(newTerm);
        assertEquals(newTerm, c.getTerm());
    }

    @Test(timeout = 50)
    public void TestEditCardDefinition() {
        String newDefinition = "leading misunderstanding.";
        cm.editCardDefinition(newDefinition);
        assertEquals(newDefinition, c.getDefinition());
    }

    @Test(timeout = 50)
    public void TestIncreaseProficiencyInRange() {
        System.out.println(c.getProficiency());
        cm.increaseProficiency();
        cm.increaseProficiency();

        assertEquals(3, c.getProficiency());
    }

    @Test(timeout = 50)
    public void TestDecreaseProficiencyInRange() {
        cm.increaseProficiency();
        cm.increaseProficiency();
        cm.decreaseProficiency();
        assertEquals(2, c.getProficiency());
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
        cm.setCurrPack(d);
        assertEquals(d, cm.getCurrCard());
    }
}

