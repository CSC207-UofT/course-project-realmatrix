package interface_adapter.presenters;

import use_case.output_boundaries.AddOutputBoundary;

/**
 * A presenter for add things (card/ pack).
 */
public class AddPresenter implements AddOutputBoundary {
    private boolean addResult;

    /**
     * Setter of addResult
     * @param addResult set value of add result.
     */
    @Override
    public void setAddResult(boolean addResult) {
        this.addResult = addResult;
    }


    /**
     * Getter of addResult
     * @return true if added successfully
     */
    @Override
    public boolean getAddResult() {
        return this.addResult;
    }
}
