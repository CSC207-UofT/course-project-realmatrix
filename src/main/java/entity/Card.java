package entity;

/**
 * A single card, containing one piece of the content which
 * the user wants to memorize.
 */
public class Card {
    private final String id;
    private String term;
    private String definition;
    private boolean definitionHidden;
    private int proficiency;

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
        this.definitionHidden = false; // If true, hide definition so that users cannot see
        // TODO: Note proficiency may initialize to a different value
        this.proficiency = 0;   // proficiency represents to what extent the user has mastered this card

    }

    /**
     * Change the term on this card to a new term.
     * @param newTerm
     */
    public void changeTerm(String newTerm) {
        this.term = newTerm;
    }

    /**
     * Change the definition on this card.
     * @param newDefinition
     */
    public void changeDefinition(String newDefinition) {
        this.definition = newDefinition;
    }

    /**
     * Increase the proficiency of the card by [some value].
     */
    public void increaseProficiency() {
        // TODO: 20 is a placeholder value currently, waiting for change...
        this.proficiency = Math.min(this.proficiency + 20, 100);
    }

    /**
     * Decrease the proficiency of the card by [some value].
     */
    public void decreaseProficiency() {
        // TODO: 20 is a placeholder value currently, waiting for change...
        this.proficiency = Math.max(this.proficiency - 20, 0);
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