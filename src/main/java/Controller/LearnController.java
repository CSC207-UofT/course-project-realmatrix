package Controller;

import entity.Card;
import entity.Pack;
import manager.CardManager;
import use_case.LearnGenerator;

import java.util.ArrayList;

public class LearnController {

    /**
     * return term or definition based on user's option
     * @param opt option of user
     * @param c Card c that user currently facing
     * @return term or definition
     */
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

    /**
     * Return a list of card that needs to be learned
     * @param p the pack of cards need to be learned
     * @return a list of card
     */

    public ArrayList<Card> learnableCardList(Pack p){
        LearnGenerator lg = new LearnGenerator(p);
        return lg.doable();
    }

    /**
     * update the proficiency of the card that user currently learning
     * @param opt user's option of the quality of learning
     * @param c the card that user currently learning
     */
    public void updateMemProficiency(String opt, Card c) {
        CardManager cm = new CardManager();
        cm.setCurrCard(c);
        if (opt.equals("1")) {
            cm.increaseProficiency();
            cm.increaseProficiency();
        }
        if(opt.equals("2")){
            cm.increaseProficiency();
        }
    }
}
