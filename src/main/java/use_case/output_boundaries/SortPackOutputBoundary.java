package use_case.output_boundaries;

import java.util.ArrayList;

public interface SortPackOutputBoundary {
    void setSortResult(ArrayList<String> result);

    ArrayList<String> getSortResult();
}
