package use_case.manager;

import entity.Card;
import entity.Pack;
import entity.ProgramState;
import entity.User;
import use_case.input_boundaries.ProgramStateInputBoundary;

import java.util.HashMap;

/**
 * A manager class for program state.
 * Note, we only create **one instance** of it throughout the entire program.
 */
public class ProgramStateManager implements ProgramStateInputBoundary {
    private final ProgramState ps;

    public ProgramStateManager() {
        ps = new ProgramState();
    }

    /**
     * Getter for current user.
     * @return a User object, representing current user.
     */
    @Override
    public User getCurrUser() {
        return ps.getCurrUser();
    }

    /**
     * Getter for current username.
     * @return a string of current user's username.
     */
    @Override
    public String getCurrUserName() {
        if (ps.getCurrUser() != null) {
            return ps.getCurrUser().getName();
        }
        return null;
    }

    /**
     * Getter for current pack.
     * @return a Pack object, representing current pack.
     */
    @Override
    public Pack getCurrPack() {
        return ps.getCurrPack();
    }

    /**
     * Getter for current pack name.
     * @return a string of current pack's name.
     */
    @Override
    public String getCurrPackName() {
        if (ps.getCurrPack() != null) {
            return ps.getCurrPack().getName();
        }
        return null;
    }

    /**
     * Getter for current card.
     * @return a Card object, representing current card.
     */
    @Override
    public Card getCurrCard() {
        return ps.getCurrCard();
    }

    /**
     * Getter for current card's term.
     * @return a string of current card's term.
     */
    @Override
    public String getCurrCardTerm() {
        if (ps.getCurrCard() != null) {
            return ps.getCurrCard().getTerm();
        }
        return null;
    }

    /**
     * Getter for current card's definition.
     * @return a string of current card's definition.
     */
    @Override
    public String getCurrCardDefinition() {
        if (ps.getCurrCard() != null) {
            return ps.getCurrCard().getDefinition();
        }
        return null;
    }

    /**
     * Setter for current user.
     * @param user the current user
     */
    @Override
    public void setCurrUser(User user) {
        ps.setCurrUser(user);
    }

    /**
     * Setter for current pack.
     * @param packname the current pack's pack name.
     */
    @Override
    public void setCurrPack(String packname) {
        // Know: ps.currUser is not null
        if (packname != null) {
            HashMap<String, Pack> packMap = ps.getCurrUser().getPackageMap();
            ps.setCurrPack(packMap.get(packname));
        } else {
            ps.setCurrPack(null);
        }
    }

    /**
     * Setter for current card.
     * @param cardTerm the current card's term.
     */
    @Override
    public void setCurrCard(String cardTerm) {
        // Know: ps.currPack is not null
        if (cardTerm != null) {
            HashMap<String, Card> cardMap = ps.getCurrPack().getCardMap();
            ps.setCurrCard(cardMap.get(cardTerm));
        } else {
            ps.setCurrCard(null);
        }
    }
}
