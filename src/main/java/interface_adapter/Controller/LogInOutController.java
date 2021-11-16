package interface_adapter.Controller;

import entity.User;
import use_case.input_boundaries.LogInOutInputBoundary;
import use_case.input_boundaries.UserInputBoundary;
import use_case.output_boundaries.LogInOutOutputBoundary;

/**
 * This controller manages log-in and log-out tasks for users.
 */
public class LogInOutController {
    public final LogInOutInputBoundary logInOutIB;
    public final UserInputBoundary userIB;

    /**
     * interface_adapter.Controller for login
     */
    public LogInOutController(UserInputBoundary userIB, LogInOutInputBoundary logInOutIB) {
        this.userIB = userIB;
        this.logInOutIB = logInOutIB;
    }

    /**
     * login method for user login
     *
     * @param username user's username, cannot be repeated
     * @param password user's password
     */
    public void login(String username, String password, LogInOutOutputBoundary logInOutOB) {
        logInOutIB.logInUser(username, password, logInOutOB);
    }

    /**
     * sign off method for current user.
     */
    public void signOff(LogInOutOutputBoundary logInOutOB) {
        logInOutIB.signOffUser(logInOutOB);
    }

    /**
     * Need to discuss whether this is useful.
     *
     * @return the current user.
     */
    //TODO: if we use observer DP to update program state, controller no need to call on this method
    public User getCurrentUser() throws Exception {
        return this.logInOutIB.getCurrUser();
    }
}
