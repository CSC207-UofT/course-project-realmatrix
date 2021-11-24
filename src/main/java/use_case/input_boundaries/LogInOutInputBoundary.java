package use_case.input_boundaries;

import use_case.output_boundaries.LogInOutOutputBoundary;

/**
 * An input boundary that connects LogInOutManager and LogInOutController.
 * **LogInOutManager should implement this.**
 */
public interface LogInOutInputBoundary {

    void logInUser(String username, String password, LogInOutOutputBoundary logInOutOB) throws Exception;

    void signOffUser(LogInOutOutputBoundary logInOutOB);
}
