package interface_adapter.gateway.datain;

import entity.Card;
import entity.Pack;
import entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CardWriterTest {
    final String oldTerm = "TestTerm1old";
    final String newTerm = "TestTerm1new";
    Card testCard1;
    final String testUsername1 = "testUser1";
    final String testPackname1 = "testPack1";
    String[] testPartialPathOld;
    CardWriter cw;
    User testUser1;
    UserWriter uw;
    PackWriter pw;
    Pack testPack1;

    @Before
    public void createCardWriter() throws IOException {
        testCard1 = new Card(oldTerm, "TestDefinition1");
        testPartialPathOld = new String[]{testUsername1, testPackname1};
        cw = new CardWriter(testPartialPathOld, testCard1);
        testUser1 = new User(testUsername1, "tempPw");
        testPack1 = new Pack(testPackname1);
        pw = new PackWriter(testPartialPathOld, testPack1);
        pw.write();
        uw = new UserWriter(testPartialPathOld, testUser1);
        uw.write();


    }

    @Test
    public void testWriteCard() throws IOException {
        cw.write();
        Path path1 = Paths.get("user_data/users/" + testUsername1 + "/packages/" + testPackname1 + "/cards/" + oldTerm + ".txt");
        assertTrue(Files.exists(path1));
        cw.delete();
    }

    @Test
    public void testDeleteCard() throws IOException {
        cw.write();
        Path path1 = Paths.get("user_data/users/" + testUsername1 + "/packages/" + testPackname1 + "/cards/" + oldTerm + ".txt");
        assertTrue(Files.exists(path1));
        cw.delete();
        assertFalse(Files.exists(path1));
    }


    @Test
    public void testWriteNewTerm() throws IOException {
        cw.write();
        Path path1 = Paths.get("user_data/users/" + testUsername1 + "/packages/" + testPackname1 + "/cards/" + oldTerm + ".txt");
        assertTrue(Files.exists(path1));
        testCard1.setTerm(newTerm);
        cw.write(oldTerm, testCard1);
        Path path2 = Paths.get("user_data/users/" + testUsername1 + "/packages/" + testPackname1 + "/cards/" + newTerm + ".txt");
        assertFalse(Files.exists(path1));
        assertTrue(Files.exists(path2));
        cw.delete();
    }

    @After
    public void deleteFiles() {
        pw.delete();
        uw.delete();


    }
}

