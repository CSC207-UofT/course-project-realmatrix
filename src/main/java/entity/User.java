package entity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A single user.
 */
public class User {
    private String name;
    private String password;
    private final HashMap<String, Pack> packages;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.packages = new HashMap<>();
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public void changeName(String newName) {
        this.name = newName;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public void addPackage(Pack pack) {
        this.packages.put(pack.getName(), pack);
    }

    public HashMap<String, Pack> getPackages() {
        return this.packages;
    }

    /**
     * @param pack the package to be deleted
     */
    public void deletePackage(Pack pack) {
        this.packages.remove(pack.getName());
    }
}
