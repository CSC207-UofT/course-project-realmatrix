package use_case;

import entity.Card;
import entity.Pack;
import constants.Constants;

import java.util.ArrayList;

public class ReviewGenerator extends TaskGenerator{

    private Pack pack;
    private ArrayList<Card> cardList;

    public ReviewGenerator(Pack pack) {
        super();
    }



    public ArrayList<Card> doable() {
        for (Card c : this.pack.getCards()) {
            if (c.getProficiency() <= Constants.REVIEW_PROFICIENCY_MAX) {
                this.cardList.add(c);
            }
        }
        return this.cardList;
    }


    public ArrayList<Card> withProficiencyBasedCards(){
        ArrayList<Card> newCards = new ArrayList<>();
        for (Card c : this.doable()){
            int prof = c.getProficiency();
            for (int i = 0; i < (Constants.REVIEW_PROFICIENCY_MAX - prof); i++) {
                newCards.add(c);
            }
        }
        return newCards;
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

