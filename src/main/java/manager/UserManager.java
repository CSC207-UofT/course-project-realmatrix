package manager;

import entity.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

/**
 * A manager that manages all Users.
 */
public class UserManager extends Manager<User>{

    public UserManager() {
        super();
    }

    public void createNewUser(String name, String password) {
        String id = super.generateId();
        User user = new User(id, name, password);
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
    public static void main(String[] args) {
        UserManager um = new UserManager();
        um.createNewUser("Xing", "password");
        um.createNewUser("SuperDog", "super");
        um.createNewUser("FunkyCat", "funky");
        for (User user : um.idToItem.values()) {
            System.out.println(user.getId());
        }
    }
}
