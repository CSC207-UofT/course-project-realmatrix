package entity;

/**
 * This class stores the current state of our program, including
 *      - the current user,
 *      - the current pack the current user is in,
 *      - the current card the user is learning/reviewing.
 */
public class ProgramState {
    private User currUser = null;
    private Pack currPack = null;
    private Card currCard = null;


    // Setter for currUser
    public void setCurrUser(User user) {
        currUser = user;
    }

    // Setter for currCard
    public void setCurrCard(Card card) {
        currCard = card;
    }

    // Setter for currPack
    public void setCurrPack(Pack pack) {
        currPack = pack;
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
    public User getCurrUser() {
        return currUser;
    }

    // Getter for currCard
    public Card getCurrCard() {
        return currCard;
    }

    // Getter for currPack
    public Pack getCurrPack() {
        return currPack;
    }
}
