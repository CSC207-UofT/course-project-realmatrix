package entity;

/**
 * A single card, containing one piece of the content which
 * the user wants to memorize.
 */
public class Card {
    private final String id;
    private String question;
    private String answer;
    private boolean answerHidden;
    private float proficiency;

    /**
     * Construct a Card with given id, question, and answer.
     * Initialize the proficiency of the card to 0.
     *
     * @param id    The unique id of this card
     * @param question  The question that the user wants to answer
     * @param answer     The answer to the question,
     *                   which is something the user wants to memorize
     */
    public Card(String id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.answerHidden = false;
        // TODO: Note proficiency may initialize to a different value
        // proficiency represents to what extent the user has mastered this card
        this.proficiency = 0;
    }

    /**
     * Change the question on this card to a new question.
     * @param newQuestion
     */
    public void changeQuestion(String newQuestion) {
        this.question = newQuestion;
    }

    /**
     * Change the answer on this card to a new answer.
     * @param newAnswer
     */
    public void changeAnswer(String newAnswer) {
        this.answer = newAnswer;
    }

    /**
     * Increase the proficiency of the card by [some value].
     */
    public void increaseProficiency() {
        // TODO: 20 is a placeholder value currently, waiting for change...
        this.proficiency += 20;
    }

    /**
     * Decrease the proficiency of the card by [some value].
     */
    public void decreaseProficiency() {
        // TODO: 20 is a placeholder value currently, waiting for change...
        this.proficiency -= 20;
    }

    public void changeAnswerHidden() {
        this.answerHidden = !this.answerHidden;
    }

    public String getId() {
        return this.id;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getAnswer() {
        return this.answer;
    }

    public boolean getAnswerHidden() {
        return this.answerHidden;
    }

    public float getProficiency() {
        return this.proficiency;
    }

    @Override
    public String toString() {
        if (this.answerHidden) {
            return String.format("Question: %s", this.question);
        } else {
            return String.format("Question: %1$s\nAnswer: %2$s",
                    this.question, this.answer);
        }
    }

//    Simple test
//    public static void main(String[] args) {
//        Card c = new Card("1231", "card", "卡");
//        c.changeHideAnswer();
//        System.out.println(c);
//    }

}