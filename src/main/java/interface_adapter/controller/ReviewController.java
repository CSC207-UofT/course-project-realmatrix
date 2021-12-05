package interface_adapter.controller;

import entity.Card;
import interface_adapter.gateway.DataInOut;
import interface_adapter.gateway.IDataInOut;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.input_boundaries.ReviewInputBoundary;

import java.io.IOException;

/**
 * Controller that fetches user input and sends it to ReviewGenerator.
 */
public class ReviewController {
    private final ReviewInputBoundary reviewIB;
    private final IDataInOut dataInOut = new DataInOut();
    private final ProgramStateInputBoundary programStateInputBoundary;

    public ReviewController(ReviewInputBoundary reviewIB, ProgramStateInputBoundary programStateInputBoundary) {
        this.reviewIB = reviewIB;
        this.programStateInputBoundary = programStateInputBoundary;
    }

    /**
     * user wants to go to next card
     * @throws IOException fails to update card's proficiency in database
     */
    public void next() throws IOException {
        // before going to the next card, update current card's proficiency in database
        Card currCard = reviewIB.getCurrCard();
        if (currCard != null) {
            dataInOut.write(new String[] {programStateInputBoundary.getCurrUserName(), programStateInputBoundary.getCurrPackName()},
                    reviewIB.getCurrCard());
        }
        reviewIB.next();
    }

    /**
     * user remember the current card's definition wrong
     */
    public void setCantRecall() {
        reviewIB.setCantRecall();
    }

    /**
     * user wants to reveal current card's definition
     */
    public void setShowDefinition() {
        reviewIB.setShowDefinition();
    }
}
