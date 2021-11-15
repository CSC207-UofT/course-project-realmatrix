import manager.LogInOutManager;
import manager.UserManager;
import org.junit.Before;
import org.junit.Test;
import output_boundaries.LogInOutOutputBoundary;
import output_boundaries.RegisterOutputBoundary;
import presenters.LogInOutPresenter;
import presenters.RegisterPresenter;

import static org.junit.Assert.*;


public class LogInOutManagerTest {
    UserManager um = new UserManager();
    LogInOutManager lm;
    String user1Name = "newUser1";
    String user1Password = "user_password1";
    LogInOutOutputBoundary lp = new LogInOutPresenter();
    RegisterOutputBoundary rp = new RegisterPresenter();

    @Before
    public void createLogInOutManager() {
        um.createNewUser(user1Name, user1Password, rp);
        lm = new LogInOutManager(um);
    }

    @Test
    public void testLoggedInUser() throws Exception {
        lm.logInUser(user1Name, user1Password, lp);
        assertEquals(lm.getCurrUser().getName(), user1Name);
    }

    @Test
    public void testSignOffUser(){
        try{
            lm.signOffUser(lp);
            lm.getCurrUser();
            fail("Expected an Exception to be thrown");
        }catch (Exception e){
            assertEquals ("There's no logged-in user.", e.getMessage());
        }
    }

}
