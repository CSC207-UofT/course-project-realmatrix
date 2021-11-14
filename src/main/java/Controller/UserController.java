package Controller;

import input_boundaries.UserInputBoundary;
import output_boundaries.AddOutputBoundary;
import output_boundaries.ChangeOutputBoundary;
import output_boundaries.RegisterOutputBoundary;
import entity.*;

/**
 * A controller that allows users to change username/password and add packs.
 */
public class UserController {
    UserInputBoundary userIB;

    public UserController(UserInputBoundary userIB){
        this.userIB = userIB;
    }

    public void changeUserName(User user, String newName, ChangeOutputBoundary changeOutputBoudary) {
        this.userIB.changeName(user, newName, changeOutputBoudary);
    }
    /**
     * User register method.
     * @param username user's username
     * @param password user's password
     */
    public void register(String username, String password, RegisterOutputBoundary registerOB){
        userIB.createNewUser(username,password, registerOB);
    }
    
    public void addPack(User user, Pack pack, AddOutputBoundary AddOutputBoundary) {
        userIB.addPack(user, pack, AddOutputBoundary);
    }

    public void deletePack(User user, Pack pack) {
        userIB.deletePack(user, pack);
    }
}
