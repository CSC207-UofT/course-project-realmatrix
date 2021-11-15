package use_case.manager;

import entity.Pack;
import entity.User;
import input_boundaries.UserInputBoundary;
import output_boundaries.AddOutputBoundary;
import output_boundaries.ChangeOutputBoundary;
import output_boundaries.RegisterOutputBoundary;

import java.util.Objects;

/**
 * A manager that manages all Users.
 */
//TODO: implement <sort> interface
public class UserManager extends Manager<User> implements UserInputBoundary {

    public UserManager() {
        super();
    }

    /**
     * Create a new user if the user doesn't exist in database.
     *
     * @param name       the username of this user
     * @param password   the password of this user
     * @param registerOB the output boundary (abstract interface for presenter)
     */
    @Override
    public void createNewUser(String name, String password, RegisterOutputBoundary registerOB) {
        if (uniqueUsername(name)) {
            String id = super.generateId();
            User user = new User(id, name, password);
            this.idToItem.put(id, user);
            registerOB.setRegisterResult(true);
        } else {
            registerOB.setRegisterResult(false);
        }
    }

    /**
     * Private helper function to createNewUser. Check if a username is unique.
     *
     * @param username user's choice of username
     * @return true if it is unique; false otherwise
     */
    private boolean uniqueUsername(String username) {
        for (User user : this.getItems().values()) {
            if (Objects.equals(user.getName(), username)) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method is only used in Loader, because user registered before and doesn't need a new id.
     *
     * @param id   id of user
     * @param user User
     */
    public void putUser(String id, User user) {
        this.idToItem.put(id, user);
    }

    /**
     * Change name of current user.
     *
     * @param user    the user that needs to change name
     * @param newName new name
     */
    @Override
    public void changeName(User user, String newName, ChangeOutputBoundary changeOutputBoudary) {
        if (uniqueUsername(newName)) {
            user.changeName(newName);
            changeOutputBoudary.setChangeResult(true);
            //TODO: save the change into database
        } else {
            changeOutputBoudary.setChangeResult(false);
        }
    }

    /**
     * Change password of current user.
     *
     * @param user        the user that needs to change password
     * @param newPassword new password
     */
    @Override
    public void changePassword(User user, String newPassword) {
        user.changePassword(newPassword);
        // TODO: save the change into database
    }

    /**
     * Add pack into the user's arraylist of packs.
     *
     * @param user the user that needs to add pack
     * @param pack the pack to be added
     */
    @Override
    public void addPack(User user, Pack pack, AddOutputBoundary AddOutputBoundary) {
        try {
            user.addPackage(pack);
            AddOutputBoundary.presentAddSuccessView();
            // TODO: save to database
        } catch (Exception e) {
            AddOutputBoundary.presentAddFailView();
        }
    }

    @Override
    public void deletePack(User user, Pack pack) {
        user.deletePackage(pack);
        //TODO: archive in database
    }

//    /**
//     * For testing purposes only.
//     * @param args
//     */
//    public static void main(String[] args) throws Exception {
//        UserManager um = new UserManager();
//        um.createNewUser("Xing", "password");
//        um.createNewUser("SuperDog", "super");
//        um.createNewUser("FunkyCat", "funky");
//        for (User user : um.idToItem.values()) {
//            System.out.println(user.getId());
//        }
//    }
}
