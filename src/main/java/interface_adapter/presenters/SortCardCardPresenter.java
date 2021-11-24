package interface_adapter.presenters;

import use_case.output_boundaries.SortCardOutputBoundary;

import java.util.ArrayList;
import java.util.HashMap;

public class SortCardCardPresenter implements SortCardOutputBoundary {
    private ArrayList<String[]> sortResult;

    /**
     * setter of sortResult
     * @param result the set value of result.
     */
    @Override
    public void setSearchResult(ArrayList<String[]> result) {
        this.sortResult = result;
    }

    /**
     * getter of sort Result
     * @return the arraylist of card term and definition.
     */
    @Override
    public ArrayList<String[]> getSortResult(){
        return this.sortResult;
    }

    @Override
    public void presentSearchResult() {
        for (String[] arr : this.sortResult) {
            System.out.println(arr[0]); // arr[0] is cardTerm
            System.out.println(arr[1]); // arr[1] is cardDef
        }
    }
}
