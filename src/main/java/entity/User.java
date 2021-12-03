package entity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A single user.
 */
public class User {
    private String name;
    private String password;
    private final ArrayList<Pack> packageList;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.packageList = new ArrayList<>();
    }

    // A bunch of getters.
    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    /**
     * Change the user's username to newName.
     * @param newName the user's new username.
     */
    public void changeName(String newName) {
        this.name = newName;
    }

    /**
     * Change the user's password to newPassword.
     * @param newPassword the user's new password.
     */
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    /**
     * Add the pack into user's packageList.
     * @param pack the pack to be added into user's packageList.
     */
    public void addPackage(Pack pack) {
        this.packageList.add(pack);
    }

    /**
     * Getter for PackageList.
     * @return a PackageList.
     */
    public ArrayList<Pack> getPackageList() {
        return this.packageList;
    }

    /**
     * Return a pack map that maps pack name to pack, allows more flexible use in other classes.
     * @return a hah map of pack name to pack object.
     */
    public HashMap<String, Pack> getPackageMap() {
        HashMap<String, Pack> nameToPack = new HashMap<>();
        for (Pack p : this.packageList) {
            nameToPack.put(p.getName(), p);
        }
        return nameToPack;
    }

    /**
     * @param pack the package to be deleted
     */
    public void deletePackage(Pack pack) {
        this.packageList.remove(pack);
    }
}
