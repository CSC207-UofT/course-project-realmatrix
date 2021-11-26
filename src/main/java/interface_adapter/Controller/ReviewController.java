package interface_adapter.Controller;

import entity.Card;
import entity.ProgramState;
import interface_adapter.gateway.DataInOut;
import interface_adapter.gateway.IDataInOut;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.input_boundaries.ReviewInputBoundary;

import java.io.IOException;

// TODO: before you change anything in this file, consult with Xing. Learn and review components work fine currently.
public class ReviewController {
    private final ReviewInputBoundary reviewIB;
    private final IDataInOut dataInOut = new DataInOut();
    private final ProgramStateInputBoundary programStateInputBoundary;

    public ReviewController(ReviewInputBoundary reviewIB, ProgramStateInputBoundary programStateInputBoundary) {
        this.reviewIB = reviewIB;
        this.programStateInputBoundary = programStateInputBoundary;
    }

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
     * This method sets can't recall to true.
     */
    public void setCantRecall() {
        reviewIB.setCantRecall();
    }

    public void setShowDefinition() {
        reviewIB.setShowDefinition();
    }
}
