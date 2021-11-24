package interface_adapter.presenters;

import use_case.output_boundaries.RegisterOutputBoundary;

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

    /**
     * Present the registration result.
     * TODO: popping up framework.command_line_interface.GUI windows with such strings.
     * @return
     */
    @Override
    public String presentRegisterResult() {
        if (this.registerResult) {
            return("Registration succeeds!");
        } else {
            return("Registration fails...Please use another username");
        }
    }
}
