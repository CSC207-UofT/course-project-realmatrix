package entity;
import java.util.ArrayList;

/**
 * This class is a package that stores the name of the package owner and the snippets in this package.
 */

public class Pack {

    private User owner;
    private ArrayList<Card> cardList;
    private final String id;

    public Pack(User owner, String id, String id1) {
        this.owner = owner;
        this.id = id;
        this.cardList = new ArrayList<Card>();
    }

    /**
     * Add method add a card in cardList according to proficiency and return
     * whether a card is added into cardList successfully.
     * @return boolean
     * TODO: implement needed
     */
    private boolean add(Card card) {
        return true;
    }

    public String getId() {
        return id;
    }
}
