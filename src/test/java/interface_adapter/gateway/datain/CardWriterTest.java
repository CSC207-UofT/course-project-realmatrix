package interface_adapter.gateway.datain;

import entity.Card;
import entity.Pack;
import entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static org.junit.Assert.*;

public class CardWriterTest {
    String oldTerm = "TestTerm1old";
    String newTerm = "TestTerm1new";
    Card testCard1;
    String testUsername1 = "testUser1";
    String testPackname1 = "testPack1";
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

//        Path path1 = FileSystems.getDefault().getPath("./user_data/users/testUser1/cards/TestTerm1");
//        try {
//            Files.deleteIfExists(path1);
//        } catch (IOException x) {
//            System.err.println(x);
//        }
//
//        Path path = FileSystems.getDefault().getPath("./user_data/users/testUser1/cards");
//        try {
//            Files.deleteIfExists(path);
//        } catch (NoSuchFileException x) {
//            System.err.format("%s: no such" + " file or directory%n", path);
//        } catch (DirectoryNotEmptyException x) {
//            System.err.format("%s not empty%n", path);
//        } catch (IOException x) {
//            System.err.println(x);
//        }
    }
}

