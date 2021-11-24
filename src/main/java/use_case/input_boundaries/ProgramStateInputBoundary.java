package use_case.input_boundaries;

import entity.*;

import java.beans.PropertyChangeListener;

public interface ProgramStateInputBoundary {
    User getCurrUser();
    Pack getCurrPack();
    Card getCurrCard();
    void setCurrUser(User user);
    void setCurrPack(String packname);
    void setCurrCard(String cardTerm);
}
