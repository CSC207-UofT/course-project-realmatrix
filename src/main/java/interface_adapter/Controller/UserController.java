package interface_adapter.Controller;

import entity.Pack;
import entity.ProgramState;
import entity.User;
import interface_adapter.gateway.IDataInOut;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.input_boundaries.UserInputBoundary;
import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.DatabaseErrorOutputBoundary;
import use_case.output_boundaries.RegisterOutputBoundary;

import java.io.IOException;

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

    public void changeUserName(String oldName, String newName, ChangeOutputBoundary changeOutputBoundary) {
        if (this.userIB.changeName(newName, changeOutputBoundary)) {
            this.userIB.write(oldName, databaseErrorOutputBoundary);
        }
    }

    public void changePassword(String newPassword) {
        this.userIB.changePassword(newPassword);
        this.userIB.write(databaseErrorOutputBoundary);
    }

    /**
     * User register method.
     *
     * @param username user's username
     * @param password user's password
     */
    public void register(String username, String password, RegisterOutputBoundary registerOB) throws IOException {
        if (userIB.createNewUser(username, password, registerOB)) {
            this.userIB.write(databaseErrorOutputBoundary);
        }
    }
}
