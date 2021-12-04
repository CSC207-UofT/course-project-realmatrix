package use_case.output_boundaries;

import java.util.ArrayList;

/**
 * An output boundary for the result of sorting/searching packs.
 */
public interface SortSearchPackOutputBoundary {
    /**
     * Setter for sorting/searching result.
     * @param result An arraylist that contains the sorted/searched pack names.
     */
    void setSortSearchResult(ArrayList<String> result);

    /**
     * Getter for sorting/searching result.
     * @return  an arraylist that contains the sorted/searched pack names.
     */
    ArrayList<String> getSortSearchResult();
}
