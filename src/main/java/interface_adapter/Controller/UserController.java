package interface_adapter.Controller;

import entity.Pack;
import entity.ProgramState;
import entity.User;
import interface_adapter.gateway.IDataInOut;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.input_boundaries.UserInputBoundary;
import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.RegisterOutputBoundary;

import java.io.IOException;

/**
 * A controller that allows users to change username/password and add packs.
 */
public class UserController {
    final UserInputBoundary userIB;
    final IDataInOut dataInOut;
    final ProgramState programState;

    public UserController(UserInputBoundary userIB, IDataInOut dataInOut, ProgramState programState) {
        this.userIB = userIB;
        this.dataInOut = dataInOut;
        this.programState = programState;
    }

    public void changeUserName(String newName, ChangeOutputBoundary changeOutputBoudary) throws IOException {
        if (this.userIB.changeName(newName, changeOutputBoudary)) {
            this.userIB.write();
        }
    }

    public void changePassword(String newPassword) throws IOException {
        this.userIB.changePassword(newPassword);
        this.userIB.write();
    }

    /**
     * User register method.
     *
     * @param username user's username
     * @param password user's password
     */
    public void register(String username, String password, RegisterOutputBoundary registerOB) throws IOException {
        if (userIB.createNewUser(username, password, registerOB)) {
            this.userIB.write();
        }
    }
}
