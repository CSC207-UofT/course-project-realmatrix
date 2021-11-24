package interface_adapter.presenters;

import use_case.output_boundaries.SearchCardOutputBoundary;

import java.util.HashMap;

/**
 * A presenter for search result.
 */
public class SearchCardCardPresenter implements SearchCardOutputBoundary {
    private HashMap<String, String> searchResult;

    @Override
    public void setSearchResult(HashMap<String, String> result) {
        this.searchResult = result;
    }

    @Override
    public void presentSearchResult() {
        for (String cardTerm : searchResult.keySet()) {
            System.out.println(cardTerm);
            System.out.println(searchResult.get(cardTerm));
        }
    }
}
