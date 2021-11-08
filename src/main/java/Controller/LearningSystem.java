package Controller;

import entity.*;
import manager.CardManager;
import manager.PackManager;
import use_case.ReviewGenerator;
import Controller.ProgramState;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Properties;

public class LearningSystem {

    private Pack currPack;
    private PackManager pm;
    private CardManager cm;
    private ReviewGenerator rg;
    private ProgramState state;

    public LearningSystem(){
        this.currPack = null;
        this.pm = new PackManager();
        this.cm = new CardManager();
        this.rg = new ReviewGenerator(this.currPack);
        this.state = new ProgramState();
    }


    public Pack setCurrPack(String name) throws Exception {
        this.currPack = this.state.choosePack(name);
        return this.currPack;
    }

    public String learnDisplay(String opt, Card c){
        if(opt.equals("t")){
            return c.getTerm();
        }
        if(opt.equals("d")){
            return c.getDefinition();
        }
        else{
            return "";
        }
    }

    public String reviewDisplay(Card c){
        return c.getDefinition();
    }

    public ArrayList<Card> reviewableCardList(){
        ReviewGenerator rg = new ReviewGenerator(this.currPack);
        return rg.doable();
    }

    public void updateMemProficiency(String opt, Card c) {
        CardManager cm = new CardManager();
        cm.setCurrCard(c);
        if (opt.equals("1")) {
            cm.increaseProficiency();
        }

        if (opt.equals("3")) {
            cm.decreaseProficiency();
        }
    }
    public void updateTestProficiency(String opt, Card c){
        CardManager cm = new CardManager();
        cm.setCurrCard(c);
        if(opt.equals("2")){
            cm.decreaseProficiency();
        }
    }
}
