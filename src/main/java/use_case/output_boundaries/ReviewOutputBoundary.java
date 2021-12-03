package use_case.output_boundaries;

// TODO: before you change anything in this file, consult with Xing. Learn and review components work fine currently.
public interface ReviewOutputBoundary {
    void setReviewCompleted();

    boolean getReviewCompleted();

    void setCurrCardStrRep(String cardStrRep);

    String getCurrCardStrRep();
}
