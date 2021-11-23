package use_case.manager;

import entity.User;
import interface_adapter.gateway.IDataInOut;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.input_boundaries.UserInputBoundary;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.RegisterOutputBoundary;

import java.io.IOException;
import java.util.HashMap;

/**
 * A manager that manages the current user's actions, including
 *      - registration
 *      - changing username
 *      - changing password
 */
//TODO: implement <sort> interface
public class UserManager extends Manager<User> implements UserInputBoundary {
    private final HashMap<String, String> items;
    // Note items for this manager is a map <username: password>

    public UserManager(IDataInOut dataInOut, ProgramStateInputBoundary programStateInputBoundary) throws IOException {
        super(dataInOut, programStateInputBoundary);
        this.currItem = programStateInputBoundary.getCurrUser();
        this.items = dataInOut.initialLoad();
    }

    /**
     * This is the registration method for user.
     * Create a new user if the user doesn't exist in database.
     * @param name       the username of this user
     * @param password   the password of this user
     * @param registerOB the output boundary (abstract interface for presenter)
     */
    public boolean createNewUser(String name, String password, RegisterOutputBoundary registerOB) {
        if (!this.items.containsKey(name)) { // No user of such username, valid for registration
            this.currItem = new User(name, password);
            programStateInputBoundary.setCurrUser(this.currItem);
            registerOB.setRegisterResult(true);
            return true;
        } else {
            registerOB.setRegisterResult(false);
            return false;
        }
    }

    /**
     * Change name of current user.
     *
     * @param newName    the new name the user wants to change to
     * @return true if user successfully changed name; false otherwise
     */
    public boolean changeName(String newName, ChangeOutputBoundary changeOutputBoundary) {
        Object item = searchItem(newName);
        if (item == null) { // No user of such username, valid for change
            this.items.remove(currItem.getName()); // Remove user with old name
            currItem.changeName(newName);
            this.items.put(newName, currItem.getPassword());     // Add user with new name
            changeOutputBoundary.setChangeResult(true);
            return true;
        } else {
            changeOutputBoundary.setChangeResult(false);
            return false;
        }
    }

    /**
     * Change password of current user.
     * @param newPassword new password*/
    @Override
    public void changePassword(String newPassword) {
        this.currItem.changePassword(newPassword);
    }

//    /**
//     * Add pack into the user's arraylist of packs.
//     *
//     * @param pack the pack to be added
//     * @return true if successfully added; false otherwise
//     */
//    @Override
//    public boolean addPack(Pack pack, AddOutputBoundary AddOutputBoundary) {
//        try {
//            programStateInputBoundary.getCurrUser().addPackage(pack);
//            AddOutputBoundary.presentAddSuccessView();
//
//            return true;
//        } catch (Exception e) {
//            AddOutputBoundary.presentAddFailView();
//            return false;
//        }
//    }
//
//    @Override
//    public void deletePack(Pack pack) {
//        currItem.deletePackage(pack);
//    }
}
