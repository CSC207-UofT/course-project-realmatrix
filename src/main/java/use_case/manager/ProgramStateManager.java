package use_case.manager;

import entity.Card;
import entity.Pack;
import entity.ProgramState;
import entity.User;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.output_boundaries.RegisterOutputBoundary;

import java.beans.PropertyChangeEvent;
import java.util.Objects;

public class ProgramStateManager implements ProgramStateInputBoundary {
    @Override
    public User getCurrUser() {
        return ProgramState.getCurrUser();
    }

    @Override
    public Pack getCurrPack() {
        return ProgramState.getCurrPack();
    }

    @Override
    public Card getCurrCard() {
        return ProgramState.getCurrCard();
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "user":    // TODO: constant
                ProgramState.setCurrUser((User) evt.getNewValue());
                break;

            case "pack":    // TODO: constant
                ProgramState.setCurrPack((Pack) evt.getNewValue());
                break;

            case "card":    // TODO: constant
                ProgramState.setCurrCard((Card) evt.getNewValue());
                break;
        }
    }
}
