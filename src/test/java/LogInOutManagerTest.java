import interface_adapter.gateway.DataInOut;
import interface_adapter.presenters.DatabaseErrMsgPresenter;
import org.junit.Before;
import org.junit.Test;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.manager.ProgramStateManager;
import use_case.output_boundaries.LogInOutOutputBoundary;
import use_case.output_boundaries.RegisterOutputBoundary;
import interface_adapter.presenters.LogInOutPresenter;
import interface_adapter.presenters.RegisterPresenter;
import use_case.manager.LogInOutManager;
import use_case.manager.UserManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class LogInOutManagerTest {
    final ProgramStateInputBoundary programStateInputBoundary = new ProgramStateManager();
    LogInOutManager lm;
    final String user1Name = "xing";
    final String user1Password = "password";
    final LogInOutOutputBoundary lp = new LogInOutPresenter();

    @Before
    public void createLogInOutManager() {
        lm = new LogInOutManager(programStateInputBoundary, new DataInOut());
    }

    @Test(timeout = 200)
    public void testLoggedInUser() {
        lm.initialLoad(new DatabaseErrMsgPresenter());
        lm.logInUser(user1Name, user1Password, lp);
        assertEquals(programStateInputBoundary.getCurrUserName(), user1Name);
    }

    //how to test SignOffUser?
    @Test(timeout = 200)
    public void testSignOffUser() {
        lm.signOffUser(lp);
        assertNull(programStateInputBoundary.getCurrUser());
    }

}
