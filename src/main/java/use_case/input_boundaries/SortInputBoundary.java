package use_case.input_boundaries;

/**
 * A sort Interface which will be extended by CardInputBoundary and PackInputBoundary,
 * so that this interface is shared between Card/Pack Manager.
 */
public interface SortInputBoundary<T> {
    // Sort according to date added, from oldest to newest.
    void sortOldToNew(T sortOutputBoundary);

    // Sort in alphabetical order (a-z), ignore case difference.
    void sortAtoZ(T sortOutputBoundary);
}
