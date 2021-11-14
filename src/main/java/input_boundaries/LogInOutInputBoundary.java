package input_boundaries;

import entity.User;
import output_boundaries.LogInOutOutputBoundary;

/**
 * An input boundary that connects LogInOutManager and LogInOutController.
 * **LogInOutManager should implement this.**
 */
public interface LogInOutInputBoundary {

    void logInUser(String username, String password, LogInOutOutputBoundary logInOutOB);
    User getCurrUser() throws Exception;
    void signOffUser(LogInOutOutputBoundary logInOutOB);
}
