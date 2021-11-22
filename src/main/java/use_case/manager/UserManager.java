package use_case.manager;

import entity.Pack;
import entity.User;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.input_boundaries.UserInputBoundary;
import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.RegisterOutputBoundary;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Objects;

/**
 * A manager that manages the current user's registration process and other actions.
 * It is an observable class, which can induce changes to ProgramState.
 */
//TODO: implement <sort> interface
public class UserManager extends Manager<User> implements UserInputBoundary {
    private final PropertyChangeSupport observable;

    public UserManager() {
        super();
        this.observable = new PropertyChangeSupport(this);
    }

    /**
     * Add ProgramStateManager as an observer to respond to the changes
     * (creating new users, adding new packs) to this class.
     */
    private void addObserver() {
        PropertyChangeListener observer = new ProgramStateManager();
        observable.addPropertyChangeListener("user", observer);
        observable.addPropertyChangeListener("pack", observer);
        // TODO: make propertyName constant
    }

    /**
     * Create a new user if the user doesn't exist in database.
     *
     * @param name       the username of this user
     * @param password   the password of this user
     * @param registerOB the output boundary (abstract interface for presenter)
     */
    @Override
    public Object createNewUser(String name, String password, RegisterOutputBoundary registerOB) {
        if (uniqueUsername(name)) {
            User user = new User(name, password);
            this.getItems().add(user);
            registerOB.setRegisterResult(true);

            addObserver();
            observable.firePropertyChange("user", null, user); //TODO: constant
            return user;
        } else {
            registerOB.setRegisterResult(false);
            return null;
        }
    }

    /**
     * Private helper function to createNewUser. Check if a username is unique.
     *
     * @param username user's choice of username
     * @return true if it is unique; false otherwise
     */
    private boolean uniqueUsername(String username) {
        for (User user : this.getItems()) {
            if (Objects.equals(user.getName(), username)) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method is only used in Loader, because user registered before and doesn't need a new id.
     *
     * @param user User
     */
    public void putUser(User user) {
        this.getItems().add(user);
    }

    /**
     * Change name of current user.
     *
     * @param user    the user that needs to change name
     * @param newName new name
     * @return true if user successfully changed name; false otherwise
     */
    @Override
    public boolean changeName(User user, String newName, ChangeOutputBoundary changeOutputBoudary) {
        if (uniqueUsername(newName)) {
            user.changeName(newName);
            changeOutputBoudary.setChangeResult(true);
            return true;
        } else {
            changeOutputBoudary.setChangeResult(false);
            return false;
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
    }

    /**
     * Add pack into the user's arraylist of packs.
     *
     * @param pack the pack to be added
     * @return true if successfully added; false otherwise
     */
    @Override
    public boolean addPack(Pack pack, AddOutputBoundary AddOutputBoundary, ProgramStateInputBoundary programStateInputBoundary) {
        try {
            programStateInputBoundary.getCurrUser().addPackage(pack);
            AddOutputBoundary.presentAddSuccessView();

            addObserver();
            observable.firePropertyChange("pack", null, pack); // TODO: constant
            return true;
        } catch (Exception e) {
            AddOutputBoundary.presentAddFailView();
            return false;
        }
    }

    @Override
    public void deletePack(User user, Pack pack) {
        user.deletePackage(pack);
    }
}
