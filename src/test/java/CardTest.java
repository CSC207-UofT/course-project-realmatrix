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
    public void TestEditDefinition() {
        String newDefinition = "wardrobe";
        c.editDefinition(newDefinition);
        assertSame(c.getDefinition(), newDefinition);
    }

    @Test(timeout = 50)
    public void TestEditTerm() {
        String newTerm = "storage";
        c.editTerm(newTerm);
        assertSame(c.getTerm(), newTerm);
    }

    @Test(timeout = 50)
    public void TestIncreaseProficiencyOutOfRange() {
        for (int i = 1; i <= 5; i++) {
            c.increaseProficiency();
        }
        assertSame(c.getProficiency(), 5);
    }

    @Test(timeout = 50)
    public void TestDecreaseProficiencyOutOfRange() {
        c.decreaseProficiency();
        assertSame(c.getProficiency(), 1);
    }

    @Test(timeout = 50)
    public void TestIncreaseDecreaseProficiencyInRange() {
        c.increaseProficiency();
        assertSame(c.getProficiency(), 2);
        c.decreaseProficiency();
        assertSame(c.getProficiency(), 1);
    }

    @Test(timeout = 50)
    public void TestToString() {
        assertEquals(c.toString(), "Term: closet\n" +
                "Definition: a tall cupboard or wardrobe with a door, used for storage.");
    }
}
