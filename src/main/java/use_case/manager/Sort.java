package use_case.manager;

/**
 * A sort Interface which is shared between Card/Pack Manager.
 */
public interface Sort<T> {
    // Sort according to date added, from oldest to newest.
    void sortOldToNew(T sortOutputBoundary);

    // Sort in alphabetical order (a-z), ignore case difference.
    void sortAtoZ(T sortOutputBoundary);
}
