package use_case.manager;

import use_case.output_boundaries.SortCardOutputBoundary;

public interface Sort {
    // Sort according to date added, from oldest to newest.
    void sortOldToNew(SortCardOutputBoundary sortOutputBoundary);

    // Sort in alphabetical order (a-z), ignore case difference.
    void sortAtoZ(SortCardOutputBoundary sortCardOutputBoundary);

    void sortRandom(SortCardOutputBoundary sortCardOutputBoundary);

    // Sort in alphabetical order (z-a), ignore case difference.
//    void sortZtoA(SortCardOutputBoundary sortCardOutputBoundary);

    // Sort according to date added, from newest to oldest.
//    void sortNewToOld(SortCardOutputBoundary<T> sortOutputBoundary);
}
