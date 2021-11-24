package interface_adapter.gateway;

import entity.User;

import java.io.IOException;
import java.util.HashMap;

/**
 * The interface that implements dependency inversion:
 * allows usecase classes to access database.
 */
public interface IDataInOut {
    void write(String[] partialDataPath, Object o) throws IOException;

    void write(String[] partialDataPath, String oldName, Object newO) throws IOException;

    void archive(String[] partialDataPath, Object o) throws IOException;

    HashMap<String, String> initialLoad() throws IOException;

    void userLoad(User user) throws IOException;

}
