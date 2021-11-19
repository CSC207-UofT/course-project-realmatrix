package interface_adapter.Controller;

import entity.Card;
import entity.Pack;
import entity.User;

/**
 * This class stores the current User, Pack, Card.
 * This class implements Singleton design pattern.
 */
public class ProgramState {
    private static final ProgramState state = new ProgramState();
    private User currUser;  // the current User
    private Pack currPack;  // the current Pack
    private Card currCard;  // the current Card

    private ProgramState() {
        // Initialize all attributes to null
        this.currUser = null;
        this.currPack = null;
        this.currCard = null;
    }

    /**
     * Setter for currUser. Need to set currUser when a user signs in.
     * @param currUser the current User
     */
    public void setCurrUser(User currUser) {
        this.currUser = currUser;
    }

    /**
     * Setter for currCard. Need to set currCard when a user creates/edits a card.
     * @param currCard the current Card
     */
    public void setCurrCard(Card currCard) {
        this.currCard = currCard;
    }

    /**
     * Setter for currPack. Need to set currPack when a user enters a pack.
     * @param currPack the current Pack
     */
    public void setCurrPack(Pack currPack) {
        this.currPack = currPack;
    }

    /**
     * Getter for the static ProgramState object.
     * @return a ProgramState object.
     */
    public static ProgramState getState(){
        return state;
    }

    /**
     * Getter for currUser.
     * @return a User object.
     */
    public User getCurrUser() {
        return this.currUser;
    }

    /**
     * Getter for currCard.
     * @return a Card object.
     */
    public Card getCurrCard() {
        return this.currCard;
    }

    /**
     * Getter for currPack.
     * @return a Pack object.
     */
    public Pack getCurrPack() {
        return this.currPack;
    }

    public String toString() {
        String user;
        String pack;
        String card;
        if (this.currUser == null) {
            user = "NULL";
        } else {
            user = this.currUser.toString();
        }
        if (this.currPack == null) {
            pack = "NULL";
        } else {
            pack = this.currPack.toString();
        }
        if (this.currCard == null) {
            card = "NULL";
        } else {
            card = this.currCard.toString();
        }

        return "Current User is: " + user + "\n" +
                "Current Pack is: " + pack + "\n" +
                "Current Card is: " + card + "\n";
    }

    public Pack choosePack(String name) throws Exception {
        for (Pack p : this.currUser.getPackages()) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        throw new Exception("no such pack");
    }

//     For Test purpose
//    public static void main(String[] args) {
//        ProgramState state = ProgramState.getState();
//        System.out.println(state);
//        state.setCurrCard(new Card("apple", "bad"));
//
//        ProgramState state1 = ProgramState.getState();
//        System.out.println(state1);
//        state1.setCurrPack(new Pack("words"));
//
//        ProgramState state2 = ProgramState.getState();
//        System.out.println(state2);
//    }
}
