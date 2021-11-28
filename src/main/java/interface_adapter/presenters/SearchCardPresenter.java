package interface_adapter.presenters;

import use_case.output_boundaries.SearchCardOutputBoundary;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A presenter for search result.
 */
public class SearchCardPresenter implements SearchCardOutputBoundary {
    private ArrayList<String[]> searchResult;

    /**
     * setter of search result.
     * @param result the searched result
     */
    @Override
    public void setSearchResult(ArrayList<String[]> result) {
        this.searchResult = result;
    }

    /**
     * Getter of search result.
     * @return
     */
    @Override
    public ArrayList<String[]> getSearchResult(){
        return this.searchResult;
    }
}
