package manager;

import java.util.HashMap;
import java.util.Random;

/**
 * An abstract manager.
 */
public abstract class Manager<T> {
    protected HashMap<String, T> idToItem;

    public Manager() {
        this.idToItem = new HashMap<>();
    }

    /**
     * Return a unique identifier of 10 characters long
     * @return an id
     */
    protected String generateId() {
        String chars = "abcdefghijklmnopqrstuvwxyz1234567890-_=+?!@%$#&*";
        StringBuilder id = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            char c = chars.charAt(rand.nextInt(48));
            id.append(c);
        }
        if (this.idToItem.containsKey(id.toString())) {
            return this.generateId();
        } else {
            return id.toString();
        }
    }

    /**
     * Important!! This method is for testing purposes only! Don't use it in the code. Remove when testing is done.
     * @return
     */
    public HashMap<String, T> items() {
        return this.idToItem;
    }
}
