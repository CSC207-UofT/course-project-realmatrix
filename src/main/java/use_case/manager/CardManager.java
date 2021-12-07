package use_case.manager;

import entity.Card;
import use_case.input_boundaries.CardInputBoundary;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.SortSearchCardOutputBoundary;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * A CardManager manages all cards in current pack.
 */
public class CardManager extends Manager<Card> implements CardInputBoundary {
    // Note items for this manager is a map <cardTerm: Card>

    public CardManager(ProgramStateInputBoundary programStateInputBoundary) {
        super(programStateInputBoundary);
        this.currItem = this.programStateInputBoundary.getCurrCard();
        this.items = this.programStateInputBoundary.getCurrPack().getCardMap();
    }

    /**
     * Create a new card with specified term and definition.
     * If the card term already exists, adding fails. Otherwise, adding succeeds.
     *
     * @param term              The term of the card
     * @param definition        The definition of the term
     * @param addOutputBoundary an output boundary for showing the result of adding new pack
     * @return true if the pack is successfully added; false otherwise
     */
    public boolean addNewCard(String term, String definition,
                              AddOutputBoundary addOutputBoundary) {
        if (!this.items.containsKey(term)) { // no card has such term, adding is valid
            Card c = new Card(term, definition);
            this.items.put(term, c);
            this.currItem = c;
            programStateInputBoundary.getCurrPack().addCard(c);
            addOutputBoundary.setAddResult(true);
            return true;
        }
        addOutputBoundary.setAddResult(false);
        return false;
    }

    /**
     * delete an existing card wit specified card term.
     *
     * @param term: the term of the card the user wants to delete
     * @return true iff we successfully deleted an item
     */
    public boolean deleteCard(String term) {
        currItem = this.items.get(term);
        if (currItem != null) { // We have the item to be deleted
            items.remove(term);
            programStateInputBoundary.getCurrPack().deleteCard(currItem);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Change the card's term to a new term specified by user.
     * If the new term doesn't exist, change succeeds. Otherwise, change fails.
     *
     * @param newTerm              the new term the card should change to
     * @param changeOutputBoundary the output boundary for getting whether change is successful or not
     * @return true if the user successfully changes the card term; false otherwise
     */
    public boolean changeCardTerm(String newTerm, ChangeOutputBoundary changeOutputBoundary) {
        Card card = searchItem(newTerm);
        if (card == null) { // no card has such term, changing is valid
            String oldName = currItem.getTerm();
            this.items.remove(oldName);            // Remove card with old name
            currItem.setTerm(newTerm);
            this.items.put(newTerm, currItem);     // Add card with new name
            changeOutputBoundary.setChangeResult(true);
            return true;
        } else {
            changeOutputBoundary.setChangeResult(false);
            return false;
        }
    }

    /**
     * Change the card's definition to a new definition specified by user.
     * This always succeeds.
     *
     * @param newDefinition the new definition the card should change to
     */
    public void changeCardDefinition(String newDefinition) {
        this.currItem.setDefinition(newDefinition);
    }

    /**
     * Users can search cards by card's term and definition (not necessarily equal to) keyword.
     *
     * @param keyword                      the term that the user searches
     * @param sortSearchCardOutputBoundary a search output boundary for getting the search result.
     */
    public void searchCard(String keyword, SortSearchCardOutputBoundary sortSearchCardOutputBoundary) {
        ArrayList<Card> cardList = programStateInputBoundary.getCurrPack().getCardList();
        ArrayList<Card> result = new ArrayList<>();
        for (Card c : cardList) {
            if (c.getTerm().toLowerCase().contains(keyword.toLowerCase())
                    || c.getDefinition().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(c);
            }
        }
        presentSortSearchResult(result, sortSearchCardOutputBoundary);
    }

    /**
     * Sort a card list by date added: oldest to newest.
     *
     * @param sortSearchCardOutputBoundary a sort output boundary for getting the sorted output.
     */
    public void sortOldToNew(SortSearchCardOutputBoundary sortSearchCardOutputBoundary) {
        presentSortSearchResult(programStateInputBoundary.getCurrPack().getCardList(), sortSearchCardOutputBoundary);
    }

    /**
     * Sort cards by cards' terms' alphabetical order: a - z.
     *
     * @param sortSearchCardOutputBoundary a sort output boundary for getting the sorted output.
     */
    public void sortAtoZ(SortSearchCardOutputBoundary sortSearchCardOutputBoundary) {
        ArrayList<String> cardTermList = new ArrayList<>(this.items.keySet());
        cardTermList.sort(String::compareToIgnoreCase);
        ArrayList<Card> sorted = new ArrayList<>();
        for (String term : cardTermList) {
            sorted.add(this.items.get(term));
        }
        presentSortSearchResult(sorted, sortSearchCardOutputBoundary);
    }


    /**
     * Sort a card list by cards' proficiency: low to high.
     *
     * @param sortSearchCardOutputBoundary an output boundary that gets the result of sorted cards
     */
    public void sortProLowToHigh(SortSearchCardOutputBoundary sortSearchCardOutputBoundary) {
        ArrayList<Card> sorted = new ArrayList<>(this.items.values());
        sorted.sort(new ProficiencyComparator());
        presentSortSearchResult(sorted, sortSearchCardOutputBoundary);
    }

    /**
     * Helper method for present sorted cards.
     *
     * @param sorted                       An arraylist of cards that are sorted according to some algorithm.
     * @param sortSearchCardOutputBoundary a sort output boundary that receives the result of sorted cards.
     */
    private void presentSortSearchResult(ArrayList<Card> sorted, SortSearchCardOutputBoundary sortSearchCardOutputBoundary) {
        String[][] presentResult = new String[sorted.size()][2];
        for (int i = 0; i < sorted.size(); i++) {
            String term = sorted.get(i).getTerm();
            String def = sorted.get(i).getDefinition();
            presentResult[i] = new String[]{term, def};
        }
        sortSearchCardOutputBoundary.setSortSearchResult(presentResult);
    }

    private static class ProficiencyComparator implements Comparator<Card> {
        /**
         * Compare 2 cards according to their terms' proficiency.
         * <p>
         * Return a negative integer if c1 < c2,
         * zero if c1 == c2,
         * a positive integer if c1 > c2
         * in terms of proficiency.
         *
         * @param c1 the first card
         * @param c2 the second card
         * @return a negative integer, zero, or a positive integer
         * if c1 < c2, c1 == c2, or c1 > c2 in terms of proficiency.
         */
        @Override
        public int compare(Card c1, Card c2) {
            return c1.getProficiency() - c2.getProficiency();
        }
    }
}
