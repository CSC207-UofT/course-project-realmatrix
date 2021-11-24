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
     * Getter for LogInOutResult
     * @return true iff user successfully logged in.
     */
    @Override
    public boolean getLogInOutResult(){
        switch (loginResult) {
            case SUCCEED:
                return true;

            case FAIL:
                return false;
        }
        return false;
    }
    /**
     * Present the login result.
     * TODO: popping up framework.command_line_interface.GUI windows with such string.
     * @return
     */
    @Override
    public String presentLogInOutResult() {
        switch (loginResult) {
            case SUCCEED:
                return("Login succeeds!");

            case FAIL:
                return(
                        "Invalid name or password. If you are new, please create an account first.");
        }
        return null;
    }
}
