package entity;

/**
 * This class stores the current state of our program, including
 *      - the current user,
 *      - the current pack the current user is in,
 *      - the current card the user is learning/reviewing.
 *
 * All variables are static, ensuring creating a new instance of ProgramState won't affect
 * the tracking process.
 */
public class ProgramState {
    private static User currUser = null;
    private static Pack currPack = null;
    private static Card currCard = null;


    // Setter for currUser
    public static void setCurrUser(User user) {
        currUser = user;
    }

    // Setter for currCard
    public static void setCurrCard(Card card) {
        currCard = card;
    }

    // Setter for currPack
    public static void setCurrPack(Pack p) {
        currPack = p;
    }

    public String toString() {
        String user;
        String pack;
        String card;
        if (currUser == null) {
            user = "NULL";
        } else {
            user = currUser.toString();
        }
        if (currPack == null) {
            pack = "NULL";
        } else {
            pack = currPack.toString();
        }
        if (currCard == null) {
            card = "NULL";
        } else {
            card = currCard.toString();
        }

        return "Current User is: " + user + "\n" +
                "Current Pack is: " + pack + "\n" +
                "Current Card is: " + card + "\n";
    }

    // Getter for currUser
    public static User getCurrUser() {
        return currUser;
    }

    // Getter for currCard
    public static Card getCurrCard() {
        return currCard;
    }

    // Getter for currPack
    public static Pack getCurrPack() {
        return currPack;
    }
}
