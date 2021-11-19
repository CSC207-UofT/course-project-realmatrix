package use_case.manager;

import java.util.HashSet;

/**
 * An abstract manager.
 */
public abstract class Manager<T> {
    private final HashSet<T> items;

    public Manager() {
        this.items = new HashSet<>();
    }

    public HashSet<T> getItems() {
        return this.items;
    }
}
