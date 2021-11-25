package interface_adapter.presenters;

import use_case.output_boundaries.SearchCardOutputBoundary;

import java.util.HashMap;

/**
 * A presenter for search result.
 */
public class SearchCardPresenter implements SearchCardOutputBoundary {
    private HashMap<String, String> searchResult;

    /**
     * setter of search result.
     * @param result
     */
    @Override
    public void setSearchResult(HashMap<String, String> result) {
        this.searchResult = result;
    }

    /**
     * Getter of search result.
     */
    @Override
    public HashMap<String, String> getSearchResult(){
        return this.searchResult;
    }

    @Override
    public void presentSearchResult() {
        for (String cardTerm : searchResult.keySet()) {
            System.out.println(cardTerm);
            System.out.println(searchResult.get(cardTerm));
        }
    }
}
