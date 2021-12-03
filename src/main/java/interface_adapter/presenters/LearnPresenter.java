package interface_adapter.presenters;

import use_case.output_boundaries.LearnOutputBoundary;

/**
 * Concrete implementation of LearnOutputBoundary. Present output data to user
 */
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
        if (currCardStrRep != null) {
            return this.currCardStrRep.replace("\n","<br>");
        }
        return null;
    }
}
