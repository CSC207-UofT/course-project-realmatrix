package interface_adapter.Controller;

import entity.Card;
import use_case.input_boundaries.CardInputBoundary;
import use_case.input_boundaries.LearnInputBoundary;

import java.util.ArrayList;

public class LearnController {
    private final LearnInputBoundary learnIB;

    public LearnController(LearnInputBoundary learnIB) {
        this.learnIB = learnIB;
    }

    public void next() {
        learnIB.next();
    }
}
