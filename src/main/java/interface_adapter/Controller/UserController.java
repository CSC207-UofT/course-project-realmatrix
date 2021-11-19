package interface_adapter.Controller;

import entity.Pack;
import entity.User;
import interface_adapter.gateway.IDataInOut;
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

    public UserController(UserInputBoundary userIB, IDataInOut dataInOut) {
        this.userIB = userIB;
        this.dataInOut = dataInOut;
    }

    public void changeUserName(User user, String newName, ChangeOutputBoundary changeOutputBoudary) {
        this.userIB.changeName(user, newName, changeOutputBoudary);
        // TODO: how to save to database? may need overWrite method in gateway.
    }

    public void changePassword(User user, String newPassword) {
        this.userIB.changePassword(user, newPassword);
        // TODO: how to save to database? may need overWrite method in gateway.
    }

    /**
     * User register method.
     *
     * @param username user's username
     * @param password user's password
     */
    public void register(String username, String password, RegisterOutputBoundary registerOB) throws IOException {
        Object object = userIB.createNewUser(username, password, registerOB);
        if (object != null) {
            dataInOut.write(ProgramState.getState(), object);
            // TODO: may not be clean, may need ProgramStateManager or something like that
        }

    }

    public void addPack(User user, Pack pack, AddOutputBoundary AddOutputBoundary) throws IOException {
        if (userIB.addPack(user, pack, AddOutputBoundary)) {
            dataInOut.write(ProgramState.getState(), pack);
            // TODO: may not be clean, may need ProgramStateManager or something like that
        }
    }

    public void deletePack(User user, Pack pack) throws IOException {
        userIB.deletePack(user, pack);
        dataInOut.archive(ProgramState.getState(), pack);
        // TODO: may not be clean, may need ProgramStateManager or something like that
    }
}
