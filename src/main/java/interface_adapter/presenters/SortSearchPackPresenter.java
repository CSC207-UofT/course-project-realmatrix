package interface_adapter.presenters;

import use_case.output_boundaries.SortSearchPackOutputBoundary;

import java.util.ArrayList;

/**
 * A presenter for presenting the result of sorting/searching packs.
 */
public class SortSearchPackPresenter implements SortSearchPackOutputBoundary {
    private ArrayList<String> sortSearchResult;

    /**
     * setter of sortResult
     *
     * @param result the set value of result.
     */
    @Override
    public void setSortSearchResult(ArrayList<String> result) {
        this.sortSearchResult = result;
    }

    /**
     * getter of sort Result
     *
     * @return the arraylist of card term and definition.
     */
    @Override
    public ArrayList<String> getSortSearchResult() {
        return this.sortSearchResult;
    }
}
