package use_case.input_boundaries;

import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.DatabaseErrorOutputBoundary;
import use_case.output_boundaries.RegisterOutputBoundary;

/**
 * An input boundary that connects UserManager and UserController.
 * **UserManager should implement this.**
 */
public interface UserInputBoundary extends ManagerInputBoundary {
    void initialLoad(DatabaseErrorOutputBoundary databaseErrorOutputBoundary);

    boolean createNewUser(String name, String password, RegisterOutputBoundary registerOB);

    boolean changeName(String newName, ChangeOutputBoundary changeOutputBoundary);

    void changePassword(String newInfo);
}
