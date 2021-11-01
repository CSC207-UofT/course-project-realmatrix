package database;

import entity.Card;
import entity.Pack;
import entity.User;
import manager.UserManager;

import java.io.IOException;

/**
 * A simple factory that hides the creation of writers and loaders from use cases.
 * Other classes should only interact with this factory and not any of the writers and loaders.
 */
public class DataInOutFactory {
    private final UserWriter userWriter = new UserWriter();
    private final PackWriter packWriter = new PackWriter();
    private final CardWriter cardWriter = new CardWriter();
    private final Loader loader = new Loader();

    public void write(User user) throws IOException {
        userWriter.writeUser(user);
    }

    public void write(User user, Pack pack) throws IOException {
        packWriter.writePack(user, pack);
    }

    public void write(User user, Pack pack, Card card) throws IOException {
        cardWriter.writeCard(user, pack, card);
    }

    public void archive(User user) throws IOException {
        userWriter.archiveUser(user);
    }

    public void archive(User user, Pack pack) throws IOException {
        packWriter.archivePack(user, pack);
    }

    public void archive(User user, Pack pack, Card card) throws IOException {
        cardWriter.archiveCard(user, pack, card);
    }

    public UserManager load() throws IOException {
        return loader.load();
    }
}
