package input_boundaries;

import entity.*;
import output_boundaries.AddOutputBoundary;
import output_boundaries.ChangeOutputBoundary;
import output_boundaries.RegisterOutputBoundary;

/**
 * An input boundary that connects UserManager and UserController.
 * **UserManager should implement this.**
 */
public interface UserInputBoundary {
    void createNewUser(String name, String password, RegisterOutputBoundary registerOB);
    void changeName(User user, String newInfo, ChangeOutputBoundary changeOutputBoudary);
    void changePassword(User user, String newInfo);
    void addPack(User user, Pack pack, AddOutputBoundary AddOutputBoundary);
    void deletePack(User user, Pack pack);
    
//    void searchPack(User user, Pack pack);
//    void sortPack();
}
