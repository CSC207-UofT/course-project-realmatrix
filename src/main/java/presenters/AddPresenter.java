package presenters;

import output_boundaries.AddOutputBoundary;

/**
 * A presenter for add things (card/ pack).
 */
public class AddPresenter implements AddOutputBoundary {
    @Override
    public void presentAddSuccessView() {
        // TODO: GUI presents this newly added object
        System.out.println("Successfully added.");
    }

    @Override
    public void presentAddFailView() {
        // TODO: GUI pops up a warning window.
        System.out.println("Name already exists. Try a new name.");
    }
}
