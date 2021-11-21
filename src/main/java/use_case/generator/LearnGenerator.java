package use_case.generator;

import entity.Card;
import entity.Pack;

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