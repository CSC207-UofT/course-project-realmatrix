package interface_adapter.gateway;

import entity.User;
import interface_adapter.gateway.datain.DataInFactory;
import interface_adapter.gateway.datain.Writer;
import interface_adapter.gateway.dataout.Loader;

import java.io.IOException;
import java.util.HashMap;

/**
 * Concrete class for IDataInOut, can store and load data from database.
 */
public class DataInOut implements IDataInOut {
    private final DataInFactory factory;    // A factory for creating Writer class

    // Initialize a factory
    public DataInOut() {
        this.factory = new DataInFactory();
    }

    /**
     * Write a new object into database.
     * @param partialDataPath a partial datapath contains username and packname (may be null),
     *                        necessary for determining the path for writing.
     * @param o the object to be written
     * @throws IOException fails to write
     */
    @Override
    public void write(String[] partialDataPath, Object o) throws IOException {
        Writer writer = this.factory.getWriter(partialDataPath, o);
        writer.write();
    }

    /**
     * Write the object's new name into database by renaming the object's directory.
     * Overloading is necessary since name is the unique identifier for entities in this project,
     * if the user changes name of an entity, it's essentially creating a new one in the database.
     * Thus, one needs both old and new name in order to rename.
     *
     * @param partialDataPath a partial datapath contains username and packname (may be null),
     *                        necessary for determining the path for writing.
     * @param oldName the object's old name
     * @param newO the new object to be written into database.
     * @throws IOException
     */
    @Override
    public void write(String[] partialDataPath, String oldName, Object newO) throws IOException {
        Writer writer = this.factory.getWriter(partialDataPath, newO);
        writer.write(oldName, newO);
    }

    @Override
    public void delete(String[] partialDataPath, Object o) throws IOException{
        Writer writer = this.factory.getWriter(partialDataPath, o);
        writer.delete();
    }

    /**
     * Initial load for checking registration/login.
     * @return a hash map that maps username to user password.
     * @throws IOException fails to load
     */
    @Override
    public HashMap<String, String> initialLoad() throws IOException {
        Loader loader = new Loader();
        return loader.initialLoad();
    }

    /**
     * Loads all packs/cards for the current user who logs in.
     * After a user logs in, this method should be called immediately.
     * @param user the current user
     * @throws IOException fails to load
     */
    @Override
    public void userLoad(User user) throws IOException {
        Loader loader = new Loader();
        loader.userLoad(user);
    }
}