package use_case.generator;

import entity.Card;
import entity.Pack;

/**
 * Learn a pack by going over all the cards once, showing both term and definition.
 */
public class LearnGenerator extends TaskGenerator {

    public LearnGenerator(Pack pack) {
        super(pack);
    }

    @Override
    public Card next() {
        Card nextCard = cards.poll();
        if (nextCard != null) {
            currCard = nextCard;
            return nextCard;
        } else {
            return null;
        }
    }
}