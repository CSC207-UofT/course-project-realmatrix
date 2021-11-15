package presenters;

import output_boundaries.RegisterOutputBoundary;

public class RegisterPresenter implements RegisterOutputBoundary {
    private boolean registerResult; // A boolean value representing registration succeeds or fails

    /**
     * Get the registration result.
     *
     * @param result a boolean value representing registration succeeds or fails.
     */
    public void setRegisterResult(boolean result) {
        this.registerResult = result;
    }

    /**
     * Present the registration result.
     * TODO: popping up GUI windows with such strings.
     */
    public void presentRegisterResult() {
        if (this.registerResult) {
            System.out.println("Registration succeeds!");
        } else {
            System.out.println("Registration fails...Please use another username");
        }
    }
}
