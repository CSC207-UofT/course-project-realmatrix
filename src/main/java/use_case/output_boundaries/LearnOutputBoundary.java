package use_case.output_boundaries;

/**
 * output boundary. layer between LearnGenerator output and view
 */
public interface LearnOutputBoundary {
    void setLearnCompleted();

    boolean getLearnCompleted();

    void setCurrCardStrRep(String cardStrRep);

    String getCurrCardStrRep();
}
