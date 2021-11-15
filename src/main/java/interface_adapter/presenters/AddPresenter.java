package interface_adapter.presenters;

import use_case.output_boundaries.AddOutputBoundary;

/**
 * A presenter for add things (card/ pack).
 */
public class AddPresenter implements AddOutputBoundary {
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
