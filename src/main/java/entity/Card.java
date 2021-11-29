package entity;

/**
 * A single card, containing one piece of the content which
 * the user wants to memorize.
 */
public class Card {
    private String term;
    private String definition;
    private boolean definitionHidden; // If true, hide definition so that users cannot see
    private int proficiency; // proficiency represents to what extent the user has mastered this card, ranges from 0-3

    /**
     * Construct a Card with given id, term, and definition.
     * Initialize the proficiency of the card to 0.
     *
     * @param term       The term on the card
     * @param definition The definition to the term,
     *                   which is something the user wants to memorize
     */
    public Card(String term, String definition) {
        this.term = term;
        this.definition = definition;
        this.definitionHidden = false;
        this.proficiency = 0;

    }

    public void hideDefinition() {
        this.definitionHidden = true;
    }

    public void unhideDefinition() {
        this.definitionHidden = false;
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

//    public void setProficiencyToMax(){
//        this.proficiency = Constants.PROFICIENCY_MAX;
//    }
//
//    public void setProficiencyToMin(){
//        this.proficiency = Constants.PROFICIENCY_MIN;
//    }

    /**
     * Change the term on this card to a new term.
     *
     * @param newTerm the new term for the card.
     */
    public void setTerm(String newTerm) {
        this.term = newTerm;
    }

    /**
     * Change the definition on this card.
     *
     * @param newDefinition the new definition for the card.
     */
    public void setDefinition(String newDefinition) {
        this.definition = newDefinition;
    }


    @Override
    public String toString() {
        if (this.definitionHidden) {
            return String.format("Term: %s", this.term);
        } else {
            return String.format("Term: %1$s \n Definition: %2$s",
                    this.term, this.definition);
//            return "Term: " + this.term + "\n" + "Definition: " + this.definition;
        }
    }

//    Simple test
//    public static void main(String[] args) {
//        Card c = new Card("1231", "card", "卡");
//        c.changeHideDefinition();
//        System.out.println(c);
//    }
}