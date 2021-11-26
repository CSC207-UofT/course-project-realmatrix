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
        this.packageList.add(pack);
    }

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
