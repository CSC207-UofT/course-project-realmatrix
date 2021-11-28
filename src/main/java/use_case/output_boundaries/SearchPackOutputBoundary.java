package use_case.output_boundaries;

import java.util.ArrayList;

public interface SearchPackOutputBoundary {
    void setSearchResult(ArrayList<String> result);

    ArrayList<String> getSearchResult();
}
