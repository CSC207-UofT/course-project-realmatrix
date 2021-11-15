package use_case.manager;

import entity.User;
import input_boundaries.LogInOutInputBoundary;
import output_boundaries.LogInOutOutputBoundary;

import java.util.Objects;

public class LogInOutManager implements LogInOutInputBoundary {
    private LoggedIn loggedIn; // by default, no user logged in
    private Object currUser; // by default, no current user who is logged in
    private final UserManager manager;

    public LogInOutManager(UserManager manager) {
        this.loggedIn = LoggedIn.SIGNED_OFF;
        this.currUser = null;
        this.manager = manager;
    }

    /**
     * A enum class representing logged in status.
     */
    public enum LoggedIn {
        SIGNED_OFF,         // No user is logged in currently
        LOGIN_SUCCEED,      // Login succeeds
        NO_SUCH_USER,       // Login fails
        ALREADY_LOGGED_IN,  // There's already a user logged in the program, cannot log in again
    }

    @Override
    public User getCurrUser() throws Exception {
        System.out.println(this.currUser);
        if (this.currUser instanceof User) {
            return (User) this.currUser;
        } else {
            throw new Exception("There's no logged-in user.");
        }
    }

    /**
     * /**
     * Return the User with given name and password
     *
     * @param name     name of a User
     * @param password password of a User (matches the name)
     * @return User    the user with given name and password
     * @throws Exception A UserNotFound exception.
     */
    private User findUser(String name, String password) throws Exception {
        for (User user : this.manager.getItems().values()) {
            if (Objects.equals(user.getName(), name) && Objects.equals(user.getPassword(), password)) {
                return user;
            }
        }
        // TODO: create a more specific exception constant, e.g. UserNotFound exception.
        throw new Exception("No such user.");
    }

    /**
     * Login the user with given username and password.
     *
     * @param name       the username the client enters
     * @param password   the password the client enters
     * @param logInOutOB a outputBoundary that gets the result of login.
     */
    @Override
    public void logInUser(String name, String password, LogInOutOutputBoundary logInOutOB) {
        if (this.loggedIn == LoggedIn.LOGIN_SUCCEED) {
            // This is the case where there's already a user signed in.
            this.loggedIn = LoggedIn.ALREADY_LOGGED_IN;
        } else {
            try {
                this.currUser = this.findUser(name, password); // Check if there's a user with such username and password
                this.loggedIn = LoggedIn.LOGIN_SUCCEED;
            } catch (Exception e) { //TODO: UserNotExist exception
                this.loggedIn = LoggedIn.NO_SUCH_USER;
            }
        }
        logInOutOB.setLogInOutResult(this.loggedIn);
    }

    /**
     * Sign off the current user.
     *
     * @param logInOutOB
     */
    @Override
    public void signOffUser(LogInOutOutputBoundary logInOutOB) {
        this.loggedIn = LoggedIn.SIGNED_OFF;
        this.currUser = null;
        logInOutOB.setLogInOutResult(this.loggedIn);
    }
}
