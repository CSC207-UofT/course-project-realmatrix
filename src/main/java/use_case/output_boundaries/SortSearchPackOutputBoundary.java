package use_case.output_boundaries;

import java.util.ArrayList;

public interface SortSearchPackOutputBoundary {
    void setSortSearchResult(ArrayList<String> result);

    ArrayList<String> getSortSearchResult();
}
