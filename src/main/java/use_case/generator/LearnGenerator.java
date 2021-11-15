package use_case.generator;

import entity.Card;
import entity.Pack;
import use_case.input_boundaries.LearnInputBoundary;

import java.util.ArrayList;
import java.util.Collections;

public class LearnGenerator extends TaskGenerator implements LearnInputBoundary {

    public LearnGenerator(Pack pack) {
        super(pack);
    }

    /**
     * Generate a card list containing all cards eligible for learning
     * with random order and 2 occurrences of each card.
     *
     * @return an arraylist of card for learning
     */
    @Override
    public ArrayList<Card> getDoCardList() {
        doable();
        this.cardList.addAll(this.cardList); // So that every card has 2 occurrences
        Collections.shuffle(this.cardList);
        return this.cardList;
    }

    /**
     * Return a list of cards to be learned.
     */
    @Override
    protected void doable() {
//        System.out.println(this.pack.getCards());
        for (Card c : this.pack.getCards()) {
            if (c.getProficiency() == 0) {
                this.cardList.add(c);
            }
        }
    }


//    @Override
//    public String display_term(){
//        Scanner in = new Scanner(System.in);
//        System.out.println(framework.command_line_interface.constants.GREEN_BOLD_BRIGHT + "Press any key to start reviewing, type 99 to quit...");
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