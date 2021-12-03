package interface_adapter.presenters;

import use_case.output_boundaries.SortSearchPackOutputBoundary;

import java.util.ArrayList;

public class SortSearchPackPresenter implements SortSearchPackOutputBoundary {
    private ArrayList<String> sortSearchResult;

    /**
     * setter of sortResult
     * @param result the set value of result.
     */
    @Override
    public void setSortSearchResult(ArrayList<String> result) {
        this.sortSearchResult = result;
    }

    /**
     * getter of sort Result
     * @return the arraylist of card term and definition.
     */
    @Override
    public ArrayList<String> getSortSearchResult() {
        return this.sortSearchResult;
    }
}
