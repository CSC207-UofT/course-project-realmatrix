package use_case.input_boundaries;

import entity.Pack;
import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.SortCardOutputBoundary;

public interface PackInputBoundary extends ManagerInputBoundary {
    boolean addNewPack(String packName, AddOutputBoundary addOutputBoundary);

    boolean changePackName(String newPackName, ChangeOutputBoundary changePackNameOB);

    // These two may not be needed if we have observer for tracking program state
    Pack getCurrPack();

    void setCurrPack(Pack pack);

}
