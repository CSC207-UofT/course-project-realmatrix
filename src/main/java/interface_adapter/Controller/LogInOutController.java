package interface_adapter.Controller;

import entity.User;
import interface_adapter.gateway.IDataInOut;
import use_case.input_boundaries.LogInOutInputBoundary;
import use_case.input_boundaries.UserInputBoundary;
import use_case.output_boundaries.DatabaseErrorOutputBoundary;
import use_case.output_boundaries.LogInOutOutputBoundary;

import java.util.HashMap;

/**
 * This controller manages log-in and log-out tasks for users.
 */
public class LogInOutController {
    public final LogInOutInputBoundary logInOutIB;

    /**
     * interface_adapter.Controller for login
     */
    public LogInOutController(LogInOutInputBoundary logInOutIB) {
        this.logInOutIB = logInOutIB;
    }

    /**
     * login method for user login
     *
     * @param username user's username, cannot be repeated
     * @param password user's password
     */
    public void login(String username, String password, LogInOutOutputBoundary logInOutOB, DatabaseErrorOutputBoundary databaseErrorOutputBoundary) {
        logInOutIB.initialLoad(databaseErrorOutputBoundary);
        if (logInOutIB.logInUser(username, password, logInOutOB)) { // login succeeds
            logInOutIB.userLoad(databaseErrorOutputBoundary); // loads all packs/cards for this user
        }
    }

    /**
     * sign off method for current user.
     */
    public void signOff(LogInOutOutputBoundary logInOutOB) {
        logInOutIB.signOffUser(logInOutOB);
    }
}
