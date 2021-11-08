package database;

import entity.Card;
import entity.Pack;
import entity.User;
import manager.UserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Loader {

    /**
     * An iterative approach to load data from file system.
     * @return Returns a UserManager object.
     */
    public UserManager load() throws IOException {
        UserManager manager = new UserManager();
        Reader reader = new Reader();
        for (String userPath : reader.readUsers()) { // load users
            User user = this.putUser(userPath, manager);
            for (String packPath : reader.readPacks(user.getName())) { // load this user's packages
                Pack pack = this.putPack(packPath, user);
                for (String cardPath : reader.readCards(user.getName(), pack.getName())) { // load this pack's cards
                    this.putCard(cardPath, pack);
                }
            }
        }
        return manager;
    }

    /**
     * Helper to Loader.load(). Load user into user manager.
     * @param userPath directory of user_info.txt
     * @param manager user manager to save the user to
     * @return the loaded user
     * @throws IOException
     */
    private User putUser(String userPath, UserManager manager) throws IOException {
        BufferedReader userInfoFileReader = Files.newBufferedReader(Path.of(userPath + "/user_info.txt"));
        String userInfo = userInfoFileReader.readLine();
        userInfoFileReader.close();
        String userId = userInfo.split(",")[0];
        String userName = userInfo.split(",")[1];
        String userPassword = userInfo.split(",")[2];
        User user = new User(userId, userName, userPassword);
        manager.putUser(userId, user); // put User into UserManager
        return user;
    }

    /**
     * Helper to Loader.load(). Load pack into user.
     * @param packPath directory of package_info.txt
     * @param user user to save package to
     * @return the loaded package
     * @throws IOException
     */
    private Pack putPack(String packPath, User user) throws IOException {
        BufferedReader packInfoFileReader
                = Files.newBufferedReader(Path.of(packPath + "/package_info.txt"));
        String packInfo = packInfoFileReader.readLine();
        packInfoFileReader.close();
        String packId = packInfo.split(",")[0];
        String packName = packInfo.split(",")[1];
        Pack pack = new Pack(packId, packName);
        user.createPackage(pack); // put Pack into User
        return pack;
    }

    /**
     * Helper to Loader.load(). Load card into pack.
     * @param cardPath file path to card_term.txt
     * @param pack pack to save card to
     * @throws IOException
     */
    private void putCard(String cardPath, Pack pack) throws IOException {
        BufferedReader cardInfoFileReader = Files.newBufferedReader(Path.of(cardPath));
        String cardInfo = cardInfoFileReader.readLine();
        cardInfoFileReader.close();
        String cardId = cardInfo.split(",")[0];
        String cardTerm = cardInfo.split(",")[1];
        String cardDefinition = cardInfo.split(",")[2];
        String cardProficiency = cardInfo.split(",")[3];
        Card card = new Card(cardId, cardTerm, cardDefinition);
        card.setProficiency(Integer.parseInt(cardProficiency));
        try {
            pack.addCard(card); // put Card into Pack
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Tests
    public static void main(String... args) throws IOException {
        DataInOutFactory factory = new DataInOutFactory();
        UserManager manager = factory.load();
        System.out.println(manager.getItems().keySet());
        for (User user : manager.getItems().values()) {
            System.out.println(user.getName());
            for (Pack pack : user.getPackages()) {
                System.out.println(pack.getName());
                for (Card card : pack.getCards()) {
                    System.out.println(card.getTerm());
                }
            }
        }
    }
}
