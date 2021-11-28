package use_case.input_boundaries;

import entity.*;

public interface ProgramStateInputBoundary {
    User getCurrUser();
    String getCurrUserName();
    Pack getCurrPack();
    String getCurrPackName();
    Card getCurrCard();
    String getCurrCardTerm();
    String getCurrCardDefinition();
    void setCurrUser(User user);
    void setCurrPack(String packname);
    void setCurrCard(String cardTerm);
}
