package interface_adapter;

import entity.Card;
import entity.Pack;
import entity.User;
import entity.ProgramState;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ProgramStateTest {
    ProgramState ps;
    User currUser;
    Pack currPack;
    Card currCard;

    @Before
    public void createProgramState() {
        ps = new ProgramState();
        currCard = new Card("currCardTerm", "currCardDef");
        currUser = new User("currUserName", "currUserPasswrod");
        currPack = new Pack("currPackName");

    }

    @Test
    public void testSetCurrUser() {
        ps.setCurrUser(currUser);
        assertEquals(currUser, ps.getCurrUser());
    }

    @Test
    public void testSetCurrCard() {
        ps.setCurrCard(currCard);
        assertEquals(currCard, ps.getCurrCard());
    }

    @Test
    public void testSetCurrPack() {
        ps.setCurrPack(currPack);
        assertEquals(currPack, ps.getCurrPack());
    }

    @Test
    public void testToString() {
        ps.setCurrUser(currUser);
        assertEquals("Current User is: " + currUser.toString() +
                "\n" + "Current Pack is: " + "NULL" + "\n" + "Current Card is: " + "NULL" + "\n", ps.toString());
    }

    @Test
    public void testGetCurrUser() {
        assertNull(ps.getCurrUser());
    }

    @Test
    public void testGetCurrCard() {
        assertNull(ps.getCurrCard());
    }

    @Test
    public void testGetCurrPack() {
        assertNull(ps.getCurrPack());
    }



}