package Controller;

import entity.Card;
import entity.Pack;
import entity.User;

public class ProgramState {
    private User currUser;
    private Pack currPack;
    private Card currCard;

    public ProgramState() {
        this.currUser = null;
        this.currPack = null;
        this.currCard = null;
    }

    public void setCurrUser(User currUser) {
        this.currUser = currUser;
    }

    public void setCurrCard(Card currCard) {
        this.currCard = currCard;
    }

    public void setCurrPack(Pack p) {
        this.currPack = p;
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

    public User getCurrUser() {
        return this.currUser;
    }

    public Card getCurrCard() {
        return this.currCard;
    }

    public Pack getCurrPack() {
        return this.currPack;
    }

    public Pack choosePack(String name) throws Exception {
        for (Pack p : this.currUser.getPackages()) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        throw new Exception("no such pack");
    }
}
