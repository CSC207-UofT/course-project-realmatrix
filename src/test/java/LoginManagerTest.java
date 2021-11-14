import manager.LoginManager;
import manager.UserManager;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class LoginManagerTest {
    UserManager um = new UserManager();
    LoginManager lm;
    String user1Name = "newUser1";
    String user1Password = "user_password1";

    @Before
    public void createLoginManager() throws Exception {
        um.createNewUser(user1Name, user1Password);
        lm = new LoginManager(um);
    }

//    @Test
//    public void testLoggedInUser() throws Exception {
//        lm.logInUser(user1Name, user1Password);
//        assertEquals(lm.getCurrUser().getName(), user1Name);
//    }
//
//    //how to test SignOffUser?

}
