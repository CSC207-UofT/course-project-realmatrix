//import entity.Card;
//import entity.Pack;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
//public class PackTest {
//    final String name = "COG250";
//    Pack p;
//
//    @Before
//    public void createPack() {
//        p = new Pack(name);
//    }
//
//    @Test
//    public void TestAddCard() {
//        Card c = new Card("fragmentation", "don't feel himself as a unit");
//        Card d = new Card("equivocation", "explaining things by assuming itself");
//        try {
//            p.addCard(c);
//            p.addCard(d);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        List<Card> expected = new ArrayList<>();
//        expected.add(c);
//        expected.add(d);
//        assertEquals(expected, p.getCardList());
//    }
//
//    @Test
//    public void TestGetName() {
//        assertEquals(name, p.getName());
//    }
//
//    @Test
//    public void TestGetCard() {
//        List<Card> expected = new ArrayList<>();
//        assertEquals(expected, p.getCardList());
//    }
//
//    @Test
//    public void TestChangeName() {
//        String newName = "COG250 Terminology";
//        p.changeName(newName);
//        assertEquals(newName, p.getName());
//    }
//
//}
