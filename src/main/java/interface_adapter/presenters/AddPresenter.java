package interface_adapter.presenters;

import use_case.output_boundaries.AddOutputBoundary;

/**
 * A presenter for add things (card/ pack).
 */
public class AddPresenter implements AddOutputBoundary {
    private boolean addResult;

    @Override
    /**
     * Setter of addResult
     * @param addResult set value of add result.
     */
    public void setAddResult(boolean addResult) {
        this.addResult = addResult;
    }

    @Override
    /**
     * Getter of addResult
     * @return true if added successfully
     */
    public boolean getAddResult() {
        return this.addResult;
    }
}
