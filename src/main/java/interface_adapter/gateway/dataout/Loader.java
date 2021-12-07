package interface_adapter.gateway.dataout;

import entity.Card;
import entity.Pack;
import entity.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * Data files are structured in this way:
 * user_data/
 * users/
 * user1/
 * user2/
 * ...
 * user_info.txt
 * packages/
 * package1/
 * package2/
 * ...
 * cards/
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
    public HashMap<String, String> initialLoad() throws IOException {
        HashMap<String, String> map = new HashMap<>();
        Reader reader = new Reader();
        for (String userPath : reader.readUsers()) { // load users
            this.putUser(userPath, map);
        }
        return map;
    }

    /**
     * Load a given user's packages and cards.
     *
     * @param user the given user
     * @throws IOException fail to load
     */
    public void userLoad(User user) throws IOException {
        Reader reader = new Reader();
        for (String packPath : reader.readPacks(user.getName())) { // load this user's packages
            Pack pack = new Pack(Path.of(packPath).getFileName().toString());
            user.addPackage(pack);
            for (String cardPath : reader.readCards(user.getName(), pack.getName())) { // load this pack's cards
                this.putCard(cardPath, pack);
            }
        }
    }

    /**
     * Helper to Loader.initialLoad(). Load user info.
     *
     * @param userPath directory of user_info.txt
     * @param map      map to save the username to user password
     * @throws IOException fails to load
     */
    private void putUser(String userPath, HashMap<String, String> map) throws IOException {
        BufferedReader userInfoFileReader = Files.newBufferedReader(Path.of(userPath + "/user_info.txt"));
        String userPassword = userInfoFileReader.readLine();
        String userName = Path.of(userPath).getFileName().toString();
        userInfoFileReader.close();
        map.put(userName, userPassword);
    }

    /**
     * Helper to Loader.userLoad(). Load card into pack.
     *
     * @param cardPath file path to card_term.txt
     * @param pack     pack to save card to
     * @throws IOException fail to load
     */
    private void putCard(String cardPath, Pack pack) throws IOException {
        String cardFileName = Path.of(cardPath).getFileName().toString();
        String cardTerm = cardFileName.substring(0, cardFileName.lastIndexOf("."));

        String cardInfo = Files.readString(Path.of(cardPath));
        String cardDefinition = cardInfo.substring(0, cardInfo.lastIndexOf(","));
        String cardProficiency = cardInfo.substring(cardInfo.length() - 1);
        Card card = new Card(cardTerm, cardDefinition);
        card.setProficiency(Integer.parseInt(cardProficiency));
        pack.addCard(card);
    }


}