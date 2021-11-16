package interface_adapter.gateway;

import entity.User;
import interface_adapter.Controller.ProgramState;

import java.io.IOException;
import java.util.HashMap;

/**
 * The interface that implements dependency inversion:
 * allows usecase classes to access database.
 */
public interface IDataInOut {
    void write(ProgramState state, Object o) throws IOException;

    void archive(ProgramState state, Object o) throws IOException;

    HashMap<String, String> initialLoad() throws IOException;

    void userLoad(User user) throws Exception;

}
