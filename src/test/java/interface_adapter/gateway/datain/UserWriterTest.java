package interface_adapter.gateway.datain;

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

public class UserWriterTest {
    UserWriter uw;
    String testUsername = "testUserWriterUser1";
    String testUserPw = "testPw";
    String testPackName = "testPack1";
    String[] partialPath;
    User testUser;
    Path path1;
    Path path2;
    String testUserNewName = "testUserWriterUser1New";

    @Before
    public void setUp() {
        testUser = new User(testUsername, testUserPw);
        partialPath = new String[]{testUsername, testPackName};
        uw =  new UserWriter(partialPath, testUser);
    }

    @Test
    public void testWriteNewUser() throws IOException {
        uw.write();
        path1 = Paths.get("user_data/users/" + testUser.getName());
        assertTrue(Files.exists(path1));
        uw.delete();
        assertFalse(Files.exists(path1));
    }

    @Test
    public void testWriteRenameUser() throws IOException {
        uw.write();
        path1 = Paths.get("user_data/users/" + testUser.getName());
        assertTrue(Files.exists(path1));
        testUser.changeName(testUserNewName);
        uw.write(testUsername, testUser);
        assertFalse(Files.exists(path1));
        path2 = Paths.get("user_data/users/" + testUser.getName());
        assertTrue(Files.exists(path2));
        uw.delete();
    }

    @After
    public void tearDown() {
        uw.delete();
    }
}