package use_case.input_boundaries;

import interface_adapter.gateway.IDataInOut;
import use_case.output_boundaries.DatabaseErrorOutputBoundary;

public interface ManagerInputBoundary {
    void write(IDataInOut dataInOut, DatabaseErrorOutputBoundary databaseErrorOutputBoundary);

    void write(String oldName, IDataInOut dataInOut, DatabaseErrorOutputBoundary databaseErrorOutputBoundary);

    void delete(IDataInOut dataInOut, DatabaseErrorOutputBoundary databaseErrorOutputBoundary);
}
