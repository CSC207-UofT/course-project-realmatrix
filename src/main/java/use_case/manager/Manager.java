package use_case.manager;

import interface_adapter.Controller.ProgramState;
import interface_adapter.gateway.DataInOut;
import interface_adapter.gateway.IDataInOut;

import java.util.HashMap;
import java.util.HashSet;

/**
 * An abstract manager.
 */
public abstract class Manager<T> {
    private final HashSet<T> idToItem;
    private ProgramState state;
    protected final IDataInOut writer = new DataInOut();

    public Manager(ProgramState state) {
        this.idToItem = new HashSet<>();
        this.state = state;
    }

    public HashSet<T> getItems() {
        return this.idToItem;
    }

    public ProgramState getState() {
        return this.state;
    }
}
