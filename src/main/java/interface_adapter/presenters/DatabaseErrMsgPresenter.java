package interface_adapter.presenters;

import framework.GUI.database_error.IDatabaseErrorWindow;
import use_case.output_boundaries.DatabaseErrorOutputBoundary;

/**
 * This presenter is responsible for showing database error message (fails to load/save)
 * This class uses dependency inversion to pop up a GUI window.
 */
public class DatabaseErrMsgPresenter implements DatabaseErrorOutputBoundary {
    private final IDatabaseErrorWindow databaseErrorWindow;

    public DatabaseErrMsgPresenter(IDatabaseErrorWindow databaseErrorWindow) {
        this.databaseErrorWindow = databaseErrorWindow;
    }
    
    /**
     * Present error messages when saving things into database.
     */
    @Override
    public void presentWriteErrMsg() {
        databaseErrorWindow.presentWriteErrMsg();
    }

    /**
     * Present error messages when loading things into database.
     */
    @Override
    public void presentLoadErrMsg() {
        databaseErrorWindow.presentLoadErrMsg();
    }
}
