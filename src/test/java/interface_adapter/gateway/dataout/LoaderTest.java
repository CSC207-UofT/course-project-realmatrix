package interface_adapter.gateway.dataout;

import entity.Card;
import entity.Pack;
import entity.User;
import interface_adapter.gateway.datain.CardWriter;
import interface_adapter.gateway.datain.PackWriter;
import interface_adapter.gateway.datain.UserWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoaderTest {
    Loader loader;
    User testUser1;
    User testUser2;
    final String testUser1Name = "testReaderUser1Name";
    final String testUser2Name = "testReaderUser2Name";
    final String testUser1Pw = "User1Pw";
    final String testUser2Pw = "User2Pw";
    Pack p1;
    Pack p2;
    final String p1Name = "testP1Name";
    final String p2Name = "testP2Name";
    Card c1;
    Card c2;
    final String card1Term = "testC1Term";
    final String card1Def = "testC1Def";
    final String card2Term = "testC2Term";
    final String card2Def = "testC2Def";
    UserWriter uw1;
    UserWriter uw2;
    PackWriter pw1;
    PackWriter pw2;
    CardWriter cw1;
    CardWriter cw2;
    String[] partialPath1;
    String[] partialPath2;

    @Before
    public void setUp() {
        loader = new Loader();
        c1 = new Card(card1Term, card1Def);
        c2 = new Card(card2Term, card2Def);
        p1 = new Pack(p1Name);
        p2 = new Pack(p2Name);
        testUser1 = new User(testUser1Name, testUser1Pw);
        testUser2 = new User(testUser2Name, testUser2Pw);
        p1.addCard(c1);
        p1.addCard(c2);
        partialPath1 = new String[]{testUser1Name, p1Name};
        partialPath2 = new String[]{testUser1Name, p2Name};
        uw1 = new UserWriter(partialPath1, testUser1);
        uw2 = new UserWriter(partialPath1, testUser2);
        pw1 = new PackWriter(partialPath1, p1);
        pw2 = new PackWriter(partialPath1, p2);
        cw1 = new CardWriter(partialPath1, c1);
        cw2 = new CardWriter(partialPath1, c2);
    }

    @Test
    public void testInitialLoad() throws IOException {
        uw1.write();
        HashMap<String, String> result1 = loader.initialLoad();
        assertTrue(result1.containsKey(testUser1Name));
        assertTrue(result1.containsValue(testUser1Pw));
        uw2.write();
        HashMap<String, String> result2 = loader.initialLoad();
        assertTrue(result2.containsKey(testUser1Name));
        assertEquals(result2.get(testUser1Name), testUser1Pw);
        assertTrue(result2.containsKey(testUser2Name));
        assertEquals(result2.get(testUser2Name), testUser2Pw);
        uw1.delete();
        uw2.delete();
    }

    @Test
    public void testUserLoad() throws IOException {
        uw1.write();
        uw2.write();
        pw1.write();
        pw2.write();
        cw1.write();
        cw2.write();
        assertEquals(0, testUser1.getPackageList().size());
        assertEquals(0, testUser2.getPackageList().size());
        loader.userLoad(testUser1);
        loader.userLoad(testUser2);
        assertEquals(2, testUser1.getPackageList().size());
        assertTrue(Objects.equals(testUser1.getPackageList().get(0).getName(), p1Name) ||
                Objects.equals(testUser1.getPackageList().get(0).getName(), p2Name));
        assertTrue(Objects.equals(testUser1.getPackageList().get(1).getName(), p1Name) ||
                Objects.equals(testUser1.getPackageList().get(1).getName(), p2Name));
        if (testUser1.getPackageList().get(0).getCardList().size() == 2) {
            assertTrue(Objects.equals(testUser1.getPackageList().get(0).getCardList().get(0).getTerm(), card1Term)
                    || Objects.equals(testUser1.getPackageList().get(0).getCardList().get(0).getTerm(), card2Term));
            assertTrue(Objects.equals(testUser1.getPackageList().get(0).getCardList().get(1).getTerm(), card1Term)
                    || Objects.equals(testUser1.getPackageList().get(0).getCardList().get(1).getTerm(), card2Term));
        } else {
            assertTrue(Objects.equals(testUser1.getPackageList().get(1).getCardList().get(0).getTerm(), card1Term)
                    || Objects.equals(testUser1.getPackageList().get(1).getCardList().get(0).getTerm(), card2Term));
            assertTrue(Objects.equals(testUser1.getPackageList().get(1).getCardList().get(1).getTerm(), card1Term)
                    || Objects.equals(testUser1.getPackageList().get(1).getCardList().get(1).getTerm(), card2Term));
            assertEquals(0, testUser2.getPackageList().size());
        }

        assertEquals(0, testUser2.getPackageList().size());
        cw2.delete();
        cw1.delete();
        pw2.delete();
        pw1.delete();
        uw2.delete();
        uw1.delete();
    }

    @After
    public void tearDown() {
        cw2.delete();
        cw1.delete();
        pw2.delete();
        pw1.delete();
        uw2.delete();
        uw1.delete();
    }

}