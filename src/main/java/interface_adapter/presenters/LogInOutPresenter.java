package interface_adapter.presenters;

import use_case.manager.LogInOutManager;
import use_case.output_boundaries.LogInOutOutputBoundary;

public class LogInOutPresenter implements LogInOutOutputBoundary {
    private LogInOutManager.LoggedIn loginResult; // A boolean value representing login succeeds or fails

    /**
     * Get the login result from LoginManager's login method.
     *
     * @param loginResult a boolean value representing login succeeds or fails.
     */
    @Override
    public void setLogInOutResult(LogInOutManager.LoggedIn loginResult) {
        this.loginResult = loginResult;
    }

    /**
     * Present the login result.
     * TODO: popping up framework.command_line_interface.GUI windows with such string.
     * @return
     */
    @Override
    public String presentLogInOutResult() {
        switch (loginResult) {
            case LOGIN_SUCCEED:
                return("Login succeeds!");

            case NO_SUCH_USER:
                return(
                        "Invalid name or password. If you are new, please create an account first.");

            case ALREADY_LOGGED_IN:
                return("You are signed in. Please sign off first.");

            case SIGNED_OFF:
                return("Logged out successfully");
        }
        return null;
    }
}
