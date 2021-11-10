import entity.Card;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {
    String id = "1234";
    String term = "closet";
    String definition = "a tall cupboard or wardrobe with a door, used for storage.";
    Card c;

    @Before public void createCard() {
        c = new Card(id, term, definition);
    }

    @Test(timeout = 50)
    public void TestSetDefinition() {
        String newDefinition = "wardrobe";
        c.setDefinition(newDefinition);
        assertSame(c.getDefinition(), newDefinition);
    }

    @Test(timeout = 50)
    public void TestHideDefinition() {

        c.hideDefinition();
        assertSame(c.getDefinitionHidden(), true);
    }

    @Test(timeout = 50)
    public void TestUnHideDefinition() {
        c.hideDefinition();
        c.unhideDefinition();
        assertSame(c.getDefinitionHidden(), false);
    }

    @Test(timeout = 50)
    public void TestGetTerm() {
        assertSame(c.getTerm(), term);
    }

    @Test(timeout = 50)
    public void TestGetDefinition() {
        assertSame(c.getDefinition(), definition);
    }

    @Test(timeout = 50)
    public void TestSetTerm() {
        String newTerm = "storage";
        c.setTerm(newTerm);
        assertSame(c.getTerm(), newTerm);
    }


    @Test(timeout = 50)
    public void TestGetId() {
        assertSame(c.getId(), id);
    }

    @Test(timeout = 50)
    public void TestSetProficiencyInRange() {
        int newProficiency = 4;
        c.setProficiency(newProficiency);
        assertSame(c.getProficiency(), newProficiency);
    }

    @Test(timeout = 50)
    public void TestSetProficiencyOutOfRange() {
        int newProficiency = 6;
        c.setProficiency(newProficiency);
        assertSame(c.getProficiency(), newProficiency);
    }

    @Test(timeout = 50)
    public void TestGetProficiency() {
        assertSame(0, c.getProficiency());
    }

    @Test(timeout = 50)
    public void TestToStringHiddenDef() {
        c.hideDefinition();
        String expected = String.format("Term: %s", this.term);
        assertEquals(expected, c.toString());
    }

    @Test(timeout = 50)
    public void TestToStringUnHiddenDef() {
        assertEquals(String.format("Term: %1$s\nDefinition: %2$s",
                this.term, this.definition), c.toString());
    }

    // Put them in CardManager Test
//    @Test(timeout = 50)
//    public void TestIncreaseProficiencyOutOfRange() {
//        for (int i = 1; i <= 5; i++) {
//            c.increaseProficiency();
//        }
//        assertSame(c.getProficiency(), 5);
//    }
//
//    @Test(timeout = 50)
//    public void TestDecreaseProficiencyOutOfRange() {
//        c.decreaseProficiency();
//        assertSame(c.getProficiency(), 1);
//    }
//
//    @Test(timeout = 50)
//    public void TestIncreaseDecreaseProficiencyInRange() {
//        c.increaseProficiency();
//        assertSame(c.getProficiency(), 2);
//        c.decreaseProficiency();
//        assertSame(c.getProficiency(), 1);
//    }

    @Test(timeout = 50)
    public void TestToString() {
        assertEquals(c.toString(), "Term: closet\n" +
                "Definition: a tall cupboard or wardrobe with a door, used for storage.");
    }
}
