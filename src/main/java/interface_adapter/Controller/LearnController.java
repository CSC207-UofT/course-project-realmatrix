package interface_adapter.Controller;

import use_case.input_boundaries.LearnInputBoundary;

// TODO: before you change anything in this file, consult with Xing
public class LearnController {
    private final LearnInputBoundary learnIB;

    public LearnController(LearnInputBoundary learnIB) {
        this.learnIB = learnIB;
    }

    public void next() {
        learnIB.next();
    }
}
