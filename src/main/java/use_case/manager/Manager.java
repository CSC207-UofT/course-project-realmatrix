package use_case.manager;

import java.util.HashMap;
import java.util.UUID;

/**
 * An abstract manager.
 */
public abstract class Manager<T> {
    protected HashMap<String, T> idToItem;

    public Manager() {
        this.idToItem = new HashMap<>();
    }

    public HashMap<String, T> getItems() {
        return this.idToItem;
    }

    /**
     * Return a unique identifier
     *
     * @return an id
     */
    protected String generateId() {
        return UUID.randomUUID().toString();
    }
}
