package Controller;

import entity.Card;
import entity.Pack;
import input_boundaries.CardInputBoundary;
import input_boundaries.LearnInputBoundary;
import use_case.manager.CardManager;
import use_case.LearnGenerator;

import java.util.ArrayList;

public class LearnController {
    private final LearnInputBoundary lg;
    private final CardInputBoundary cm;

    public LearnController(Pack p){
        this.lg = new LearnGenerator(p);
        this.cm = new CardManager();
    }

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
     * @return a list of card
     */

    public ArrayList<Card> learnableCardList(){
        return this.lg.getDoCardList();
    }

    /**
     * update the proficiency of the card that user currently learning
     * @param opt user's option of the quality of learning
     * @param c the card that user currently learning
     */
    public void updateMemProficiency(String opt, Card c) {
        this.cm.setCurrCard(c);
        if (opt.equals("1")) {
            this.cm.increaseProficiency();
            this.cm.increaseProficiency();
        }
        if(opt.equals("2")){
            this.cm.increaseProficiency();
        }
    }
}
