package interface_adapter.presenters;

import use_case.output_boundaries.ReviewOutputBoundary;

public class ReviewPresenter implements ReviewOutputBoundary {
    private boolean learnCompleted = false;
    private String currCardStrRep = null;
    private boolean showDefinitionStatus = false;

    public void setReviewCompleted() {
        this.learnCompleted = true;
    }

    public boolean getReviewCompleted() {
        return learnCompleted;
    }

    public void setCurrCardStrRep(String cardStrRep) {
        this.currCardStrRep = cardStrRep;
    }

    public String getCurrCardStrRep() {
        return currCardStrRep;
    }

    public void setShowDefinitionStatus() {
        this.showDefinitionStatus = true;
    }

    public boolean getShowDefinitionStatus() {
        return this.showDefinitionStatus;
    }
}
