package use_case.manager;

import use_case.output_boundaries.SortCardOutputBoundary;

public interface Sort {
    // Sort in alphabetical order (a-z), ignore case difference.
    void sortAtoZ(SortCardOutputBoundary sortCardOutputBoundary);

    // Sort in alphabetical order (z-a), ignore case difference.
    void sortZtoA(SortCardOutputBoundary sortCardOutputBoundary);

//    // Sort according to date added, from newest to oldest.
//    ArrayList<T> sortNewToOld(SortCardOutputBoundary<T> sortOutputBoundary);
//
//    // Sort according to date added, from oldest to newest.
//    ArrayList<T> sortOldToNew(SortCardOutputBoundary<T> sortOutputBoundary);

    void sortRandom(SortCardOutputBoundary sortCardOutputBoundary);
}
