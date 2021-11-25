package use_case.output_boundaries;

// TODO: before you change anything in this file, consult with Xing. Learn and review components work fine currently.
public interface ReviewOutputBoundary {
    public void setReviewCompleted();

    public boolean getReviewCompleted();

    public void setCurrCardStrRep(String cardStrRep);

    public String getCurrCardStrRep();
}
