package manager;

import entity.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * A pack manager manages a collection of cards stored in a pack.
 */
public class PackManager extends Manager<Pack> implements Sort<Card> {
    private Pack currPack = null; // The initial state where the user is not in any pack

    public PackManager() {
        super();
    }

    /**
     * Create and return a new pack with specified pack name.
     * Stores id and the corresponding pack in idToItem.
     *
     * @param packName   The name of the pack
     * @return           The newly-created pack
     */
    public Pack createNewPack(String packName) {
        String id = generateId();
        Pack p = new Pack(id, packName);
        this.idToItem.put(id, p);
        return p;
    }

    /**
     * Add a new card into current pack.
     *
     */
    public void addCard(Card card) throws Exception {
        this.currPack.addCard(card);
    }

    /**
     * Delete a specific card in the current pack.
     *
     */
    public void deleteCard(Card card) throws Exception {
        this.currPack.deleteCard(card);
    }

    /**
     * Users can search cards by card's term.
     * Return an arraylist of cards that contain (not necessarily equal to) term.
     * @param term  the term that the user searches
     * @return      an arraylist of cards that contain term
     */
    public ArrayList<Card> searchCardByTerm(String term) {
        ArrayList<Card> lst = new ArrayList<>();
        for (Card c: this.currPack.getCards()) {
            if (c.getTerm().contains(term)) {
                lst.add(c);
            }
        }
        return lst;
    }

    /**
     * Users can search cards by card's definition.
     * Return an arraylist of cards that contain (not necessarily equal to) definition.
     * @param keyWord  the key word in the definition that the user searches
     * @return      an arraylist of cards that contain the key word of definition
     */
    public ArrayList<Card> searchCardByDefinition(String keyWord) {
        ArrayList<Card> lst = new ArrayList<>();
        for (Card c: this.currPack.getCards()) {
            if (c.getDefinition().contains(keyWord)) {
                lst.add(c);
            }
        }
        return lst;
    }

    /**
     * Return a card list sorted by date added: oldest to newest.
     *
     * @return an arraylist of sorted cards
     */
    public ArrayList<Card> sortOldToNew() {
        return this.currPack.getCards();
    }

    /**
     * Return a card list sorted by date added: newest to oldest
     *
     * @return an arraylist of sorted cards
     */
    public ArrayList<Card> sortNewToOld() {
        ArrayList<Card> lst = (ArrayList<Card>) this.currPack.getCards().clone();
        Collections.reverse(lst);
        return lst;
    }

    /**
     * Return a card list sorted by cards' terms' alphabetical order: a - z.
     *
     * @return an arraylist of sorted cards
     */
    public ArrayList<Card> sortAtoZ() {
        ArrayList<Card> lst = (ArrayList<Card>) this.currPack.getCards().clone();
        lst.sort(new AlphabetComparator());
        return lst;
    }

    /**
     * Return a card list sorted by cards' terms' alphabetical order: z - a.
     *
     * @return an arraylist of sorted cards
     */
    public ArrayList<Card> sortZtoA() {
        ArrayList<Card> lst = (ArrayList<Card>) this.currPack.getCards().clone();
        lst.sort(new AlphabetComparator().reversed());
        return lst;
    }


    /**
     * Return a card list sorted by cards' proficiency: low to high.
     *
     * @return an arraylist of sorted cards
     */
    public ArrayList<Card> sortProLowToHigh() {
        ArrayList<Card> lst = (ArrayList<Card>) this.currPack.getCards().clone();
        lst.sort(new ProficiencyComparator());
        return lst;
    }

    /**
     * Return a card list sorted by cards' proficiency: high to low.
     *
     * @return an arraylist of sorted cards
     */
    public ArrayList<Card> sortProHighToLow() {
        ArrayList<Card> lst = (ArrayList<Card>) this.currPack.getCards().clone();
        lst.sort(new ProficiencyComparator().reversed());
        return lst;
    }

    /**
     * Return a card list sorted in random order.
     *
     * @return an arraylist of sorted cards
     */
    public ArrayList<Card> sortRandom() {
        ArrayList<Card> lst = (ArrayList<Card>) this.currPack.getCards().clone();
        // TODO: implementation
        return lst;
    }


    private class AlphabetComparator implements Comparator<Card> {
        /**
         * Compare 2 cards according to their terms' alphabetical order (ignore case).
         *
         * Return a negative integer if c1 < c2,
         *        zero if c1 == c2,
         *        a positive integer if c1 > c2
         * in terms of alphabetical order (ignore cases) of their terms.
         *
         * @param c1    the first card
         * @param c2    the second card
         * @return a negative integer, zero, or a positive integer if
         * if c1 < c2, c1 == c2, or c1 > c2 in terms of alphabetical order of terms.
         */
        @Override
        public int compare(Card c1, Card c2) {
            return c1.getTerm().compareToIgnoreCase(c2.getTerm());
        }
    }

    private class ProficiencyComparator implements Comparator<Card> {
        /**
         * Compare 2 cards according to their terms' proficiency.
         *
         * Return a negative integer if c1 < c2,
         *        zero if c1 == c2,
         *        a positive integer if c1 > c2
         * in terms of proficiency.
         *
         * @param c1    the first card
         * @param c2    the second card
         * @return a negative integer, zero, or a positive integer
         * if c1 < c2, c1 == c2, or c1 > c2 in terms of proficiency.
         */
        @Override
        public int compare(Card c1, Card c2) {
            return c1.getProficiency() - c2.getProficiency();
        }
    }

    /**
     * Getter for the current pack the user is in.
     * @return the current pack.
     */
    public Pack getCurrPack() {
        return this.currPack;
    }

    /**
     * Change to the current pack the user is in.
     */
    public void setCurrPack(Pack pack) {
        this.currPack = pack;
    }

}
