package interface_adapter.presenters;

import use_case.output_boundaries.SortPackOutputBoundary;

import java.util.ArrayList;

public class SortPackPresenter implements SortPackOutputBoundary {
    private ArrayList<String> sortResult;

    /**
     * setter of sortResult
     * @param result the set value of result.
     */
    @Override
    public void setSortResult(ArrayList<String> result) {
        this.sortResult = result;
    }

    /**
     * getter of sort Result
     * @return the arraylist of card term and definition.
     */
    @Override
    public ArrayList<String> getSortResult() {
        return this.sortResult;
    }
}
