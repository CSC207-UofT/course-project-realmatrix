package interface_adapter.presenters;

import use_case.output_boundaries.SortSearchCardOutputBoundary;

public class SortSearchCardPresenter implements SortSearchCardOutputBoundary {
    private String[][] sortResult;

    /**
     * setter of sortResult
     * @param result the set value of result.
     */
    @Override
    public void setSortSearchResult(String[][] result) {
        this.sortResult = result;
    }

    /**
     * getter of sort Result
     * @return the arraylist of card term and definition.
     */
    @Override
    public String[][] getSortSearchResult(){
        return this.sortResult;
    }

//    @Override
//    public void presentSortResult() {
//        for (String[] arr : this.sortResult) {
//            System.out.println(arr[0]); // arr[0] is cardTerm
//            System.out.println(arr[1]); // arr[1] is cardDef
//        }
//    }
}
