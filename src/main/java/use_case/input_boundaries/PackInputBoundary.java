package use_case.input_boundaries;

import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.SortSearchPackOutputBoundary;

public interface PackInputBoundary extends ManagerInputBoundary {
    boolean addNewPack(String packName, AddOutputBoundary addOutputBoundary);

    boolean deletePack(String packName);

    boolean changePackName(String newPackName, ChangeOutputBoundary changePackNameOB);

    void searchPack(String packName, SortSearchPackOutputBoundary sortSearchPackOutputBoundary);

    void sortOldToNew(SortSearchPackOutputBoundary sortSearchPackOutputBoundary);

    void sortAtoZ(SortSearchPackOutputBoundary sortSearchPackOutputBoundary);

}
