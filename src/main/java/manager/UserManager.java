package manager;

import entity.User;

import java.util.Objects;

/**
 * A manager that manages all Users.
 */
public class UserManager extends Manager<User>{

    public UserManager() {
        super();
    }

    public void createNewUser(String name, String password) throws Exception {
        if (uniqueName(name)) {
            String id = super.generateId();
            User user = new User(id, name, password);
            this.idToItem.put(id, user);
        } else {
            throw new Exception("This username is taken. Please choose another one.");
        }
    }

    /**
     * Private helper function to createNewUser. Check if a username is unique.
     * @param name user's choice of username
     * @return true if it is unique; false otherwise
     */
    private boolean uniqueName(String name) {
        for (User user : this.getItems().values()) {
            if (Objects.equals(user.getName(), name)) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method is only used in Loader, because user registered before and doesn't need a new id.
     * @param id id of user
     * @param user User
     */
    public void putUser(String id, User user) {
        this.idToItem.put(id, user);
    }

    /**
     * Change name/password of current user.
     * @param func 'N' for name or 'P' for password
     * @param newInfo new name or new password
     */
    public void changeInfo(User user, char func, String newInfo) {
        if (func == 'N') {
            user.changeName(newInfo);
        } else if (func == 'P') {
            user.changePassword(newInfo);
        }
    }

    /**
     * For testing purposes only.
     * @param args
     */
    public static void main(String[] args) throws Exception {
        UserManager um = new UserManager();
        um.createNewUser("Xing", "password");
        um.createNewUser("SuperDog", "super");
        um.createNewUser("FunkyCat", "funky");
        for (User user : um.idToItem.values()) {
            System.out.println(user.getId());
        }
    }
}
