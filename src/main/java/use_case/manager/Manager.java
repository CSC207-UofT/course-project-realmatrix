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
    // Maps entity's name to entity, e.g. items in UserManager maps username to User.
    protected IDataInOut dataInOut;
    protected HashMap<String,T> items;
    protected ProgramStateInputBoundary programStateInputBoundary;
    protected T currItem;

    public Manager(IDataInOut dataInOut, ProgramStateInputBoundary programStateInputBoundary) {
        this.items = new HashMap<>();
        this.dataInOut = dataInOut;
        this.programStateInputBoundary = programStateInputBoundary;
    }

    public HashMap<String,T> getItems() {
        return this.items;
    }

    public void setItems(HashMap<String,T> items) {
        this.items = items;
    }

//    /**
//     * to add a new item, we call this method without pass existing name in items.
//     * @param name: unique name
//     *
//     * @return true iff we successfully add a new item
//     */
//    public abstract boolean addItem(String name);

//    /**
//     * change an existing item in items
//     * @param name item name we want to change
//     * @param newItem new item
//     * @return true iff we successfully changed to new item
//     */
//    public boolean updateItem(String name, T newItem){
//        if(this.items.containsKey(name)){
//            this.items.put(name,newItem);
//            return true;
//        }else{
//            return false;
//        }
//    }

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
    public void write(DatabaseErrorOutputBoundary databaseErrorOutputBoundary) {
        try {
            dataInOut.write(findPartialDataPath(), currItem);
        } catch (IOException e) {
            databaseErrorOutputBoundary.presentWriteErrMsg();
        }
    }

    public void write(String oldName, DatabaseErrorOutputBoundary databaseErrorOutputBoundary) {
        try {
            dataInOut.write(findPartialDataPath(), oldName, currItem);
        } catch (IOException e) {
            databaseErrorOutputBoundary.presentWriteErrMsg();
        }
    }

    /**
     * Archive (delete and store) the required object into database.
     */
    public void archive(DatabaseErrorOutputBoundary databaseErrorOutputBoundary) {
        try {
            dataInOut.archive(findPartialDataPath(), currItem);
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

    public void setCurrItem(T item) {
        this.currItem = item;
    }
}
