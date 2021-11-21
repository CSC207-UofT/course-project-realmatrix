package interface_adapter.Controller;

import entity.Card;
import entity.Pack;
import use_case.generator.ReviewGenerator;
import use_case.input_boundaries.CardInputBoundary;
import use_case.input_boundaries.ReviewInputBoundary;
import use_case.manager.CardManager;

import java.util.ArrayList;

public class ReviewController {
    private final ReviewInputBoundary reviewIB;

    public ReviewController(ReviewInputBoundary reviewIB) {
        this.reviewIB = reviewIB;
    }

    public Card next() {
        return reviewIB.next();
        // TODO: Where should I call the write method to update proficiency of the cards?
    }

    public void setShowDefinition() {
        reviewIB.setShowDefinition();
    }

    public void setCantRecall() {
        reviewIB.setCantRecall();
    }
}
