package interface_adapter.Controller;

import entity.Card;
import entity.Pack;
import interface_adapter.gateway.DataInOut;
import interface_adapter.gateway.IDataInOut;
import use_case.generator.ReviewGenerator;
import use_case.input_boundaries.CardInputBoundary;
import use_case.input_boundaries.ReviewInputBoundary;

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

    public void next() throws IOException {
        // before going to the next card, update current card's proficiency in database
        Card currCard = reviewIB.getCurrCard();
        if (currCard != null) {
            dataInOut.write(programState, reviewIB.getCurrCard());
        }
        reviewIB.next();
    }

    /**
     * update the proficiency of the card that user currently reviewing
     *
     * @param opt user's option of the quality of reviewing
     * @param c   the card that user currently learning
     */
    public void updateMemProficiency(String opt, Card c) {
        this.cm.setCurrCard(c);
        if (opt.equals("1")) {
            this.cm.increaseProficiency();
        }

        if (opt.equals("3")) {
            this.cm.decreaseProficiency();
        }
    }

    /**
     * update the proficiency of the card that user currently testing
     *
     * @param opt user's correctness
     * @param c   the card that user currently learning
     */
    public void updateTestProficiency(String opt, Card c) {
        this.cm.setCurrCard(c);
        if (opt.equals("2")) {
            this.cm.decreaseProficiency();
            this.cm.decreaseProficiency();
        }
    }
}
