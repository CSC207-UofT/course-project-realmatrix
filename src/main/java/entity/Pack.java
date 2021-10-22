package entity;

import java.util.ArrayList;

/**
 * This class is a package that stores the name of the package owner and the snippets in this package.
 */

public class Pack {

    private ArrayList<Card> cardList;
    private final String id;
    private String name;

    public Pack(String id, String name) {
        this.id = id;
        this.name = name;
        this.cardList = new ArrayList<>();
    }

    /**
     * Add method add a card in cardList according to proficiency and return
     * whether a card is added into cardList successfully.
     * @return boolean
     */
    public void add(Card card) {
        this.cardList.add(card);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getCards() {
        return this.cardList;
    }

    public void changeName(String newName) {
        this.name = newName;
    }
}
