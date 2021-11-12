package Controller;

import manager.LoginManager;
import manager.UserManager;

public class RegisterController {
    UserManager um;
    LoginManager lm;

    /**
     * A register Controller that allow user to register
     */
    public RegisterController(){
        this.um = new UserManager();
        this.lm = new LoginManager(um);
    }

    /**
     * User register method
     * @param username user's username
     * @param password user's password
     * @return User's username and tell he/she is already registered and login
     */
    public String register(String username, String password){
        boolean flag;
        String s = "";
        try {
            um.createNewUser(username,password);
            flag = true;
        } catch (Exception e) {
            s= e.getMessage();
            flag = false;
        }
        try {
            lm.logInUser(username, password);
        } catch (Exception e) {
            s += "\n" + e.getMessage();
            flag = false;
        }
        if(flag) {
            return "User" + username + "registered and already login";
        }else{
            return s;
        }
    }
}
