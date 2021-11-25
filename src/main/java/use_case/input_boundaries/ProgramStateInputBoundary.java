package use_case.input_boundaries;

import entity.*;

import java.beans.PropertyChangeListener;

public interface ProgramStateInputBoundary {
    User getCurrUser();
    String getCurrUserName();
    Pack getCurrPack();
    String getCurrPackName();
    Card getCurrCard();
    void setCurrUser(User user);
    void setCurrPack(String packname);
    void setCurrCard(String cardTerm);
}
