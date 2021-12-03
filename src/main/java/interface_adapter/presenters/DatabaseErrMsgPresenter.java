package interface_adapter.presenters;

import use_case.output_boundaries.DatabaseErrorOutputBoundary;

/**
 * This presenter is responsible for showing database error message (fails to load/save)
 * in the console.
 */
public class DatabaseErrMsgPresenter implements DatabaseErrorOutputBoundary {

    @Override
    public void presentWriteErrMsg() {
        System.out.println("Cannot save to database. Please try again.");
    }

    @Override
    public void presentLoadErrMsg() {
        System.out.println("Cannot load database. Please try again.");
    }
}
