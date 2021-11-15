package use_case.output_boundaries;

import java.util.ArrayList;

public interface SortOutputBoundary<T> {
    void setSearchResult(ArrayList<T> result);

    void presentSearchResult();
}
