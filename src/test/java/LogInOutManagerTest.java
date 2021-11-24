//import org.junit.Before;
//import org.junit.Test;
//import use_case.output_boundaries.LogInOutOutputBoundary;
//import use_case.output_boundaries.RegisterOutputBoundary;
//import interface_adapter.presenters.LogInOutPresenter;
//import interface_adapter.presenters.RegisterPresenter;
//import use_case.manager.LogInOutManager;
//import use_case.manager.UserManager;
//
//
//public class LogInOutManagerTest {
//    final UserManager um = new UserManager();
//    LogInOutManager lm;
//    final String user1Name = "newUser1";
//    final String user1Password = "user_password1";
//    final LogInOutOutputBoundary lp = new LogInOutPresenter();
//    final RegisterOutputBoundary rp = new RegisterPresenter();
//
//    @Before
//    public void createLogInOutManager() {
//        um.createNewUser(user1Name, user1Password, rp);
//        lm = new LogInOutManager(um, );
//    }
//
//    @Test
//    public void testLoggedInUser() throws Exception {
//        lm.logInUser(user1Name, user1Password, lp);
//        assertEquals(lm.getCurrUser().getName(), user1Name);
//    }
//
//    //how to test SignOffUser?
//
//}
