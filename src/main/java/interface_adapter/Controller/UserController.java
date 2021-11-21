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
    final ProgramState programState;

    public UserController(UserInputBoundary userIB, IDataInOut dataInOut, ProgramState programState) {
        this.userIB = userIB;
        this.dataInOut = dataInOut;
        this.programState = programState;
    }

    public void changeUserName(User user, String newName, ChangeOutputBoundary changeOutputBoudary) throws IOException {
        if (this.userIB.changeName(user, newName, changeOutputBoudary)) {
            dataInOut.write(this.programState, user);
        }
    }

    public void changePassword(User user, String newPassword) throws IOException {
        this.userIB.changePassword(user, newPassword);
        dataInOut.write(this.programState, user);
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
            dataInOut.write(this.programState, object);
            // TODO: may not be clean, may need ProgramStateManager or something like that
        }

    }

    public void addPack(User user, Pack pack, AddOutputBoundary AddOutputBoundary) throws IOException {
        if (userIB.addPack(user, pack, AddOutputBoundary)) {
            dataInOut.write(this.programState, pack);
            // TODO: may not be clean, may need ProgramStateManager or something like that
        }
    }

    public void deletePack(User user, Pack pack) throws IOException {
        userIB.deletePack(user, pack);
        dataInOut.archive(this.programState, pack);
        // TODO: may not be clean, may need ProgramStateManager or something like that
    }
}
