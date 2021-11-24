package interface_adapter.presenters;

import use_case.output_boundaries.DatabaseErrorOutputBoundary;

/**
 * This presenter is responsible for showing database error message (fails to load/save)
 * to user.
 */
public class DatabaseErrMsgPresenter implements DatabaseErrorOutputBoundary {

    @Override
    public void presentWriteErrMsg() {
        //TODO: call a new ErrorFrame showing messages, e.g.
        // new WriteErrFrame("Cannot save to database. Please try again.")
    }

    @Override
    public void presentLoadErrMsg() {
        //TODO: call a new ErrorFrame showing messages, e.g.
        // new LoadErrFrame("Cannot load database. Please try again.")
    }
}
