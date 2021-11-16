package interface_adapter.gateway;

import entity.User;
import interface_adapter.Controller.ProgramState;
import interface_adapter.gateway.datain.DataInFactory;
import interface_adapter.gateway.datain.Writer;
import interface_adapter.gateway.dataout.Loader;
import use_case.manager.UserManager;

import java.io.IOException;

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
    public void write(ProgramState state, Object o) throws IOException {
        Writer writer = this.factory.getWriter(state, o);
        writer.write();
    }

    @Override
    public void archive(ProgramState state, Object o) throws IOException {
        Writer writer = this.factory.getWriter(state, o);
        writer.archive();
    }

    @Override
    //TODO: Consider return a hashmap <String username, String password>.
    public UserManager initialLoad() throws IOException {
        Loader loader = new Loader();
        return loader.initialLoad();
    }

    @Override
    //TODO: Consider pass in a String username and return
    public void userLoad(User user) throws Exception {
        Loader loader = new Loader();
        loader.userLoad(user);
    }
}
