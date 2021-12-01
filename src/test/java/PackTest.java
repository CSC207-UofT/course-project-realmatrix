import entity.Card;
import entity.Pack;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PackTest {
    final String name = "COG250";
    Pack p;

    @Before
    public void createPack() {
        p = new Pack(name);
    }

    @Test
    public void testAddCard() {
        Card c = new Card("fragmentation", "don't feel himself as a unit");
        Card d = new Card("equivocation", "explaining things by assuming itself");
        try {
            p.addCard(c);
            p.addCard(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Card> expected = new ArrayList<>();
        expected.add(c);
        expected.add(d);
        assertEquals(expected, p.getCardList());
    }

    @Test
    public void testDeleteCard(){
        Card c = new Card("fragmentation", "don't feel himself as a unit");
        p.addCard(c);
        List<Card> expected = new ArrayList<>();
        expected.add(c);
        assertEquals(expected, p.getCardList());
        p.deleteCard(c);
        assertEquals(0, p.getCardList().size());
    }

    @Test
    public void testGetName() {
        assertEquals(name, p.getName());
    }

    @Test
    public void testGetCardList() {
        List<Card> expected = new ArrayList<>();
        assertEquals(expected, p.getCardList());
    }

    @Test
    public void testGetCardMap(){
        Card c = new Card("fragmentation", "don't feel himself as a unit");
        p.addCard(c);
        HashMap<String, Card> expect = new HashMap<>();
        expect.put(c.getTerm(), c);
        assertEquals(expect, p.getCardMap());
    }

    @Test
    public void testChangeName() {
        String newName = "COG250 Terminology";
        p.changeName(newName);
        assertEquals(newName, p.getName());
    }

}
