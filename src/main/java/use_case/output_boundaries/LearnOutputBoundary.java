package use_case.output_boundaries;

// TODO: before you change anything in this file, consult with Xing
public interface LearnOutputBoundary {
    void setLearnCompleted();

    boolean getLearnCompleted();

    void setCurrCardStrRep(String cardStrRep);

    String getCurrCardStrRep();
}
