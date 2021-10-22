package entity;

/**
 * A single card, containing one piece of the content which
 * the user wants to memorize.
 */
public class Card {
    private final String id;
    private String term;
    private String definition;
    private boolean definitionHidden; // If true, hide definition so that users cannot see
    private int proficiency; // proficiency represents to what extent the user has mastered this card, ranges from 1-5

    /**
     * Construct a Card with given id, term, and definition.
     * Initialize the proficiency of the card to 0.
     *
     * @param id    The unique id of this card
     * @param term  The term on the card
     * @param definition The definition to the term,
     *                   which is something the user wants to memorize
     */
    public Card(String id, String term, String definition) {
        this.id = id;
        this.term = term;
        this.definition = definition;
        this.definitionHidden = false;
        this.proficiency = 1;

    }

    public void hideDefinition() {
        this.definitionHidden = true;
    }

    public void unhideDefinition() {
        this.definitionHidden = false;
    }

    public String getId() {
        return this.id;
    }

    public String getTerm() {
        return this.term;
    }

    public String getDefinition() {
        return this.definition;
    }

    public boolean getDefinitionHidden() {
        return this.definitionHidden;
    }

    public int getProficiency() {
        return this.proficiency;
    }

    public void setProficiency(int pro) {
        this.proficiency = pro;
    }

    /**
     * Change the term on this card to a new term.
     * @param newTerm
     */
    public void setTerm(String newTerm) {
        this.term = newTerm;
    }

    /**
     * Change the definition on this card.
     * @param newDefinition
     */
    public void setDefinition(String newDefinition) {
        this.definition = newDefinition;
    }


    @Override
    public String toString() {
        if (this.definitionHidden) {
            return String.format("Term: %s", this.term);
        } else {
            return String.format("Term: %1$s\nDefinition: %2$s",
                    this.term, this.definition);
        }
    }

//    Simple test
//    public static void main(String[] args) {
//        Card c = new Card("1231", "card", "卡");
//        c.changeHideDefinition();
//        System.out.println(c);
//    }
}
