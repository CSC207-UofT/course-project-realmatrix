package use_case.output_boundaries;

import java.util.ArrayList;

public interface SearchCardOutputBoundary {
    void setSearchResult(ArrayList<String[]> result);

    ArrayList<String[]> getSearchResult();

//    void presentSearchResult();
}
