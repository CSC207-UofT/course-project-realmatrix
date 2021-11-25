package use_case.output_boundaries;

// TODO: before you change anything in this file, consult with Xing
public interface ReviewOutputBoundary {
    public void setReviewCompleted();

    public boolean getReviewCompleted();

    public void setCurrCardStrRep(String cardStrRep);

    public String getCurrCardStrRep();
}
