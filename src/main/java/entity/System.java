package entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * A system that creates entities such as Users, Packages.
 */
public class System {
    private ArrayList<User> users;
    private ArrayList<Pack> packages;
    private HashSet<String> ids;

    public System() {
        this.users = new ArrayList<User>();
        this.packages = new ArrayList<Pack>();
        this.ids = new HashSet<String>();
    }

    /**
     * Return a unique identifier of 10 characters long
     * @return an id
     */
    private String generateId() {
        String chars = "abcdefghijklmnopqrstuvwxyz1234567890-_=+?!@%$#&*";
        StringBuilder id = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            char c = chars.charAt(rand.nextInt(48));
            id.append(c);
        }
        if (this.ids.contains(id.toString())) {
            return this.generateId();
        } else {
            return id.toString();
        }
    }

    /**
     * Add a new user to the list. Generate a random unique String id of length 10
     * @param name username provided by user
     */
    public void createNewUser(String name) {
        String id = this.generateId();
        User user = new User(id, name);
        this.users.add(user);
    }

    // TODO: add a createNewPack method
}
