import manager.UserManager;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserManagerTest {
    UserManager um;
    String user1Name = "newUser1";
    String user1Password = "user_password1";
    String user2Name = "newUser2";
    String user2Password = "user_password2";
    String user2Id = "user_id2";
    User user2;

    @Before
    public void createUserManager() throws Exception {
        um = new UserManager();
        um.createNewUser(user1Name, user1Password);
        user2 = new User(user2Id, user2Name, user2Password);
    }

    /**
     * Test createNewUser with unique username.
     */
    @Test
    public void testCreateNewUser() {
        for (User user : um.getItems().values()){
            assertEquals(user1Name, user.getName());
            assertEquals(user1Password, user.getPassword());
        }
    }

    /**
     * Test putUser.
     */
    @Test
    public void testPutUser(){
        um.putUser(user2.getId(), user2);
        assertEquals(user2, um.getItems().get(user2.getId()));
    }

    /**
     * Test changeInfo.
     */
    @Test
    public void testChangeInfo(){
        String newName = "user2_newName";
        String newPassword = "user2_newPassword";
        um.changeInfo(user2, 'N', newName);
        assertEquals(newName, user2.getName());
        um.changeInfo(user2, 'P', newPassword);
        assertEquals(newPassword, user2.getPassword());
    }
}
