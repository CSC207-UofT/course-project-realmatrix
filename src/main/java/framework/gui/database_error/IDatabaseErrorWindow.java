package framework.gui.database_error;

/**
 * This interface is used for dependency inversion:
 * DatabaseErrorPresenter can call methods in this interface to pop up error window to user.
 */
public interface IDatabaseErrorWindow {
    /**
     * Present load database error message.
     */
    void presentLoadErrMsg();

    /**
     * Present error messages for saving to database.
     */
    void presentWriteErrMsg();
}
