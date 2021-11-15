package interface_adapter.presenters;

import java.util.ArrayList;

public class SortPresenter<T> implements use_case.output_boundaries.SortOutputBoundary<T> {
    private ArrayList<T> sortResult;

    @Override
    public void setSearchResult(ArrayList<T> result) {
        this.sortResult = result;
    }

    @Override
    public void presentSearchResult() {
        for (T object : this.sortResult) {
            System.out.println(object);
        }
    }
}
