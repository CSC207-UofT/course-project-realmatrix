import entity.Card;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class CardTest {
    final String term = "closet";
    final String definition = "a tall cupboard or wardrobe with a door, used for storage.";
    Card c;

    @Before
    public void createCard() {
        c = new Card(term, definition);
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
        String expected = String.format("Term:\n %s", this.term);
        assertEquals(expected, c.toString());
    }

    @Test(timeout = 50)
    public void TestToStringUnHiddenDef() {
        assertEquals(String.format("Term:\n %1$s \n\n Definition:\n %2$s",
                this.term, this.definition), c.toString());
    }

    @Test(timeout = 50)
    public void TestToString() {
        assertEquals(c.toString(), "Term:\n closet \n\n Definition:\n a tall cupboard or wardrobe with a door, used for storage.");
    }
}
