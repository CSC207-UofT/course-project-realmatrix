package interface_adapter.presenters;

import use_case.output_boundaries.LearnOutputBoundary;

// TODO: before you change anything in this file, consult with Xing. Learn and review components work fine currently.
public class LearnPresenter implements LearnOutputBoundary {
    private boolean learnCompleted = false;
    private String currCardStrRep = null;

    /**
     * set learnCompleted to true after user learned
     */
    @Override
    public void setLearnCompleted() {
        this.learnCompleted = true;
    }

    /**
     * getter of LearnCompleted
     * @return true if user already learned
     */
    @Override
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
