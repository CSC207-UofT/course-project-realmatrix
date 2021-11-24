package use_case.input_boundaries;

import use_case.output_boundaries.DatabaseErrorOutputBoundary;
import use_case.output_boundaries.LogInOutOutputBoundary;

/**
 * An input boundary that connects LogInOutManager and LogInOutController.
 * **LogInOutManager should implement this.**
 */
public interface LogInOutInputBoundary {
    void initialLoad(DatabaseErrorOutputBoundary databaseErrorOutputBoundary);
    boolean logInUser(String username, String password, LogInOutOutputBoundary logInOutOB);
    void userLoad(DatabaseErrorOutputBoundary databaseErrorOutputBoundary);
    void signOffUser(LogInOutOutputBoundary logInOutOB);
}
