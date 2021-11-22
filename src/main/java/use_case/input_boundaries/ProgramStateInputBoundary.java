package use_case.input_boundaries;

import entity.*;

import java.beans.PropertyChangeListener;

public interface ProgramStateInputBoundary extends PropertyChangeListener {
    User getCurrUser();
    Pack getCurrPack();
    Card getCurrCard();
}
