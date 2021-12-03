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
    }

    public void changeUserName(String oldName, String newName, IDataInOut dataInOut, ChangeOutputBoundary changeOutputBoundary) {
        if (this.userIB.changeName(newName, changeOutputBoundary)) {
            this.userIB.write(oldName, dataInOut, databaseErrorOutputBoundary);
        }
    }

    public void changePassword(String newPassword, IDataInOut dataInOut) {
        this.userIB.changePassword(newPassword);
        this.userIB.write(dataInOut, databaseErrorOutputBoundary);
    }
}
