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

    public void changeUserName(User user, String newName, ChangeOutputBoundary changeOutputBoudary) throws IOException {
        this.userIB.changeName(user, newName, changeOutputBoudary);
    }

    public void changePassword(User user, String newPassword) throws IOException {
        this.userIB.changePassword(user, newPassword);
    }

    /**
     * User register method.
     *
     * @param username user's username
     * @param password user's password
     */
    public void register(String username, String password, RegisterOutputBoundary registerOB) throws IOException {
        Object object = userIB.createNewUser(username, password, registerOB);
    }

    public void addPack(Pack pack, AddOutputBoundary addOutputBoundary, ProgramStateInputBoundary programStateInputBoundary) throws IOException {
        userIB.addPack(pack, addOutputBoundary, programStateInputBoundary);
    }

    public void deletePack(User user, Pack pack) throws IOException {
        userIB.deletePack(user, pack);
    }
}
