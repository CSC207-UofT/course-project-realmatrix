package interface_adapter.presenters;

import use_case.output_boundaries.ReviewOutputBoundary;

/**
 * Concrete implementation of ReviewOutputBoundary. Present output data to user
 */
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
        if (currCardStrRep != null) {
            return currCardStrRep.replace("\n", "<br>");
        }
        return null;
    }
}
