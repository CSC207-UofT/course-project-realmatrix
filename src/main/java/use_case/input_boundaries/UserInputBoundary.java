package use_case.input_boundaries;

import entity.Pack;
import entity.User;
import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.RegisterOutputBoundary;

import java.io.IOException;

/**
 * An input boundary that connects UserManager and UserController.
 * **UserManager should implement this.**
 */
public interface UserInputBoundary {
    Object createNewUser(String name, String password, RegisterOutputBoundary registerOB) throws IOException;

    void changeName(User user, String newInfo, ChangeOutputBoundary changeOutputBoudary) throws IOException;

    void changePassword(User user, String newInfo) throws IOException;

    void addPack(User user, Pack pack, AddOutputBoundary AddOutputBoundary);

    void deletePack(User user, Pack pack) throws IOException;

//    void searchPack(User user, Pack pack);
//    void sortPack();
}
