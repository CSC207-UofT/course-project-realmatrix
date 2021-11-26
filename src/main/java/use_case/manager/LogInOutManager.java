package use_case.manager;

import entity.User;
import interface_adapter.gateway.IDataInOut;
import use_case.input_boundaries.LogInOutInputBoundary;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.output_boundaries.DatabaseErrorOutputBoundary;
import use_case.output_boundaries.LogInOutOutputBoundary;

import java.io.IOException;
import java.util.HashMap;

public class LogInOutManager implements LogInOutInputBoundary {
    private User currUser; // by default, no current user who is logged in
    private final ProgramStateInputBoundary programStateInputBoundary;
    private final IDataInOut dataInOut;
    private HashMap<String, String> nameToPassword;

    public LogInOutManager(ProgramStateInputBoundary programStateInputBoundary,
                           IDataInOut dataInOut) {
        this.programStateInputBoundary = programStateInputBoundary;
        this.currUser = programStateInputBoundary.getCurrUser();
        this.dataInOut = dataInOut;
    }

    /**
     * Loads a map of username to user password for checking login.
     * @param databaseErrorOutputBoundary an outputBoundary that shows error messages if fail to connect to database.
     */
    @Override
    public void initialLoad(DatabaseErrorOutputBoundary databaseErrorOutputBoundary) {
        try {
            nameToPassword = dataInOut.initialLoad();
        } catch (IOException e) {
            databaseErrorOutputBoundary.presentLoadErrMsg();
        }
    }

    /**
     * Login the user with given username and password.
     * @param name       the username the client enters
     * @param password   the password the client enters
     * @param logInOutOB an outputBoundary that gets the result of login/signoff.
     * @return true if successfully logs in; false otherwise.
     */
    @Override
    public boolean logInUser(String name, String password, LogInOutOutputBoundary logInOutOB) {
         // All username to user password in the database
        if (password.equals(nameToPassword.get(name))) { // Check if there's a user with such username and password
            this.currUser = new User(name, password);
            programStateInputBoundary.setCurrUser(this.currUser);
            logInOutOB.setLogInOutResult(true);
            return true;
        } else {
            logInOutOB.setLogInOutResult(false);
            return false;
        }
    }

    /**
     * Load all packs/cards for current user who just logs in.
     * @param databaseErrorOutputBoundary an outputBoundary that shows error messages if fail to connect to database.
     */
    @Override
    public void userLoad(DatabaseErrorOutputBoundary databaseErrorOutputBoundary) {
        try {
            dataInOut.userLoad(currUser);
        } catch (IOException e) {
            databaseErrorOutputBoundary.presentLoadErrMsg();
        }
    }


    /**
     * Sign off the current user.
     *
     * @param logInOutOB a outputBoundary that gets the result of log in /out.
     */
    @Override
    public void signOffUser(LogInOutOutputBoundary logInOutOB) {
        this.currUser = null;
        programStateInputBoundary.setCurrUser(null);
        logInOutOB.setLogInOutResult(false);
    }
}
