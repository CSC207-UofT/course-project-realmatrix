package interface_adapter.Controller;

import entity.Card;
import entity.Pack;
import interface_adapter.gateway.DataInOut;
import interface_adapter.gateway.IDataInOut;
import use_case.generator.ReviewGenerator;
import use_case.input_boundaries.CardInputBoundary;
import use_case.input_boundaries.ReviewInputBoundary;
import use_case.manager.CardManager;

import java.io.IOException;
import java.util.ArrayList;

public class ReviewController {
    private final ReviewInputBoundary reviewIB;
    private final IDataInOut dataInOut = new DataInOut();
    private final ProgramState programState;

    public ReviewController(ReviewInputBoundary reviewIB, ProgramState programState) {
        this.reviewIB = reviewIB;
        this.programState = programState;
    }

    public Card next() throws IOException {
        // before going to the next card, update current card's proficiency in database
        dataInOut.write(programState, reviewIB.getCurrCard());
        return reviewIB.next();
    }

    public void setShowDefinition() {
        reviewIB.setShowDefinition();
    }

    public void setCantRecall() {
        reviewIB.setCantRecall();
    }
}
