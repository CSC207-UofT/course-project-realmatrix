package interface_adapter.presenters;

import use_case.output_boundaries.RegisterOutputBoundary;

/**
 * A presenter that shows the result of registration.
 */
public class RegisterPresenter implements RegisterOutputBoundary {
    private boolean registerResult; // A boolean value representing registration succeeds or fails

    /**
     * Get the registration result.
     *
     * @param result a boolean value representing registration succeeds or fails.
     */
    @Override
    public void setRegisterResult(boolean result) {
        this.registerResult = result;
    }

    /**
     * Getter of registerResult
     * @return true iff user is registered.
     */
    @Override
    public boolean getRegisterResult(){
        return this.registerResult;
    }
}
