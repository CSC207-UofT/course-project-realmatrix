package use_case.input_boundaries;

import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.RegisterOutputBoundary;

import java.io.IOException;

/**
 * An input boundary that connects UserManager and UserController.
 * **UserManager should implement this.**
 */
public interface UserInputBoundary extends ManagerInputBoundary {
    boolean createNewUser(String name, String password, RegisterOutputBoundary registerOB) throws IOException;

    boolean changeName(String newName, ChangeOutputBoundary changeOutputBoundary);

    void changePassword(String newInfo);

    // PackManager takes responsibility of the next two methods
//    boolean addPack(Pack pack, AddOutputBoundary AddOutputBoundary);

//    void deletePack(Pack pack);

//    void searchPack(User user, Pack pack);
//    void sortPack();
}
