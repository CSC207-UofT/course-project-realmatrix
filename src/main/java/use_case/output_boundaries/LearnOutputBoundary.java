package use_case.output_boundaries;

public interface LearnOutputBoundary {
    void setLearnCompleted();

    boolean getLearnCompleted();

    void setCurrCardStrRep(String cardStrRep);

    String getCurrCardStrRep();
}
