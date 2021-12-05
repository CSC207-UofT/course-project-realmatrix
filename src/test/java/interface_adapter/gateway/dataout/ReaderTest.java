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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReaderTest {
    Reader r;
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

    @Before
    public void setUp() {
        c1 = new Card(card1Term, card1Def);
        c2 = new Card(card2Term, card2Def);
        p1 = new Pack(p1Name);
        p2 = new Pack(p2Name);
        testUser1 = new User(testUser1Name, testUser1Pw);
        testUser2 = new User(testUser2Name, testUser2Pw);
        p1.addCard(c1);
        p1.addCard(c2);
        testUser1.addPackage(p1);
        testUser1.addPackage(p2);
        r = new Reader();
        partialPath1 = new String[]{testUser1Name, p1Name};
        uw1 = new UserWriter(partialPath1, testUser1);
        uw2 = new UserWriter(partialPath1, testUser2);
        pw1 = new PackWriter(partialPath1, p1);
        pw2 = new PackWriter(partialPath1, p2);
        cw1 = new CardWriter(partialPath1, c1);
        cw2 = new CardWriter(partialPath1, c2);
    }

    @Test
    public void testReadUser() throws IOException {
        uw1.write();
        uw2.write();
        ArrayList<String> actualResult = r.readUsers();
        assertTrue(actualResult.contains("user_data" + File.separator + "users" + File.separator + testUser1Name));
        assertTrue(actualResult.contains("user_data" + File.separator + "users" + File.separator + testUser2Name));
        uw1.delete();
        uw2.delete();
    }

    @Test
    public void testReadPacks() throws IOException {
        uw1.write();
        pw1.write();
        ArrayList<String> result1 = r.readPacks(testUser1Name);
        assertEquals(1, result1.size());
        assertTrue(result1.contains("user_data" + File.separator + "users" + File.separator + testUser1Name + File.separator + "packages" + File.separator + p1Name));
        pw2.write();
        ArrayList<String> result2 = r.readPacks(testUser1Name);
        assertEquals(2, result2.size());
        assertTrue(result2.contains("user_data" + File.separator + "users" + File.separator  + testUser1Name + File.separator + "packages" + File.separator + p1Name));
        assertTrue(result2.contains("user_data" + File.separator + "users" + File.separator  + testUser1Name + File.separator + "packages" + File.separator + p2Name));
        pw2.delete();
        pw1.delete();
        uw1.delete();
    }

    @Test
    public void testReadCard() throws IOException {
        uw1.write();
        pw1.write();
        cw1.write();
        ArrayList<String> result1 = r.readCards(testUser1Name, p1Name);
        ArrayList<String> result2 = r.readCards(testUser1Name, p2Name);
        assertEquals(1, result1.size());
        assertEquals(0, result2.size());
        assertTrue(result1.contains("user_data" + File.separator + "users" + File.separator + testUser1Name
                + File.separator + "packages" + File.separator + p1Name + File.separator + "cards" + File.separator
                + card1Term + ".txt"));
        cw2.write();
        ArrayList<String> result3 = r.readCards(testUser1Name, p1Name);
        assertEquals(2, result3.size());
        assertTrue(result3.contains("user_data" + File.separator + "users" + File.separator + testUser1Name
                + File.separator + "packages" + File.separator + p1Name + File.separator + "cards" + File.separator
                + card1Term + ".txt"));
        assertTrue(result3.contains("user_data" + File.separator + "users" + File.separator + testUser1Name
                + File.separator + "packages" + File.separator + p1Name + File.separator + "cards" + File.separator
                + card2Term + ".txt"));
        cw2.delete();
        cw1.delete();
        pw1.delete();
        uw1.delete();
    }

    @After
    public void tearDown() throws Exception {
        cw1.delete();
        cw2.delete();
        pw1.delete();
        pw2.delete();
        uw2.delete();
        uw1.delete();

    }
}