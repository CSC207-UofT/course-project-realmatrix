package manager;

import entity.*;
import input_boundaries.PackInputBoundary;
import output_boundaries.AddOutputBoundary;
import output_boundaries.ChangeOutputBoundary;
import output_boundaries.SearchOutputBoundary;
import output_boundaries.SortOutputBoundary;
import use_case.LearnGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * A pack manager manages a collection of cards stored in a pack.
 * Responsibilities: change packName   and   add/search/sort/edit card
 */
public class PackManager extends Manager<Pack> implements Sort<Card>, PackInputBoundary {
    private Pack currPack = null; // The initial state where the user is not in any pack

    public PackManager() {
        super();
    }
    //TODO: edit idToItem: contain all packs of current user
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
     * Change a pack's name.
     * @param newPackName the new pack name
     * @param changeOutputBoudary the output boundary for getting the result of change (successful or not)
     */
    @Override
    public void changePackName(String newPackName, ChangeOutputBoundary changeOutputBoudary) {
        if (uniquePackname(newPackName)) {
            this.currPack.changeName(newPackName);
            changeOutputBoudary.setChangeResult(true);
            //TODO: save to database
        } else {
            changeOutputBoudary.setChangeResult(false);
        }
    }

    /**
     * A helper method for changePackName.
     * Check if the packName is unique.
     * @param newPackName the pack name to be checked
     * @return true if it is unique; false otherwise
     */
    private boolean uniquePackname(String newPackName) {
        for (Pack p: this.idToItem.values()) {
            if (newPackName.equals(p.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add a new card into current pack.
     *
     */
    public void addCard(Card card, AddOutputBoundary AddOutputBoundary) {
        try {
            this.currPack.addCard(card);
            AddOutputBoundary.presentAddSuccessView();
        } catch (Exception e) {
            AddOutputBoundary.presentAddFailView();
        }
    }

    /**
     * Delete a specific card in the current pack.
     *
     */
    public void deleteCard(Card card) {
        this.currPack.deleteCard(card);
    }

    /**
     * Users can search cards by card's term and definition.
     * Return an arraylist of cards that contain (not necessarily equal to) keyword.
     * @param keyword  the term that the user searches
     * @param searchOutputBoundary a search output boundary for getting the search result.
     * @return      an arraylist of cards that contain keyword
     */
    public ArrayList<Card> searchCard(String keyword, SearchOutputBoundary<Card> searchOutputBoundary) {
        ArrayList<Card> lst = new ArrayList<>();
        for (Card c: this.currPack.getCards()) {
            if (c.getTerm().contains(keyword) || c.getDefinition().contains(keyword)) {
                lst.add(c);
            }
        }
        searchOutputBoundary.setSearchResult(lst);
        return lst;
    }

    /**
     * Return a card list sorted by date added: oldest to newest.
     *
     * @return an arraylist of sorted cards
     * @param sortOutputBoundary a sort output boundary for getting the sorted output.
     */
    public ArrayList<Card> sortOldToNew(SortOutputBoundary<Card> sortOutputBoundary) {
        ArrayList<Card> lst = this.currPack.getCards();
        sortOutputBoundary.setSearchResult(lst);
        return lst;
    }

    /**
     * Return a card list sorted by date added: newest to oldest
     *
     * @return an arraylist of sorted cards
     * @param sortOutputBoundary a sort output boundary for getting the sorted output.
     */
    public ArrayList<Card> sortNewToOld(SortOutputBoundary<Card> sortOutputBoundary) {
        ArrayList<Card> lst = new ArrayList<>(this.currPack.getCards());
        Collections.reverse(lst);
        sortOutputBoundary.setSearchResult(lst);
        return lst;
    }

    /**
     * Return a card list sorted by cards' terms' alphabetical order: a - z.
     *
     * @return an arraylist of sorted cards
     * @param sortOutputBoundary a sort output boundary for getting the sorted output.
     */
    public ArrayList<Card> sortAtoZ(SortOutputBoundary<Card> sortOutputBoundary) {
        ArrayList<Card> lst = new ArrayList<>(this.currPack.getCards());
        lst.sort(new AlphabetComparator());
        sortOutputBoundary.setSearchResult(lst);
        return lst;
    }

    /**
     * Return a card list sorted by cards' terms' alphabetical order: z - a.
     *
     * @return an arraylist of sorted cards
     * @param sortOutputBoundary a sort output boundary for getting the sorted output.
     */
    public ArrayList<Card> sortZtoA(SortOutputBoundary<Card> sortOutputBoundary) {
        ArrayList<Card> lst = new ArrayList<>(this.currPack.getCards());
        lst.sort(new AlphabetComparator().reversed());
        sortOutputBoundary.setSearchResult(lst);
        return lst;
    }


    /**
     * Return a card list sorted by cards' proficiency: low to high.
     *
     * @return an arraylist of sorted cards
     */
    public ArrayList<Card> sortProLowToHigh() {
        ArrayList<Card> lst = new ArrayList<>(this.currPack.getCards());
        lst.sort(new ProficiencyComparator());
        return lst;
    }

    /**
     * Return a card list sorted by cards' proficiency: high to low.
     * @return an arraylist of sorted cards
     */
    public ArrayList<Card> sortProHighToLow() {
        ArrayList<Card> lst = new ArrayList<>(this.currPack.getCards());
        lst.sort(new ProficiencyComparator().reversed());
        return lst;
    }

    /**
     * Return a card list sorted in random order.
     * @param sortOutputBoundary a sort output boundary for getting the sorted output.
     * @return an arraylist of randomly sorted cards
     */
    public ArrayList<Card> sortRandom(SortOutputBoundary<Card> sortOutputBoundary) {
        ArrayList<Card> lst = new ArrayList<>(this.currPack.getCards());
        Collections.shuffle(lst);
        sortOutputBoundary.setSearchResult(lst);
        return lst;
    }


    private static class AlphabetComparator implements Comparator<Card> {
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

    private static class ProficiencyComparator implements Comparator<Card> {
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
