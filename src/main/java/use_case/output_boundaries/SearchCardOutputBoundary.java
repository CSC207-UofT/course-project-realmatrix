package use_case.output_boundaries;

import java.util.ArrayList;
import java.util.HashMap;

public interface SearchCardOutputBoundary {
    void setSearchResult(HashMap<String, String> result);

    HashMap getSearchResult();

    void presentSearchResult();
}
