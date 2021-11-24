package use_case.manager;

import entity.Card;
import interface_adapter.gateway.IDataInOut;
import use_case.input_boundaries.CardInputBoundary;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.SearchCardOutputBoundary;
import use_case.output_boundaries.SortCardOutputBoundary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * A CardManager contains all cards in the system.
 */
public class CardManager extends Manager<Card> implements CardInputBoundary {
    private Card currCard; // Initialize to the state where the user is not in a card
    // Note items for this manager is a map <cardTerm: Card>

    public CardManager(IDataInOut dataInOut, ProgramStateInputBoundary programStateInputBoundary) {
        super(dataInOut, programStateInputBoundary);
        this.currCard = this.programStateInputBoundary.getCurrCard();
        this.items = this.programStateInputBoundary.getCurrPack().getCards();
    }

    /**
     * Create a new card with specified term and definition.
     * If the card term already exists, adding fails. Otherwise, adding succeeds.
     *
     * @param term       The term of the card
     * @param definition The definition of the term
     * @param addOutputBoundary an output boundary for showing the result of adding new pack
     * @return true if the pack is successfully added; false otherwise
     */
    public boolean addNewCard(String term, String definition, AddOutputBoundary addOutputBoundary) {
        if (!this.items.containsKey(term)) { // no card has such term, adding is valid
            Card c = new Card(term, definition);
            this.items.put(term, c);
            programStateInputBoundary.getCurrPack().addCard(c);
            addOutputBoundary.presentAddSuccessView();
            return true;
        }
        addOutputBoundary.presentAddFailView();
        return false;
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
        if (!this.items.containsKey(newTerm)) { // no card has such term, changing is valid
            this.currCard.setTerm(newTerm);
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
        this.currCard.setDefinition(newDefinition);
    }

//    /**
//     * Increase the proficiency of the card by 1.
//     */
//    public void increaseProficiency() {
//        this.currCard.setProficiency(Math.min(this.currCard.getProficiency() + 1, 5));
//    }
//
//    /**
//     * Decrease the proficiency of the card by 1.
//     */
//    public void decreaseProficiency() {
//        this.currCard.setProficiency(Math.max(this.currCard.getProficiency() - 1, 1));
//    }

    /**
     * Users can search cards by card's term and definition (not necessarily equal to) keyword.
     *  @param keyword              the term that the user searches
     * @param searchCardOutputBoundary a search output boundary for getting the search result.
     */
    public void searchCard(String keyword, SearchCardOutputBoundary searchCardOutputBoundary) {
        HashMap<String, String> termToDef = new HashMap<>();
        for (Card c : this.items.values()) {
            if (c.getTerm().contains(keyword) || c.getDefinition().contains(keyword)) {
                termToDef.put(c.getTerm(), c.getDefinition());
            }
        }
        searchCardOutputBoundary.setSearchResult(termToDef);
    }
    /**
     * Sort cards by cards' terms' alphabetical order: a - z.
     *
     * @param sortCardOutputBoundary a sort output boundary for getting the sorted output.
     */
    public void sortAtoZ(SortCardOutputBoundary sortCardOutputBoundary) {
        ArrayList<Card> sorted = new ArrayList<>(this.items.values());
        sorted.sort(new AlphabetComparator());
        presentSortResult(sorted, sortCardOutputBoundary);
    }

    /**
     * Return a card list sorted by cards' terms' alphabetical order: z - a.
     *
     * @param sortCardOutputBoundary a sort output boundary for getting the sorted output.
     */
    public void sortZtoA(SortCardOutputBoundary sortCardOutputBoundary) {
        ArrayList<Card> sorted = new ArrayList<>(this.items.values());
        sorted.sort(new AlphabetComparator().reversed());
        presentSortResult(sorted, sortCardOutputBoundary);
    }


    /**
     * Return a card list sorted by cards' proficiency: low to high.
     *
     * @return an arraylist of sorted cards
     */
    public ArrayList<Card> sortProLowToHigh() {
        ArrayList<Card> sorted = new ArrayList<>(this.items.values());
        sorted.sort(new ProficiencyComparator());
        return sorted;
    }

    /**
     * Return a card list sorted by cards' proficiency: high to low.
     *
     * @return an arraylist of sorted cards
     */
    public ArrayList<Card> sortProHighToLow() {
        ArrayList<Card> sorted = new ArrayList<>(this.items.values());
        sorted.sort(new ProficiencyComparator().reversed());
        return sorted;
    }

    /**
     * Return a card list sorted in random order.
     *
     * @param sortCardOutputBoundary a sort output boundary for getting the sorted output.
     * @return an arraylist of randomly sorted cards
     */
    public ArrayList<Card> sortRandom(SortCardOutputBoundary sortCardOutputBoundary) {
        ArrayList<Card> sorted = new ArrayList<>(this.items.values());
        Collections.shuffle(sorted);
        presentSortResult(sorted, sortCardOutputBoundary);
        return sorted;
    }

    /**
     * Helper method for present sorted cards.
     * @param sorted An arraylist of cards that are sorted according to some algorithm.
     * @param sortCardOutputBoundary a sort output boundary that receives the result of sorted cards.
     */
    private void presentSortResult(ArrayList<Card> sorted, SortCardOutputBoundary sortCardOutputBoundary) {
        ArrayList<String[]> presentResult = new ArrayList<>();
        for (Card c : sorted) {
            String[] termDefList = new String[]{c.getTerm(), c.getDefinition()};
            presentResult.add(termDefList);
        }
        sortCardOutputBoundary.setSearchResult(presentResult);
    }


    private static class AlphabetComparator implements Comparator<Card> {
        /**
         * Compare 2 cards according to their terms' alphabetical order (ignore case).
         * <p>
         * Return a negative integer if c1 < c2,
         * zero if c1 == c2,
         * a positive integer if c1 > c2
         * in terms of alphabetical order (ignore cases) of their terms.
         *
         * @param c1 the first card
         * @param c2 the second card
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

    /**
     * Getter for the current card the user is in.
     *
     * @return the current card.
     */
    public Card getCurrCard() {
        return this.currCard;
    }

    /**
     * Change to the current card the user is in.
     */
    public void setCurrCard(Card card) {
        this.currCard = card;
    }
}
