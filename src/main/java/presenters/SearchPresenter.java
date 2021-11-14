package presenters;

import java.util.ArrayList;

/**
 * A presenter for search result.
 */
public class SearchPresenter<T> implements output_boundaries.SearchOutputBoundary<T> {
    private ArrayList<T> searchResult;

    @Override
    public void setSearchResult(ArrayList<T> result) {
        this.searchResult = result;
    }

    @Override
    public void presentSearchResult() {
        for (T object: this.searchResult) {
            System.out.println(object);
        }
    }
}
