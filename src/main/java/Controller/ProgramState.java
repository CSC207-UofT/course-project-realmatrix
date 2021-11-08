package Controller;

import entity.*;

public class ProgramState {
    private User currUser;
    private Pack currPack;
    private Card currCard;

    public ProgramState(){
        this.currUser = null;
        this.currPack = null;
        this.currCard = null;
    }

    public void setState(User u, Pack p, Card c){
        this.currUser = u;
        this.currPack = p;
        this.currCard = c;
    }

    public String toString() {
        return "Current User is" + this.currUser.toString() + "\n" +
                "Current Pack is" + this.currPack.toString() + "\n" +
                "Current Card is" + this.currCard.toString() + "\n";
    }

    public User getCurrUser(){
        return this.currUser;
    }
    public Card getCurrCard(){
        return this.currCard;
    }
    public Pack getCurrPack(){
        return this.currPack;
    }

    public Pack choosePack(String name) throws Exception{
        for(Pack p: this.currUser.getPackages()){
            if(p.getName().equals(name)){
                return p;
            }
        }
        throw new Exception("no such pack");
    }
}
