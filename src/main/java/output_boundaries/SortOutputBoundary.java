package output_boundaries;

import java.util.ArrayList;

public interface SortOutputBoundary<T> {
    void setSearchResult(ArrayList<T> result);

    void presentSearchResult();
}
