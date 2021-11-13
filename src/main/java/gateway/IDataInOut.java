package gateway;

import Controller.ProgramState;
import entity.User;
import manager.UserManager;

import java.io.IOException;

/**
 * The interface that implements dependency inversion:
 * allows usecase classes to access database.
 */
public interface IDataInOut {
    public void write(ProgramState state, Object o) throws IOException;

    public void archive(ProgramState state, Object o) throws IOException;

    //TODO: Consider return a hashmap <String username, String password>.
    public UserManager initialLoad() throws IOException;

    //TODO: Consider pass in a String username and return
    public void userLoad(User user) throws IOException;

}
