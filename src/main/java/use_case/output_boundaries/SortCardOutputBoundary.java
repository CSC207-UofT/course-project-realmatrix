package use_case.output_boundaries;

import java.util.ArrayList;

public interface SortCardOutputBoundary {
    void setSortResult(ArrayList<String[]> result);

    ArrayList<String[]> getSortResult();

//    void presentSortResult();
}
