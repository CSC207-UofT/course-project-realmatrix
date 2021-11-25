package use_case.input_boundaries;

import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.ChangeOutputBoundary;

public interface PackInputBoundary extends ManagerInputBoundary {
    boolean addNewPack(String packName, AddOutputBoundary addOutputBoundary);

    boolean deletePack(String packName);

    boolean changePackName(String newPackName, ChangeOutputBoundary changePackNameOB);

    // These two may not be needed if we have observer for tracking program state
}
