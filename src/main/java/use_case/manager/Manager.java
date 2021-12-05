package use_case.manager;

import interface_adapter.gateway.IDataInOut;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.output_boundaries.DatabaseErrorOutputBoundary;

import java.io.IOException;
import java.util.HashMap;

/**
 * An abstract manager.
 */
public abstract class Manager<T> {
    // Maps entity's name to entity, e.g. items in UserManager maps cardTerm to Card.
    protected HashMap<String,T> items;
    protected final ProgramStateInputBoundary programStateInputBoundary;
    protected T currItem;

    public Manager(ProgramStateInputBoundary programStateInputBoundary) {
        this.items = new HashMap<>();
        this.programStateInputBoundary = programStateInputBoundary;
    }

    /**
     * search item entity by item name
     * @param name item name we want
     * @return the item entity
     */
    public T searchItem(String name){
        return this.items.get(name);
    }

    /**
     * Write the required object into database.
     */
    public void write(IDataInOut dataInOut, DatabaseErrorOutputBoundary databaseErrorOutputBoundary) {
        try {
            dataInOut.write(findPartialDataPath(), currItem);
        } catch (IOException e) {
            databaseErrorOutputBoundary.presentWriteErrMsg();
        }
    }

    /**
     * Write the object (with its name changed) into database.
     */
    public void write(String oldName, IDataInOut dataInOut, DatabaseErrorOutputBoundary databaseErrorOutputBoundary) {
        try {
            dataInOut.write(findPartialDataPath(), oldName, currItem);
        } catch (IOException e) {
            databaseErrorOutputBoundary.presentWriteErrMsg();
        }
    }

    /**
     * Archive (delete and store) the required object into database.
     * @param databaseErrorOutputBoundary an output boundary that gets the error message if fails connect to database.
     */
    public void delete(IDataInOut dataInOut, DatabaseErrorOutputBoundary databaseErrorOutputBoundary) {
        try {
            dataInOut.delete(findPartialDataPath(), currItem);
        } catch (IOException e) {
            databaseErrorOutputBoundary.presentWriteErrMsg();
        }
    }

    /**
     * Helper method for write/archive.
     * Help determine the essential part of data-path for writing data.
     */
    private String[] findPartialDataPath() {
        String userPath = this.programStateInputBoundary.getCurrUser().getName();
        String packPath = null;
        if (this.programStateInputBoundary.getCurrPack() != null) {
            packPath = this.programStateInputBoundary.getCurrPack().getName();
        }

        return new String[]{userPath, packPath};
    }
}
