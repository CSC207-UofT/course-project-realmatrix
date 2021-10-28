package manager;

import java.util.ArrayList;

public interface Sort<T> {
    /**
     * Sort in alphabetical order (a-z), ignore case difference.
     * @return An arraylist of T
     */
    ArrayList<T> sortAtoZ();

    /**
     * Sort in alphabetical order (z-a), ignore case difference.
     * @return An arraylist of T
     */
    ArrayList<T> sortZtoA();

    /**
     * Sort according to date added, from newest to oldest.
     * @return An arraylist of T
     */
    ArrayList<T> sortNewToOld();

    /**
     * Sort according to date added, from oldest to newest.
     * @return An arraylist of T
     */
    ArrayList<T> sortOldToNew();

}
