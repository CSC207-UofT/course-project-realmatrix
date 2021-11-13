package manager;

import entity.User;
import input_boundaries.LoginInputBoundary;
import presenters.LoginPresenter;

import java.util.Objects;

public class LoginManager implements LoginInputBoundary {
    private boolean loggedIn; // by default, no user logged in
    private Object currUser; // by default, no current user who is logged in
    private UserManager manager;

    public LoginManager(UserManager manager) {
        this.loggedIn = false;
        this.currUser = null;
        this.manager = manager;
    }

    public User getCurrUser() throws Exception {
        System.out.println(this.currUser);
        if (this.currUser instanceof User) {
            return (User) this.currUser;
        } else {
            throw new Exception("There's no logged-in user.");
        }
    }

    /**
     * Return the User with given name and password
     *
     * @param name     name of a User
     * @param password password of a User (matches the name)
     * @return User or if not found (which can't be the case; included for completeness reason) return null
     */
    private Object findUser(String name, String password) {
        for (User user : this.manager.getItems().values()) {
            if (Objects.equals(user.getName(), name) && Objects.equals(user.getPassword(), password)) {
                return user;
            }
        }
        return null;
    }


    public void logInUser(String name, String password, LoginPresenter lp) throws Exception {
        if (this.loggedIn) {
            throw new Exception("Please sign off before logging in.");
        } else if (this.findUser(name, password) == null) {
            throw new Exception("Invalid name or password. If you are new, please create an account first.");
        }
        this.loggedIn = true;
        this.currUser = this.findUser(name, password);
    }

    public void SignOffUser() throws Exception {
        if (!this.loggedIn) {
            throw new Exception("Already signed off.");
        }
        this.loggedIn = false;
        this.currUser = null;
    }
}
