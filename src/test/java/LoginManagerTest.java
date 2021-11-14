import entity.User;
import manager.LoginManager;
import manager.UserManager;
import presenters.LoginPresenter;
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

    @Test
    public void testLoggedInUser() throws Exception {
        LoginPresenter lp = new LoginPresenter(user1Name);
        lm.logInUser(user1Name, user1Password, lp);
        assertEquals(lm.getCurrUser().getName(), user1Name);
    }

    @Test
    public void testGetCurrUser() throws Exception{
        LoginPresenter lp = new LoginPresenter(user1Name);
        lm.logInUser(user1Name, user1Password, lp);
        for (User u: um.getItems().values()){
            assertEquals(lm.getCurrUser(), u);
        }
    }
    //how to test SignOffUser?
}