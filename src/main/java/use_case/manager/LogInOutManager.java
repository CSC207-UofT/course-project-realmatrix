package use_case.manager;

import entity.User;
import interface_adapter.gateway.IDataInOut;
import use_case.input_boundaries.LogInOutInputBoundary;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.output_boundaries.LogInOutOutputBoundary;

import java.util.HashMap;

public class LogInOutManager implements LogInOutInputBoundary {
    private LoggedIn loggedIn; // by default, no user logged in
    private User currUser; // by default, no current user who is logged in
    private final ProgramStateInputBoundary programStateInputBoundary;
    private final IDataInOut dataInOut;

    public LogInOutManager(ProgramStateInputBoundary programStateInputBoundary, IDataInOut dataInOut) {
        this.loggedIn = LoggedIn.FAIL;
        this.programStateInputBoundary = programStateInputBoundary;
        this.currUser = programStateInputBoundary.getCurrUser();
        this.dataInOut = dataInOut;
    }

    /**
     * A enum class representing logged in status.
     */
    public enum LoggedIn {
        SUCCEED,      // Login succeeds
        FAIL,       // Login fails
    }

    /**
     * Login the user with given username and password.
     * @param name       the username the client enters
     * @param password   the password the client enters
     * @param logInOutOB a outputBoundary that gets the result of login/signoff.
     */
    @Override
    public void logInUser(String name, String password, LogInOutOutputBoundary logInOutOB) throws Exception {
        HashMap<String, String> nameToPassword = dataInOut.initialLoad(); // All username to user password in the database
        if (password.equals(nameToPassword.get(name))) { // Check if there's a user with such username and password
            this.loggedIn = LoggedIn.SUCCEED;
            this.currUser = new User(name, password);
            dataInOut.userLoad(this.currUser);  // Load all packs/cards for this user
            programStateInputBoundary.setCurrUser(this.currUser);
        } else {
            this.loggedIn = LoggedIn.FAIL;
        }
        logInOutOB.setLogInOutResult(this.loggedIn);
    }


    /**
     * Sign off the current user.
     *
     * @param logInOutOB a outputBoundary that gets the result of log in /out.
     */
    @Override
    public void signOffUser(LogInOutOutputBoundary logInOutOB) {
        this.loggedIn = LoggedIn.FAIL;
        this.currUser = null;
        logInOutOB.setLogInOutResult(this.loggedIn);
    }
}
