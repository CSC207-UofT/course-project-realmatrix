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

    boolean changeName(User user, String newInfo, ChangeOutputBoundary changeOutputBoudary);

    void changePassword(User user, String newInfo);

    boolean addPack(Pack pack, AddOutputBoundary AddOutputBoundary, ProgramStateInputBoundary programStateInputBoundary);

    void deletePack(User user, Pack pack);

//    void searchPack(User user, Pack pack);
//    void sortPack();
}
