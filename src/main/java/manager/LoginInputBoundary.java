package manager;

import entity.User;
import presenters.LoginPresenter;

public interface LoginInputBoundary {

    void logInUser(String username, String password, LoginPresenter lp) throws Exception;
    User getCurrUser() throws Exception;
}
