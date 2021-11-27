package use_case.input_boundaries;

import interface_adapter.presenters.SearchPackPresenter;
import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.SearchPackOutputBoundary;
import use_case.output_boundaries.SortPackOutputBoundary;

public interface PackInputBoundary extends ManagerInputBoundary {
    boolean addNewPack(String packName, AddOutputBoundary addOutputBoundary);

    boolean deletePack(String packName);

    boolean changePackName(String newPackName, ChangeOutputBoundary changePackNameOB);

    void searchPack(String packName, SearchPackOutputBoundary searchPackOutputBoundary);

    void sortOldToNew(SortPackOutputBoundary sortPackOutputBoundary);

    // These two may not be needed if we have observer for tracking program state
}
