package entity;

import java.util.ArrayList;

/**
 * A single user.
 */
public class User {
    private String name;
    private String password;
    private final ArrayList<Pack> packages;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.packages = new ArrayList<>();
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

    public void addPackage(Pack pack) throws Exception {
        for (Pack p : this.packages) {
            if (pack.getName().equals(p.getName())) {
                throw new Exception("Pack already exists."); //TODO: PackExistError
            }
        }
        this.packages.add(pack);
    }

    public ArrayList<Pack> getPackages() {
        return this.packages;
    }

    /**
     * @param pack the package to be deleted
     * @return return true if package is successfully deleted, false if package is not in the list
     */
    public boolean deletePackage(Pack pack) {
        for (int i = 0; i < this.packages.size(); i++) {
            if (this.packages.get(i).getName().equals(pack.getName())) {
                this.packages.remove(i);
                return true;
            }
        }
        return false;
    }
}
