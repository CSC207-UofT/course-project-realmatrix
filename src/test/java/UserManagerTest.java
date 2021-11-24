import entity.User;
import org.junit.Before;
import org.junit.Test;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.RegisterOutputBoundary;
import interface_adapter.presenters.ChangePresenter;
import interface_adapter.presenters.RegisterPresenter;
import use_case.manager.UserManager;

import static org.junit.Assert.assertEquals;

public class UserManagerTest {
    UserManager um;
    final String user1Name = "newUser1";
    final String user1Password = "user_password1";
    final String user2Name = "newUser2";
    final String user2Password = "user_password2";
    final String user2Id = "user_id2";
    User user2;
    final ChangeOutputBoundary changeOutputBoudary = new ChangePresenter();
    final RegisterOutputBoundary registerOB = new RegisterPresenter();

    @Before
    public void createUserManager() {
        um = new UserManager();
        um.createNewUser(user1Name, user1Password, registerOB);
        user2 = new User(user2Name, user2Password);
    }

    /**
     * Test createNewUser with unique username.
     */
    @Test
    public void testCreateNewUser() {
        for (User user : um.getItems()) {
            assertEquals(user1Name, user.getName());
            assertEquals(user1Password, user.getPassword());
        }
    }

    /**
     * Test changeInfo.
     */
    @Test
    public void testChangeInfo() {
        String newName = "user2_newName";
        String newPassword = "user2_newPassword";
        um.changeName(newName, changeOutputBoudary);
        assertEquals(newName, user2.getName());
        um.changePassword(newPassword);
        assertEquals(newPassword, user2.getPassword());
    }
}
