package gateway.dataout;

import entity.Card;
import entity.Pack;
import entity.User;
import use_case.manager.UserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Data files are structured in this way:
 * user_data/
 * users/
 * archived_users/
 * user1/
 * user2/
 * ...
 * user_info.txt
 * packages/
 * archived_packages/
 * package1/
 * package2/
 * ...
 * package_info.txt
 * cards/
 * archived_cards/
 * card1.txt
 * card2.txt
 * ...
 */
public class Loader {

    /**
     * Load all registered users' usernames and passwords for login verification.
     *
     * @return Returns a UserManager.
     */
    // TODO: Return UserManager from database is not clean.
    //TODO: Consider return a hashmap <String username, String password>.
    public UserManager initialLoad() throws IOException {
        UserManager manager = new UserManager();
        Reader reader = new Reader();
        for (String userPath : reader.readUsers()) { // load users
            this.putUser(userPath, manager);
        }
        return manager;
    }

    /**
     * Load a given user's packages and cards.
     *
     * @param user the given user
     * @throws IOException
     */
    public void userLoad(User user) throws Exception {
        Reader reader = new Reader();
        for (String packPath : reader.readPacks(user.getName())) { // load this user's packages
            Pack pack = this.putPack(packPath, user);
            for (String cardPath : reader.readCards(user.getName(), pack.getName())) { // load this pack's cards
                this.putCard(cardPath, pack);
            }
        }
    }

    /**
     * Helper to Loader.initialLoad(). Load user into user manager.
     *
     * @param userPath directory of user_info.txt
     * @param manager  user manager to save the user to
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
        //TODO: This line is bad: directly use constructor of entity class
        User user = new User(userId, userName, userPassword);
        manager.putUser(userId, user); // put User into UserManager
        return user;
    }

    /**
     * Helper to Loader.userLoad(). Load pack into user.
     *
     * @param packPath directory of package_info.txt
     * @param user     user to save package to
     * @return the loaded package
     * @throws IOException
     */
    private Pack putPack(String packPath, User user) throws Exception {
        BufferedReader packInfoFileReader
                = Files.newBufferedReader(Path.of(packPath + "/package_info.txt"));
        String packInfo = packInfoFileReader.readLine();
        packInfoFileReader.close();
        String packId = packInfo.split(",")[0];
        String packName = packInfo.split(",")[1];
        //TODO: This line is bad: directly use constructor of entity class
        Pack pack = new Pack(packId, packName);
        user.addPackage(pack); // put Pack into User
        return pack;
    }

    /**
     * Helper to Loader.userLoad(). Load card into pack.
     *
     * @param cardPath file path to card_term.txt
     * @param pack     pack to save card to
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
        //TODO: This line is bad: directly use constructor of entity class
        Card card = new Card(cardId, cardTerm, cardDefinition);
        card.setProficiency(Integer.parseInt(cardProficiency));
        try {
            pack.addCard(card); // put Card into Pack
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Tests
//    public static void main(String... args) throws IOException {
//        DataInOutFactory factory = new DataInOutFactory();
//        UserManager manager = factory.load();
//        System.out.println(manager.getItems().keySet());
//        for (User user : manager.getItems().values()) {
//            System.out.println(user.getName());
//            factory.load(user);
//            for (Pack pack : user.getPackages()) {
//                System.out.println(pack.getName());
//                for (Card card : pack.getCards()) {
//                    System.out.println(card.getTerm());
//                }
//            }
//        }
//    }
}