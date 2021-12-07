package interface_adapter.gateway;

import entity.Card;
import entity.Pack;
import entity.User;
import interface_adapter.gateway.datain.CardWriter;
import interface_adapter.gateway.datain.PackWriter;
import interface_adapter.gateway.datain.UserWriter;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import static org.junit.Assert.*;

public class DataInOutTest {
    DataInOut data;
    final String oldTerm = "TestTerm1old";
    final String newTerm = "TestTerm1new";
    Card testCard1;
    final String testUsername1Old = "testDataInOutUser1Old";
    final String testUsername1New = "testDataInOutUser1New";
    final String testPackname1Old = "testPack1Old";
    final String testPackname1New = "testPack1New";
    String[] testPartialPathOld;
    String[] testPartialPathNew;
    CardWriter cw;
    User testUser1;
    UserWriter uw1;
    PackWriter pw;
    Pack testPack1;
    Path path1;
    Path path2;

    @Before
    public void setUp() {
        data = new DataInOut();
        testCard1 = new Card(oldTerm, "TestDefinition1");
        testPartialPathOld = new String[]{testUsername1Old, testPackname1Old};

        testUser1 = new User(testUsername1Old, "tempPw");
        testPack1 = new Pack(testPackname1Old);
        pw = new PackWriter(testPartialPathOld, testPack1);
        uw1 = new UserWriter(testPartialPathOld, testUser1);
        cw = new CardWriter(testPartialPathOld, testCard1);
    }

    @Test
    public void testWriteNewUser() throws IOException {
        data.write(testPartialPathOld, testUser1);
        path1 = Paths.get("user_data/users/" + testUser1.getName());
        assertTrue(Files.exists(path1));
        data.delete(testPartialPathOld, testUser1);
    }

    @Test
    public void testWriteNewPack() throws IOException {
        data.write(testPartialPathOld, testUser1);
        data.write(testPartialPathOld, testPack1);
        path1 = Paths.get("user_data/users/" + testUsername1Old + "/packages/" + testPackname1Old);
        assertTrue(Files.exists(path1));
        data.delete(testPartialPathOld, testPack1);
        data.delete(testPartialPathOld, testUser1);
    }

    @Test
    public void testWriteNewCard() throws IOException {
        data.write(testPartialPathOld, testUser1);
        data.write(testPartialPathOld, testPack1);
        data.write(testPartialPathOld, testCard1);
        path1 = Paths.get("user_data/users/" + testUsername1Old + "/packages/" + testPackname1Old + "/cards/" + oldTerm
                + ".txt");
        assertTrue(Files.exists(path1));
        data.delete(testPartialPathOld, testCard1);
        data.delete(testPartialPathOld, testPack1);
        data.delete(testPartialPathOld, testUser1);
    }

    @Test
    public void testWriteNewObjectWithException() {
        String string = "string";
        Exception e = assertThrows(Exception.class, () -> data.write(testPartialPathOld, string));
    }

    @Test
    public void testWriteNewUsername() throws IOException {
        data.write(testPartialPathOld, testUser1);
        path1 = Paths.get("user_data/users/" + testUser1.getName());
        assertTrue(Files.exists(path1));
        testUser1.changeName(testUsername1New);
        data.write(testPartialPathOld, testUsername1Old, testUser1);
        assertFalse(Files.exists(path1));
        path2 = Paths.get("user_data/users/" + testUsername1New);
        assertTrue(Files.exists(path2));
        testPartialPathNew = new String[]{testUsername1New, testPackname1Old};
        data.delete(testPartialPathNew, testUser1);
    }

    @Test
    public void testWriteNewPackname() throws IOException {
        data.write(testPartialPathOld, testUser1);
        data.write(testPartialPathOld, testPack1);
        path1 = Paths.get("user_data/users/" + testUsername1Old + "/packages/" + testPackname1Old);
        assertTrue(Files.exists(path1));
        testPack1.changeName(testPackname1New);
        data.write(testPartialPathOld, testPackname1Old, testPack1);
        assertFalse(Files.exists(path1));
        path2 = Paths.get("user_data/users/" + testUsername1Old + "/packages/" + testPackname1New);
        assertTrue(Files.exists(path2));
        testPartialPathNew = new String[]{testUsername1Old, testPackname1New};
        data.delete(testPartialPathNew, testPack1);
        assertFalse(Files.exists(path1));
        data.delete(testPartialPathNew, testUser1);
    }

    @Test
    public void testWriteNewCardTerm() throws IOException {
        data.write(testPartialPathOld, testUser1);
        data.write(testPartialPathOld, testPack1);
        data.write(testPartialPathOld, testCard1);
        Path path1 = Paths.get("user_data/users/" + testUsername1Old + "/packages/" + testPackname1Old
                + "/cards/" + oldTerm + ".txt");
        assertTrue(Files.exists(path1));
        testCard1.setTerm(newTerm);
        data.write(testPartialPathOld, oldTerm, testCard1);
        Path path2 = Paths.get("user_data/users/" + testUsername1Old + "/packages/" + testPackname1Old
                + "/cards/" + newTerm + ".txt");
        assertFalse(Files.exists(path1));
        assertTrue(Files.exists(path2));
        data.delete(testPartialPathOld, testCard1);
        data.delete(testPartialPathOld, testPack1);
        data.delete(testPartialPathOld, testUser1);
    }

    @Test
    public void testDeleteUser() throws IOException {
        data.write(testPartialPathOld, testUser1);
        path1 = Paths.get("user_data/users/" + testUser1.getName());
        assertTrue(Files.exists(path1));
        data.delete(testPartialPathOld, testUser1);
        assertFalse(Files.exists(path1));
    }

    @Test
    public void testDeletePack() throws IOException {
        data.write(testPartialPathOld, testUser1);
        data.write(testPartialPathOld, testPack1);
        path1 = Paths.get("user_data/users/" + testUsername1Old + "/packages/" + testPackname1Old);
        assertTrue(Files.exists(path1));
        data.delete(testPartialPathOld, testPack1);
        assertFalse(Files.exists(path1));
        data.delete(testPartialPathOld, testUser1);
    }

    @Test
    public void testDeleteCard() throws IOException {
        data.write(testPartialPathOld, testUser1);
        data.write(testPartialPathOld, testPack1);
        data.write(testPartialPathOld, testCard1);
        path1 = Paths.get("user_data/users/" + testUsername1Old + "/packages/" + testPackname1Old + "/cards/"
                + oldTerm + ".txt");
        assertTrue(Files.exists(path1));
        data.delete(testPartialPathOld, testCard1);
        assertFalse(Files.exists(path1));
        data.delete(testPartialPathOld, testPack1);
        data.delete(testPartialPathOld, testUser1);
    }

    @Test
    public void testInitialLoad() throws IOException {
        uw1.write();
        HashMap<String, String> result1 = data.initialLoad();
        assertTrue(result1.containsKey(testUsername1Old));
        assertTrue(result1.containsValue("tempPw"));
        assertEquals(result1.get(testUsername1Old), "tempPw");
        uw1.delete();
    }

    @Test
    public void testUserLoad() throws IOException {
        uw1.write();
        assertEquals(0, testUser1.getPackageList().size());
        data.userLoad(testUser1);
        assertEquals(0, testUser1.getPackageList().size());
        testUser1.addPackage(testPack1);
        data.userLoad(testUser1);
        assertEquals(1, testUser1.getPackageList().size());
        uw1.delete();
    }
}