package use_case.output_boundaries;

/**
 * output boundary. layer between ReviewGenerator output and view
 */
public interface ReviewOutputBoundary {
    void setReviewCompleted();

    boolean getReviewCompleted();

    void setCurrCardStrRep(String cardStrRep);

    String getCurrCardStrRep();
}
