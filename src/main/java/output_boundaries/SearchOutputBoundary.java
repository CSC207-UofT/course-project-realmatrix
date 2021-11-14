package output_boundaries;

import java.util.ArrayList;

public interface SearchOutputBoundary<T> {
    void setSearchResult(ArrayList<T> result);

    void presentSearchResult();
}
