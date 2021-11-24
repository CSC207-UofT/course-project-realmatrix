package use_case.input_boundaries;

import use_case.output_boundaries.DatabaseErrorOutputBoundary;

public interface ManagerInputBoundary {
    public void write(DatabaseErrorOutputBoundary databaseErrorOutputBoundary);
    public void write(String oldName, DatabaseErrorOutputBoundary databaseErrorOutputBoundary);
    public void archive(DatabaseErrorOutputBoundary databaseErrorOutputBoundary);
}
