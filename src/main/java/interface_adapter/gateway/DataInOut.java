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

    @Override
    public void write(String[] partialDataPath, Object o) throws IOException {
        Writer writer = this.factory.getWriter(partialDataPath, o);
        writer.write();
    }

    @Override
    public void archive(String[] partialDataPath, Object o) throws IOException {
        Writer writer = this.factory.getWriter(partialDataPath, o);
        writer.archive();
    }

    @Override
    public HashMap<String, String> initialLoad() throws IOException {
        Loader loader = new Loader();
        return loader.initialLoad();
    }

    @Override
    public void userLoad(User user) throws Exception {
        Loader loader = new Loader();
        loader.userLoad(user);
    }
}
