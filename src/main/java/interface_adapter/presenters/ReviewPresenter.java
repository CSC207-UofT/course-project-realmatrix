package interface_adapter.presenters;

import use_case.output_boundaries.ReviewOutputBoundary;

// TODO: before you change anything in this file, consult with Xing. Learn and review components work fine currently.
public class ReviewPresenter implements ReviewOutputBoundary {
    private boolean learnCompleted = false;
    private String currCardStrRep = null;

    /**
     * Setter of Review, set to true iff review is completed.
     */
    @Override
    public void setReviewCompleted() {
        this.learnCompleted = true;
    }

    /**
     * Getter of Review
     *
     * @return true iff review is completed by user.
     */
    @Override
    public boolean getReviewCompleted() {
        return learnCompleted;
    }

    @Override
    public void setCurrCardStrRep(String cardStrRep) {
        this.currCardStrRep = cardStrRep;
    }

    @Override
    public String getCurrCardStrRep() {
        return currCardStrRep;
    }
}
