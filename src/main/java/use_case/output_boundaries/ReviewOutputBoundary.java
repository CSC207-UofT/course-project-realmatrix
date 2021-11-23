package use_case.output_boundaries;

public interface ReviewOutputBoundary {
    public void setReviewCompleted();

    public boolean getReviewCompleted();

    public void setCurrCardStrRep(String cardStrRep);

    public String getCurrCardStrRep();

    public void setShowDefinitionStatus();

    public boolean getShowDefinitionStatus();
}
