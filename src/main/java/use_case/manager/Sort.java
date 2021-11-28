package use_case.manager;

import use_case.output_boundaries.SortSearchCardOutputBoundary;

public interface Sort {
    // Sort according to date added, from oldest to newest.
    void sortOldToNew(SortSearchCardOutputBoundary sortOutputBoundary);

    // Sort in alphabetical order (a-z), ignore case difference.
    void sortAtoZ(SortSearchCardOutputBoundary sortSearchCardOutputBoundary);

    // Sort in alphabetical order (z-a), ignore case difference.
//    void sortZtoA(SortSearchCardOutputBoundary sortCardOutputBoundary);

    // Sort according to date added, from newest to oldest.
//    void sortNewToOld(SortSearchCardOutputBoundary<T> sortOutputBoundary);
}
