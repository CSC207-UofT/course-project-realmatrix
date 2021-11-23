package interface_adapter.presenters;

import use_case.output_boundaries.LearnOutputBoundary;

public class LearnPresenter implements LearnOutputBoundary {
    private boolean learnCompleted = false;
    private String currCardStrRep = null;

    public void setLearnCompleted() {
        this.learnCompleted = true;
    }

    public boolean getLearnCompleted() {
        return learnCompleted;
    }

    public void setCurrCardStrRep(String cardStrRep) {
        this.currCardStrRep = cardStrRep;
    }

    public String getCurrCardStrRep() {
        return currCardStrRep;
    }
}
