package Controller;

import entity.User;
import manager.LoginManager;
import manager.UserManager;

public class LoginController {
    public LoginManager lm;
    public UserManager um;

    /**
     * Controller for login
     */
    public LoginController() {
        this.um = new UserManager();
        this.lm = new LoginManager(this.um);
    }

    /**
     * login method for user login
     *
     * @param username user's username, cannot be repeated
     * @param password user's password
     * @return "Login... Success!" when user login successed.
     */
    public String login(String username, String password) {
        try {
            lm.logInUser(username, password);
            return "User" + username + "Login... Success!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * sign off method for user
     *
     * @return "User signed off" when user signed off successfully
     */
    public String signOff() {
        try {
            lm.SignOffUser();
            return "User signed off";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Need to discuss whether this is useful.
     *
     * @return the current user.
     */
    public User getCurrentUser() throws Exception {
        return this.lm.getCurrUser();
    }
}
