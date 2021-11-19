package use_case.manager;

import java.util.HashMap;
import java.util.HashSet;

/**
 * An abstract manager.
 */
public abstract class Manager<T> {
    private final HashSet<T> idToItem;

    public Manager() {
        this.idToItem = new HashSet<>();
    }

    public HashSet<T> getItems() {
        return this.idToItem;
    }
}
