package database.dataout;

import entity.User;
import manager.UserManager;

import java.io.IOException;

public interface IDataLoader {
    // TODO: Return UserManager from database is not clean. Need to change, e.g. return a hashmap of user and password.
    public UserManager initialLoad() throws IOException;

    // Load all packs for this user
    public void userLoad(User user) throws IOException;
}
