package use_case.manager;

import entity.Card;
import entity.Pack;
import interface_adapter.gateway.IDataInOut;
import use_case.input_boundaries.PackInputBoundary;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.ChangeOutputBoundary;

/**
 * A pack manager manages the current user's packs.
 */
public class PackManager extends Manager<Pack> implements Sort<Card>, PackInputBoundary {
    // Note items for this manager is a map <packName: Pack>

    public PackManager(IDataInOut dataInOut, ProgramStateInputBoundary programStateInputBoundary) {
        super(dataInOut, programStateInputBoundary);
        this.currItem = this.programStateInputBoundary.getCurrPack();
        this.items = this.programStateInputBoundary.getCurrUser().getPackages();
    }

    /**
     * Create a new pack with specified pack name for the current user.
     * If the pack name already exists, adding fails. Otherwise, adding succeeds.
     *
     * @param packName The name of the pack
     * @param addOutputBoundary an output boundary for showing the result of adding new pack
     * @return true if the pack is successfully added; false otherwise
     */
    public boolean addNewPack(String packName, AddOutputBoundary addOutputBoundary) {
        if (!this.items.containsKey(packName)) { // no pack has such packname, adding is valid
            Pack p = new Pack(packName);
            this.items.put(packName, p);
            programStateInputBoundary.getCurrUser().addPackage(p);
            addOutputBoundary.presentAddSuccessView();
            return true;
        }
        addOutputBoundary.presentAddFailView();
        return false;
    }

