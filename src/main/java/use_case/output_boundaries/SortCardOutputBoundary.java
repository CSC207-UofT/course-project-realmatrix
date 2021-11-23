package use_case.output_boundaries;

import java.util.ArrayList;
import java.util.HashMap;

public interface SortCardOutputBoundary {
    void setSearchResult(ArrayList<String[]> result);

    void presentSearchResult();
}
