package entity;

import java.util.ArrayList;

/**
 * A single user.
 */
public class User {
    private final String id;
    private String name;
    private String password;
    // TODO: Name the package class Pack
    private ArrayList<Pack> packages;

    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.packages = new ArrayList<Pack>();
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void changeName(String newName) {
        this.name = newName;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public void createPackage(Pack pack) {
        this.packages.add(pack);
    }

    /**
     *
     * @param pack the package to be deleted
     * @return return true if package is successfully deleted, false if package is not in the list
     */
    public boolean deletePackage(Pack pack) {
        for (int i = 0; i < this.packages.size(); i++) {
            // TODO: implement a getId method for Pack
            if (this.packages.get(i).getId().equals(pack.getId())) {
                this.packages.remove(i);
                return true;
            }
        }
        return false;
    }
}
