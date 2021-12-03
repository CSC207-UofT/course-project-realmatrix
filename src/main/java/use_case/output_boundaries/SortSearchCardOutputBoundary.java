package use_case.output_boundaries;

/**
 * An output boundary for getting the result of sorting or searching cards.
 */
public interface SortSearchCardOutputBoundary {
    /**
     * Setter for sort / search result.
     * @param result A nested array in the form of {{card1Term, card1Def}, {card2Term, card2Def}}
     */
    void setSortSearchResult(String[][] result);

    /**
     * Getter for sort / search result.
     * @return A nested array in the form of {{card1Term, card1Def}, {card2Term, card2Def}}
     */
    String[][] getSortSearchResult();

}
