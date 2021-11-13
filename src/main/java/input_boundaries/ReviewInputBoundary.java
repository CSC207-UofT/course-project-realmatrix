package input_boundaries;

import entity.Card;

import java.util.ArrayList;

public interface ReviewInputBoundary {
    ArrayList<Card> withProficiencyBasedCards();
    ArrayList<Card> dailyReviewCards();
}
