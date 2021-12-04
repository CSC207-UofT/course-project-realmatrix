package interface_adapter.presenters;

import use_case.output_boundaries.DatabaseErrorOutputBoundary;

/**
 * This presenter is responsible for showing database error message (fails to load/save)
 * in the console.
 */
public class DatabaseErrMsgPresenter implements DatabaseErrorOutputBoundary {

    /**
     * Present error messages when saving things into database.
     */
    @Override
    public void presentWriteErrMsg() {
        System.out.println("Cannot save to database. Please try again.");
    }

    /**
     * Present error messages when loading things into database.
     */
    @Override
    public void presentLoadErrMsg() {
        System.out.println("Cannot load database. Please try again.");
    }
}
