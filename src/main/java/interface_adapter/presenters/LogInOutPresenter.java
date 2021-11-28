package interface_adapter.presenters;

import use_case.output_boundaries.LogInOutOutputBoundary;

public class LogInOutPresenter implements LogInOutOutputBoundary {
    private boolean loginOutResult; // A boolean value representing login/out succeeds or fails

    /**
     * Get the login result from LoginManager's login method.
     *
     * @param loginResult a boolean value representing login succeeds or fails.
     */
    @Override
    public void setLogInOutResult(boolean loginResult) {
        this.loginOutResult = loginResult;
    }

    /**
     * Getter for LogInOutResult
     * @return true iff user successfully logged in.
     */
    @Override
    public boolean getLogInOutResult(){
        return loginOutResult;
    }
}
