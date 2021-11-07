package use_case;

import entity.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class is a test generator that raise a collection of
 * cards for user to learn and review
 */
public abstract class TaskGenerator {

    /**
     * learnable method return whether a Card need to be learned
     *
     * @return boolean
     * TODO: implement needed
     */
    private Pack pack;
    private ArrayList<Card> cardList;

    public void TestGenerator(Pack pack){
        this.pack = pack;
        this.cardList = new ArrayList<>();
    }

    /**
     * modify cardList that can be learnt or reviewed.
     *
     * @return void
     * TODO: implement needed
     */
    private void doable() {
    }

}
