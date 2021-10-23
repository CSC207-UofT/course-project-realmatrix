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
     * An iterative approach. Load data from local data system.
     * @return Returns a UserManager object.
     */
    public UserManager load() throws IOException {
        UserManager manager = new UserManager();
        Reader reader = new Reader();
        for (String userPath : reader.readUsers()) { // load users
            BufferedReader userInfoFileReader = Files.newBufferedReader(Path.of(userPath + "/user_info.txt"));
            String userInfo = userInfoFileReader.readLine();
            userInfoFileReader.close();
            String userId = userInfo.split(",")[0];
            String userName = userInfo.split(",")[1];
            String userPassword = userInfo.split(",")[2];
            User user = new User(userId, userName, userPassword);
            manager.putUser(userId, user); // put User into UserManager
            for (String packPath : reader.readPacks(userName)) { // load this user's packages
                BufferedReader packInfoFileReader
                        = Files.newBufferedReader(Path.of(packPath + "/package_info.txt"));
                String packInfo = packInfoFileReader.readLine();
                packInfoFileReader.close();
                String packId = packInfo.split(",")[0];
                String packName = packInfo.split(",")[1];
                Pack pack = new Pack(packId, packName);
                user.createPackage(pack); // put Pack into User
                for (String cardPath : reader.readCards(userName, packName)) { // load this user -> pack's cards
                    BufferedReader cardInfoFileReader = Files.newBufferedReader(Path.of(cardPath));
                    String cardInfo = cardInfoFileReader.readLine();
                    cardInfoFileReader.close();
                    String cardId = cardInfo.split(",")[0];
                    String cardTerm = cardInfo.split(",")[1];
                    String cardDefinition = cardInfo.split(",")[2];
                    String cardProficiency = cardInfo.split(",")[3];
                    Card card = new Card(cardId, cardTerm, cardDefinition);
                    card.setProficiency(Integer.parseInt(cardProficiency));
                    pack.addCard(card); // put Card into Pack
                }
            }
        }
        return manager;
    }

    // Tests
    public static void main(String... args) throws IOException {
        Loader loader = new Loader();
        UserManager manager = loader.load();
        System.out.println(manager.items().keySet());
        for (User user : manager.items().values()) {
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
