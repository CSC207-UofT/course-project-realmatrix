package interface_adapter.gateway.dataout;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is a helper to Loader. Other classes shouldn't need it.
 */
class Reader {

    /**
     * Return a complete list of file paths, in strings, leading to up till usernames.
     *
     * @return A list of file paths in strings
     */
    ArrayList<String> readUsers() {
        ArrayList<String> users = new ArrayList<>();
        File dir = new File("user_data/users/");
        File[] files = dir.listFiles();
        if (files == null) {
            return users; // Return an empty list if there is no user directory.
        }
        for (File file : files) {
            users.add(file.toString());
        }
        return users;
    }

    /**
     * Return a complete list of file paths, in strings, leading to up till package names, of a specified user.
     *
     * @param userName The specified user
     * @return A list of file paths in strings
     */
    ArrayList<String> readPacks(String userName) {
        ArrayList<String> packs = new ArrayList<>();
        File dir = new File("user_data/users/" + userName + "/packages/");
        File[] files = dir.listFiles();
        if (files == null) {
            return packs; // Return an empty list if there is no package.
        }
        for (File file : files) {
            packs.add(file.toString());
        }
        return packs;
    }

    /**
     * Return a complete list of file paths, in strings, leading to up till package names, of a specified user -> pack.
     *
     * @param userName The specified user
     * @param packName The specified package
     * @return A list of file paths in strings
     */
    ArrayList<String> readCards(String userName, String packName) {
        ArrayList<String> cards = new ArrayList<>();
        File dir = new File("user_data/users/" + userName + "/packages/" + packName + "/cards/");
        File[] files = dir.listFiles();
        if (files == null) {
            return cards; // Return an empty list if there is no card.
        }
        for (File file : files) {
            cards.add(file.toString());
        }
        return cards;
    }

    // Testing purpose
    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        reader.readUsers();
        Loader loader = new Loader();
        System.out.println(loader.initialLoad());
    }
}
