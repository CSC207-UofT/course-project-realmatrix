package use_case.generator;

import entity.Card;
import entity.Pack;
import use_case.manager.CardManager;

public class ReviewGenerator extends TaskGenerator {
    private boolean showDefinition = false;
    private boolean cantRecall = false;

    public ReviewGenerator(Pack pack) {
        super(pack);
    }

    @Override
    public Card next() {
        if (cantRecall) {
            cards.add(currCard);
            // This is because user will recall that card eventually so the net effect on proficiency is (-2 + 1 = -1)
            currCard.setProficiency(Math.min(0, currCard.getProficiency() - 2));
        } else {
            currCard.setProficiency(Math.max(5, currCard.getProficiency() + 1));
        }
        Card nextCard = cards.poll();
        if (nextCard != null) {
            nextCard.hideDefinition();
            currCard = nextCard;
            showDefinition = false;
            cantRecall = false;
            return nextCard;
        } else {
            return null;
        }
    }

    public void setShowDefinition() {
        showDefinition = true;
        currCard.unhideDefinition();
    }

    public void setCantRecall() {
        cantRecall = true;
    }

    public boolean showDefinitionStatus() {
        return this.showDefinition;
    }
}
