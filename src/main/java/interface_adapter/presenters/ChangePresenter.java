package interface_adapter.presenters;

import use_case.output_boundaries.ChangeOutputBoundary;

public class ChangePresenter implements ChangeOutputBoundary {
    private boolean changeResult;

    /**
     * Setter of change result
     * @param result set value of change result.
     */
    @Override
    public void setChangeResult(boolean result) {
        this.changeResult = result;
    }

    /**
     * Getter of change result
     * @return true if changed successfully
     */
    @Override
    public boolean getChangeResult() {
        return this.changeResult;
    }

    @Override
    public String presentChangeResult() {
        if (this.changeResult) {
            return("OK! You have the new username now.");
        } else {
            return("This name is taken. Please use another username");
        }
    }
}
