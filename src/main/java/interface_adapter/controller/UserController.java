package interface_adapter.controller;

import interface_adapter.gateway.IDataInOut;
import use_case.input_boundaries.UserInputBoundary;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.DatabaseErrorOutputBoundary;

/**
 * A controller that allows users to change username/password and add packs.
 */
public class UserController {
    final UserInputBoundary userIB;
    private final DatabaseErrorOutputBoundary databaseErrorOutputBoundary;

    public UserController(UserInputBoundary userIB, DatabaseErrorOutputBoundary databaseErrorOutputBoundary) {
        this.userIB = userIB;
        this.databaseErrorOutputBoundary = databaseErrorOutputBoundary;
        userIB.initialLoad(databaseErrorOutputBoundary);
    }

    /**
     * Change username and write into database if successfully changed.
     *
     * @param oldName              the user's old username.
     * @param newName              the user's new username.
     * @param dataInOut            A data access interface for writing the new name into database.
     * @param changeOutputBoundary An output boundary for getting the result of changing username.
     */
    public void changeUserName(String oldName, String newName, IDataInOut dataInOut, ChangeOutputBoundary changeOutputBoundary) {
        if (this.userIB.changeName(newName, changeOutputBoundary)) {
            this.userIB.write(oldName, dataInOut, databaseErrorOutputBoundary);
        }
    }

    /**
     * Change user's password and write into database.
     *
     * @param newPassword the user's new password.
     * @param dataInOut   A data access interface for writing the new name into database.
     */
    public void changePassword(String newPassword, IDataInOut dataInOut) {
        this.userIB.changePassword(newPassword);
        this.userIB.write(dataInOut, databaseErrorOutputBoundary);
    }
}
