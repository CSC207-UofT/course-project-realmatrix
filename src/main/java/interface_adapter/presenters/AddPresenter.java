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

    @Override
    public void presentAddSuccessView() {
        // TODO: framework.command_line_interface.GUI presents this newly added object
        System.out.println("Successfully added.");
    }

    @Override
    public void presentAddFailView() {
        // TODO: framework.command_line_interface.GUI pops up a warning window.
        System.out.println("Name already exists. Try a new name.");
    }
}
