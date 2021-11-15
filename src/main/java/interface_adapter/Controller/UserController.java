package interface_adapter.Controller;

import entity.Pack;
import entity.User;
import use_case.input_boundaries.UserInputBoundary;
import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.RegisterOutputBoundary;

/**
 * A controller that allows users to change username/password and add packs.
 */
public class UserController {
    UserInputBoundary userIB;

    public UserController(UserInputBoundary userIB) {
        this.userIB = userIB;
    }

    public void changeUserName(User user, String newName, ChangeOutputBoundary changeOutputBoudary) {
        this.userIB.changeName(user, newName, changeOutputBoudary);
    }

    /**
     * User register method.
     *
     * @param username user's username
     * @param password user's password
     */
    public void register(String username, String password, RegisterOutputBoundary registerOB) {
        userIB.createNewUser(username, password, registerOB);
    }

    public void addPack(User user, Pack pack, AddOutputBoundary AddOutputBoundary) {
        userIB.addPack(user, pack, AddOutputBoundary);
    }

    public void deletePack(User user, Pack pack) {
        userIB.deletePack(user, pack);
    }
}
