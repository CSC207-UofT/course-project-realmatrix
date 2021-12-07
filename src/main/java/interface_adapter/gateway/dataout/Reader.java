package interface_adapter.gateway.dataout;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

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
        return getPaths(packs, dir);
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
        return getPaths(cards, dir);
    }

    /**
     * Helper for reader.
     *
     * @param lst an arraylist to put card/pack file
     * @param dir the parent file path
     * @return an arraylist that has put card/pack files
     */
    private ArrayList<String> getPaths(ArrayList<String> lst, File dir) {
        File[] files = dir.listFiles();
        if (files == null) {
            return lst; // Return an empty list if there is no card.
        }
        Arrays.sort(files, Comparator.comparingLong(File::lastModified));
        for (File file : files) {
            lst.add(file.toString());
        }
        return lst;
    }


}
