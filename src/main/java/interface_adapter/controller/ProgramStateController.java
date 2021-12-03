package interface_adapter.controller;

import use_case.input_boundaries.ProgramStateInputBoundary;

/**
 * A controller for program state.
 * When user switches into/out of pack/card frames, this controller should be called.
 */
public class ProgramStateController {
    private final ProgramStateInputBoundary programStateInputBoundary;

    public ProgramStateController(ProgramStateInputBoundary programStateInputBoundary) {
        this.programStateInputBoundary = programStateInputBoundary;
    }

    /**
     * Set to current pack.
     * @param packName either a pack's name or null.
     */
    public void setCurrPack(String packName) {
        programStateInputBoundary.setCurrPack(packName);
    }

    /**
     * Set to current card.
     * @param cardTerm either a card's name or null.
     */
    public void setCurrCard(String cardTerm) {
        programStateInputBoundary.setCurrCard(cardTerm);
    }

    /**
     * Getter for current user's username.
     * @return a string of current username.
     */
    public String getCurrUserName() {
        return programStateInputBoundary.getCurrUserName();
    }

    /**
     * Getter for current pack name
     * @return a pack name
     */
    public String getCurrPackName() {
        return programStateInputBoundary.getCurrPackName();
    }

    /**
     * Getter for current card term
     * @return a card term
     */
    public String getCurrCardTerm() {
        return programStateInputBoundary.getCurrCardTerm();
    }

    /**
     * Getter for current card definition
     * @return a card definition
     */
    public String getCurrCardDef() {
        return programStateInputBoundary.getCurrCardDefinition();
    }
}
