package interface_adapter.gateway;

import entity.User;
import interface_adapter.Controller.ProgramState;
import use_case.manager.UserManager;

import java.io.IOException;

/**
 * The interface that implements dependency inversion:
 * allows usecase classes to access database.
 */
public interface IDataInOut {
    void write(ProgramState state, Object o) throws IOException;

    void archive(ProgramState state, Object o) throws IOException;

    //TODO: Consider return a hashmap <String username, String password>.
    UserManager initialLoad() throws IOException;

    //TODO: Consider pass in a String username and return
    void userLoad(User user) throws Exception;

}
