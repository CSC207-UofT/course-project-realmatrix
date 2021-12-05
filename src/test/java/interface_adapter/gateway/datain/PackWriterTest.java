package interface_adapter.gateway.datain;

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

public class PackWriterTest {
    final String testUsername = "testPackWriterUser1";
    final String testUserPw = "testPw";
    final String testPackNewName = "testPackWriterUser1new";
    final String testPackName = "testPack1";
    String[] partialPath;
    PackWriter pw;
    UserWriter uw;
    Pack testPack;
    User testUser;
    Path path1;
    Path path2;

    @Before
    public void setUp() throws Exception {
        testPack = new Pack(testPackName);
        testUser = new User(testUsername, testUserPw);
        partialPath = new String[]{testUsername, testPackName};
        uw = new UserWriter(partialPath, testUser);
        pw = new PackWriter(partialPath, testPack);
        uw.write();
    }

    @Test
    public void testWriteNewPack() {
        pw.write();
        path1 = Paths.get("user_data/users/" + testUsername + "/packages/" + testPackName);
        assertTrue(Files.exists(path1));
        pw.delete();
    }

    @Test
    public void testDeletePack() {
        pw.write();
        path1 = Paths.get("user_data/users/" + testUsername + "/packages/" + testPackName);
        pw.delete();
        assertFalse(Files.exists(path1));
        uw.delete();
    }

    @Test
    public void testWriteNewName() throws IOException {
        pw.write();
        path1 = Paths.get("user_data/users/" + testUsername + "/packages/" + testPackName);
        assertTrue(Files.exists(path1));
        testPack.changeName(testPackNewName);
        pw.write(testPackName, testPack);
        assertFalse(Files.exists(path1));
        path2 = Paths.get("user_data/users/" + testUsername + "/packages/" + testPackNewName);
        assertTrue(Files.exists(path2));
        pw.delete();
        assertFalse(Files.exists(path1));
        uw.delete();
    }

    @After
    public void tearDown() {
        uw.delete();
    }
}