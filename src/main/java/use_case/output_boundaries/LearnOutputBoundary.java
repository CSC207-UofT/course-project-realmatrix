package use_case.output_boundaries;

// TODO: before you change anything in this file, consult with Xing. Learn and review components work fine currently.
public interface LearnOutputBoundary {
    void setLearnCompleted();

    boolean getLearnCompleted();

    void setCurrCardStrRep(String cardStrRep);

    String getCurrCardStrRep();
}
