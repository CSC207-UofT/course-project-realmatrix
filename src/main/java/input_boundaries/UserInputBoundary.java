package input_boundaries;

import entity.User;

public interface UserInputBoundary {
    void createNewUser(String name, String password) throws Exception ;
    void putUser(String id, User user);
    void changeInfo(User user, char func, String newInfo);
}
