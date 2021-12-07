package interface_adapter.gateway;

import entity.User;

import java.io.IOException;
import java.util.HashMap;

/**
 * The interface that implements dependency inversion:
 * allows usecase classes to access database.
 */
public interface IDataInOut {
    /**
     * Write a new object into database based on partialDataPath.
     * @param partialDataPath an array containing current user's username and current pack's pack name
     * @param o the object to be written into database.
     * @throws IOException fail to write
     */
    void write(String[] partialDataPath, Object o) throws IOException;

    /**
     * Rename an existing object in database based on partialDataPath.
     * @param partialDataPath an array containing current user's username and current pack's pack name
     * @param oldName the old name of the object
     * @param newO the new object (this object helps determine which writer class would be instantiated)
     * @throws IOException fail to write
     */
    void write(String[] partialDataPath, String oldName, Object newO) throws IOException;

    /**
     * Delete an existing object in database.
     * @param partialDataPath an array containing current user's username and current pack's pack name
     * @param o the object for deletion
     * @throws IOException fail to write the change into database.
     */
    void delete(String[] partialDataPath, Object o) throws IOException;

    /**
     * Load all username and corresponding user password in the database into a hashmap
     * @return a hashmap of <username: user password>
     * @throws IOException fail to load
     */
    HashMap<String, String> initialLoad() throws IOException;

    /**
     * Load all packs and cards for this current user.
     * @param user the current user
     * @throws IOException fail to load
     */
    void userLoad(User user) throws IOException;

}
