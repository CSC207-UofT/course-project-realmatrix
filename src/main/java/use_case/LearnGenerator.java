package use_case;

import entity.Card;
import entity.Pack;
import constants.Constants;

import java.util.ArrayList;

public class LearnGenerator extends TaskGenerator{

    private Pack pack;
    private ArrayList<Card> cardList;

    public LearnGenerator(Pack pack) {
        super();
    }


    /**
     * Return a list of cards to be learned.
     */
    public ArrayList<Card> doable() {
        this.cardList.addAll(this.pack.getCards());
        return this.cardList;
    }


//    @Override
//    public String display_term(){
//        Scanner in = new Scanner(System.in);
//        System.out.println(constants.GREEN_BOLD_BRIGHT + "Press any key to start reviewing, type 99 to quit...");
//        String option = in.nextLine();
//        if (option != "99"){
//            for (Card c:cardList){
//                System.out.println(c.getTerm());
//                System.out.println("Can you recall the definition? Type the most suitable option");
//                System.out.println("1. Clearly can, 2. Blur memory, 3. I totally forget");
//
//            }
//        }
//        return;
}