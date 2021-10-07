package manager;

import entity.User;

/**
 * A manager that manages all Users.
 */
public class UserManager extends Manager<User>{

    public UserManager() {
        super();
    }

    public void createNewUser(String name) {
        User user = new User(super.generateId(), name);
        this.items.add(user);
    }

    /**
     * For testing purposes only.
     * @param args
     */
    public static void main(String[] args) {
        // TODO: test this when Pack is implemented
        UserManager um = new UserManager();
        um.createNewUser("Xing");
        System.out.println(um.items);
        um.createNewUser("SuperDog");
        um.createNewUser("FunkyCat");
        for (User user : um.items) {
            System.out.println(user.getId());
        }
    }
}
