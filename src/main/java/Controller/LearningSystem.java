package Controller;

import entity.*;
import manager.CardManager;
import manager.PackManager;
import use_case.ReviewGenerator;
import use_case.LearnGenerator;
import Controller.ProgramState;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Properties;

public class LearningSystem {

    private User currUser;
    private Pack currPack;

    private PackManager pm;
    private CardManager cm;
    private LearnGenerator lg;
    private ReviewGenerator rg;
    private ProgramState state;

    public LearningSystem(User user){
        this.currPack = null;
        this.currUser = user;
        this.pm = new PackManager();
        this.cm = new CardManager();
        this.lg = new LearnGenerator(this.currPack);
        this.rg = new ReviewGenerator(this.currPack);
        this.state = new ProgramState();
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

    public ArrayList<Card> learnableCardList(Pack p){
        LearnGenerator lg = new LearnGenerator(p);
        return lg.doable();
    }

    public String reviewDisplay(Card c){
        return c.getDefinition();
    }

    public ArrayList<Card> reviewableCardList(Pack p){
        ReviewGenerator rg = new ReviewGenerator(p);
        return rg.withProficiencyBasedCards();
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
            cm.decreaseProficiency();
        }
    }
}
