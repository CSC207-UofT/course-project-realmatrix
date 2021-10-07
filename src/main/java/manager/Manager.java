package manager;

import entity.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * An abstract manager.
 */
public abstract class Manager<T> {
    protected ArrayList<T> items;
    private static HashSet<String> ids;

    public Manager() {
        this.items = new ArrayList<>();
        Manager.ids = new HashSet<>();
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
        if (Manager.ids.contains(id.toString())) {
            return this.generateId();
        } else {
            return id.toString();
        }
    }
}