    /**
     * Change a pack's name.
     * If the new name doesn't exist, change succeeds. Otherwise, change fails.
     *
     * @param newPackName         the new pack name
     * @param changeOutputBoundary the output boundary for getting the result of change (successful or not)
     * @return true if user successfully changed the pack name; false otherwise
     */
    @Override
    public boolean changePackName(String newPackName, ChangeOutputBoundary changeOutputBoundary) {
        if (!this.items.containsKey(newPackName)) { // there is no pack with newPackName
            this.currItem.changeName(newPackName);
            changeOutputBoundary.setChangeResult(true);
            return true;
        } else {
            changeOutputBoundary.setChangeResult(false);
            return false;
        }
    }
//
//    /**
//     * Add a new card into current pack.
//     * Return true if successfully added; false otherwise.
//     */
//    public boolean addCard(Card card, AddOutputBoundary AddOutputBoundary) {
//        try {
//            this.currItem.addCard(card);
//            AddOutputBoundary.presentAddSuccessView();
//            return true;
//        } catch (Exception e) {
//            AddOutputBoundary.presentAddFailView();
//            return false;
//        }
//    }
//
//    /**
//     * Delete a specific card in the current pack.
//     */
//    public void deleteCard(Card card) {
//        this.currItem.deleteCard(card);
//    }
//
//    /**
//     * Users can search cards by card's term and definition.
//     * Return an arraylist of cards that contain (not necessarily equal to) keyword.
//     *
//     * @param keyword              the term that the user searches
//     * @param searchOutputBoundary a search output boundary for getting the search result.
//     * @return an arraylist of cards that contain keyword
//     */
//    public ArrayList<Card> searchCard(String keyword, SearchCardOutputBoundary<Card> searchOutputBoundary) {
//        ArrayList<Card> lst = new ArrayList<>();
//        for (Card c : this.currItem.getCards()) {
//            if (c.getTerm().contains(keyword) || c.getDefinition().contains(keyword)) {
//                lst.add(c);
//            }
//        }
//        searchOutputBoundary.setSearchResult(lst);
//        return lst;
//    }
//
//    /**
//     * Return a card list sorted by date added: oldest to newest.
//     *
//     * @param sortOutputBoundary a sort output boundary for getting the sorted output.
//     * @return an arraylist of sorted cards
//     */
//    public ArrayList<Card> sortOldToNew(SortCardOutputBoundary<Card> sortOutputBoundary) {
//        ArrayList<Card> lst = this.currItem.getCards();
//        sortOutputBoundary.setSearchResult(lst);
//        return lst;
//    }
//
//    /**
//     * Return a card list sorted by date added: newest to oldest
//     *
//     * @param sortOutputBoundary a sort output boundary for getting the sorted output.
//     * @return an arraylist of sorted cards
//     */
//    public ArrayList<Card> sortNewToOld(SortCardOutputBoundary<Card> sortOutputBoundary) {
//        ArrayList<Card> lst = new ArrayList<>(this.currItem.getCards());
//        Collections.reverse(lst);
//        sortOutputBoundary.setSearchResult(lst);
//        return lst;
//    }
//
//    /**
//     * Return a card list sorted by cards' terms' alphabetical order: a - z.
//     *
//     * @param sortOutputBoundary a sort output boundary for getting the sorted output.
//     * @return an arraylist of sorted cards
//     */
//    public ArrayList<Card> sortAtoZ(SortCardOutputBoundary<Card> sortOutputBoundary) {
//        ArrayList<Card> lst = new ArrayList<>(this.currItem.getCards());
//        lst.sort(new AlphabetComparator());
//        sortOutputBoundary.setSearchResult(lst);
//        return lst;
//    }
//
//    /**
//     * Return a card list sorted by cards' terms' alphabetical order: z - a.
//     *
//     * @param sortOutputBoundary a sort output boundary for getting the sorted output.
//     * @return an arraylist of sorted cards
//     */
//    public ArrayList<Card> sortZtoA(SortCardOutputBoundary<Card> sortOutputBoundary) {
//        ArrayList<Card> lst = new ArrayList<>(this.currItem.getCards());
//        lst.sort(new AlphabetComparator().reversed());
//        sortOutputBoundary.setSearchResult(lst);
//        return lst;
//    }
//
//
//    /**
//     * Return a card list sorted by cards' proficiency: low to high.
//     *
//     * @return an arraylist of sorted cards
//     */
//    public ArrayList<Card> sortProLowToHigh() {
//        ArrayList<Card> lst = new ArrayList<>(this.currItem.getCards());
//        lst.sort(new ProficiencyComparator());
//        return lst;
//    }
//
//    /**
//     * Return a card list sorted by cards' proficiency: high to low.
//     *
//     * @return an arraylist of sorted cards
//     */
//    public ArrayList<Card> sortProHighToLow() {
//        ArrayList<Card> lst = new ArrayList<>(this.currItem.getCards());
//        lst.sort(new ProficiencyComparator().reversed());
//        return lst;
//    }
//
//    /**
//     * Return a card list sorted in random order.
//     *
//     * @param sortOutputBoundary a sort output boundary for getting the sorted output.
//     * @return an arraylist of randomly sorted cards
//     */
//    public ArrayList<Card> sortRandom(SortCardOutputBoundary<Card> sortOutputBoundary) {
//        ArrayList<Card> lst = new ArrayList<>(this.currItem.getCards());
//        Collections.shuffle(lst);
//        sortOutputBoundary.setSearchResult(lst);
//        return lst;
//    }
//
//
//    private static class AlphabetComparator implements Comparator<Card> {
//        /**
//         * Compare 2 cards according to their terms' alphabetical order (ignore case).
//         * <p>
//         * Return a negative integer if c1 < c2,
//         * zero if c1 == c2,
//         * a positive integer if c1 > c2
//         * in terms of alphabetical order (ignore cases) of their terms.
//         *
//         * @param c1 the first card
//         * @param c2 the second card
//         * @return a negative integer, zero, or a positive integer if
//         * if c1 < c2, c1 == c2, or c1 > c2 in terms of alphabetical order of terms.
//         */
//        @Override
//        public int compare(Card c1, Card c2) {
//            return c1.getTerm().compareToIgnoreCase(c2.getTerm());
//        }
//    }
//
//    private static class ProficiencyComparator implements Comparator<Card> {
//        /**
//         * Compare 2 cards according to their terms' proficiency.
//         * <p>
//         * Return a negative integer if c1 < c2,
//         * zero if c1 == c2,
//         * a positive integer if c1 > c2
//         * in terms of proficiency.
//         *
//         * @param c1 the first card
//         * @param c2 the second card
//         * @return a negative integer, zero, or a positive integer
//         * if c1 < c2, c1 == c2, or c1 > c2 in terms of proficiency.
//         */
//        @Override
//        public int compare(Card c1, Card c2) {
//            return c1.getProficiency() - c2.getProficiency();
//        }
//    }


}
