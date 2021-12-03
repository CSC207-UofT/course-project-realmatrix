package interface_adapter.controller;

import use_case.input_boundaries.LearnInputBoundary;

/**
 * Controller that fetches user input and sends it to LearnGenerator.
 */
public class LearnController {
    private final LearnInputBoundary learnIB;

    public LearnController(LearnInputBoundary learnIB) {
        this.learnIB = learnIB;
    }

    /**
     * User wants to go to next card.
     */
    public void next() {
        learnIB.next();
    }
}
