import entity.Card;
import entity.Pack;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PackTest {
    final String id = "123456";
    final String name = "COG250";
    Pack p;

    @Before
    public void createPack() {
        p = new Pack(id, name);
    }

    @Test
    public void TestAddCard() throws Exception {
        Card c = new Card("135", "fragmentation", "don't feel himself as a unit");
        Card d = new Card("136", "equivocation", "explaining things by assuming itself");
        p.addCard(c);
        p.addCard(d);
        List<Card> expected = new ArrayList<>();
        expected.add(c);
        expected.add(d);
        assertEquals(expected, p.getCards());
    }

    @Test
    public void TestGetId() {
        assertEquals(id, p.getId());
    }

    @Test
    public void TestGetName() {
        assertEquals(name, p.getName());
    }

    @Test
    public void TestGetCard() {
        List<Card> expected = new ArrayList<>();
        assertEquals(expected, p.getCards());
    }

    @Test
    public void TestChangeName() {
        String newName = "COG250 Terminology";
        p.changeName(newName);
        assertEquals(newName, p.getName());
    }

}
