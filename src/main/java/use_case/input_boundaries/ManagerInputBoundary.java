package use_case.input_boundaries;

import use_case.output_boundaries.DatabaseErrorOutputBoundary;

public interface ManagerInputBoundary {
    void write(DatabaseErrorOutputBoundary databaseErrorOutputBoundary);
    void write(String oldName, DatabaseErrorOutputBoundary databaseErrorOutputBoundary);
    void delete(DatabaseErrorOutputBoundary databaseErrorOutputBoundary);
}
