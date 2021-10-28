package Controller;

import entity.*;
import manager.CardManager;
import manager.PackManager;
import use_case.ReviewGenerator;
import Controller.ProgramState;

import java.util.ArrayList;

public class System {

    private Pack currPack;
    private PackManager pm;
    private CardManager cm;
    private ReviewGenerator rg;

    public System(){
        this.currPack = null;
        this.pm = new PackManager();
        this.cm = new CardManager();
        this.rg = new ReviewGenerator(this.currPack);
    }
//
//    public System(String state){
//        this.currPack = ProgramState.;
//        this.pm = new PackManager();
//        this.cm = new CardManager();
//        this.rg = new ReviewGenerator(this.currPack);
//    }


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
