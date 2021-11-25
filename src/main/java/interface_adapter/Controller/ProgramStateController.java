package interface_adapter.Controller;

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
}
