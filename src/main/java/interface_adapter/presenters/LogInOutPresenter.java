package interface_adapter.presenters;

import use_case.output_boundaries.LogInOutOutputBoundary;
import use_case.manager.LogInOutManager;

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
     */
    @Override
    public void presentLogInOutResult() {
        switch (loginResult) {
            case LOGIN_SUCCEED:
                System.out.println("Login succeeds!");
                break;

            case NO_SUCH_USER:
                System.out.println(
                        "Invalid name or password. If you are new, please create an account first.");
                break;

            case ALREADY_LOGGED_IN:
                System.out.println("You are signed in. Please sign off first.");
                break;

            case SIGNED_OFF:
                System.out.println("Logged out successfully");
                break;
        }
    }
}
