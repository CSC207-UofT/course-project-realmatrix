package use_case.manager;

import output_boundaries.SortOutputBoundary;

import java.util.ArrayList;

public interface Sort<T> {
    // Sort in alphabetical order (a-z), ignore case difference.
    ArrayList<T> sortAtoZ(SortOutputBoundary<T> sortOutputBoundary);

    // Sort in alphabetical order (z-a), ignore case difference.
    ArrayList<T> sortZtoA(SortOutputBoundary<T> sortOutputBoundary);

    // Sort according to date added, from newest to oldest.
    ArrayList<T> sortNewToOld(SortOutputBoundary<T> sortOutputBoundary);

    // Sort according to date added, from oldest to newest.
    ArrayList<T> sortOldToNew(SortOutputBoundary<T> sortOutputBoundary);

    ArrayList<T> sortRandom(SortOutputBoundary<T> sortOutputBoundary);
}
