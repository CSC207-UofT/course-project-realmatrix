package Controller;

import entity.User;
import input_boundaries.LogInOutInputBoundary;
import input_boundaries.UserInputBoundary;
import output_boundaries.LogInOutOutputBoundary;

/**
 * This controller manages log-in and log-out tasks for users.
 */
public class LogInOutController {
    public LogInOutInputBoundary logInOutIB;
    public UserInputBoundary userIB;

    /**
     * Controller for login
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
