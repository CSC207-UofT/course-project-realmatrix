package gateway;

import Controller.ProgramState;
import entity.User;
import gateway.datain.DataInFactory;
import gateway.datain.Writer;
import gateway.dataout.Loader;
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
