package use_case.input_boundaries;

import entity.Pack;
import entity.User;
import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.RegisterOutputBoundary;

/**
 * An input boundary that connects UserManager and UserController.
 * **UserManager should implement this.**
 */
public interface UserInputBoundary {
    Object createNewUser(String name, String password, RegisterOutputBoundary registerOB);

    void changeName(User user, String newInfo, ChangeOutputBoundary changeOutputBoudary);

    void changePassword(User user, String newInfo);

    void addPack(User user, Pack pack, AddOutputBoundary AddOutputBoundary);

    void deletePack(User user, Pack pack);

//    void searchPack(User user, Pack pack);
//    void sortPack();
}
