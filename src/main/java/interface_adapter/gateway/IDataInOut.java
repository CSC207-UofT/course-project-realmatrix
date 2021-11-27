package interface_adapter.gateway;

import entity.User;

import java.io.IOException;
import java.util.HashMap;

/**
 * The interface that implements dependency inversion:
 * allows usecase classes to access database.
 */
public interface IDataInOut {

    // Throw exception if and only if Object o is not Card/Pack/User.
    void write(String[] partialDataPath, Object o) throws Exception;

    // Throw exception if and only if newO is not Card/Pack/User.
    void write(String[] partialDataPath, String oldName, Object newO) throws Exception;

    // Throw exception if Object o is not Card/Pack/User or if the archive rout exits.
    void archive(String[] partialDataPath, Object o) throws Exception;

    HashMap<String, String> initialLoad() throws IOException;

    void userLoad(User user) throws IOException;

}
