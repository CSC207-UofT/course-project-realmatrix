package use_case.manager;

import java.util.HashMap;
import java.util.HashSet;

/**
 * An abstract manager.
 */
public abstract class Manager<T> {
    private final HashMap<String,T> items;

    public Manager() {
        this.items = new HashMap<>();
    }

    public HashMap<String,T> getItems() {
        return this.items;
    }

    /**
     * to add a new item, we call this method without pass existing name in items.
     * @param name: unique name
     * @param item: new item
     *
     * @return true iff we successfully add a new item
     */
    public boolean addItem(String name, T item){
        if(!this.items.containsKey(name)){
            this.items.put(name, item);
            return true;
        }else{
            return false;
        }
    }

    /**
     * delete a existing item
     * @param name: item name we want to delete
     * @return true iff we successfully deleted an item
     */
    public boolean deleteItem(String name){
        if(this.items.containsKey(name)){
            this.items.remove(name);
            return true;
        }else{
            return false;
        }
    }

    /**
     * change an existing item in items
     * @param name item name we want to change
     * @param newItem new item
     * @return true iff we successfully changed to new item
     */
    public boolean updateItem(String name, T newItem){
        if(this.items.containsKey(name)){
            this.items.put(name,newItem);
            return true;
        }else{
            return false;
        }
    }

    /**
     * search item entity by item name
     * @param name item name we want
     * @return the item entity
     */
    public T searchItem(String name){
        return this.items.get(name);
    }
}
