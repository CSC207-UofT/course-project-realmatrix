package interface_adapter.presenters;

import use_case.output_boundaries.SortSearchCardOutputBoundary;

/**
 * A presenter for the result of sorting/searching cards.
 */
public class SortSearchCardPresenter implements SortSearchCardOutputBoundary {
    private String[][] sortResult;

    /**
     * setter of sortResult
     *
     * @param result the set value of result.
     */
    @Override
    public void setSortSearchResult(String[][] result) {
        this.sortResult = result;
    }

    /**
     * getter of sort Result
     *
     * @return the arraylist of card term and definition.
     */
    @Override
    public String[][] getSortSearchResult() {
        return this.sortResult;
    }
}
