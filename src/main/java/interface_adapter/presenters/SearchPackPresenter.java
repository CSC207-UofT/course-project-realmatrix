package interface_adapter.presenters;

import use_case.output_boundaries.SearchPackOutputBoundary;

import java.util.ArrayList;

public class SearchPackPresenter implements SearchPackOutputBoundary {
    private ArrayList<String> result;

    @Override
    public void setSearchResult(ArrayList<String> result) {
        this.result = result;
    }

    @Override
    public ArrayList<String> getSearchResult() {
        return result;
    }
}
