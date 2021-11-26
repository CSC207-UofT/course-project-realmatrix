package use_case.manager;

import use_case.input_boundaries.ManagerInputBoundary;
import use_case.output_boundaries.DatabaseErrorOutputBoundary;

public class ReadWriteController {
    private final ManagerInputBoundary managerInputBoundary;

    public ReadWriteController(ManagerInputBoundary managerInputBoundary) {
        this.managerInputBoundary = managerInputBoundary;
    }

    public void write(DatabaseErrorOutputBoundary databaseErrorOutputBoundary) {
        this.managerInputBoundary.write(databaseErrorOutputBoundary);
    }

    public void write(String oldName, DatabaseErrorOutputBoundary databaseErrorOutputBoundary) {
        this.managerInputBoundary.write(oldName, databaseErrorOutputBoundary);
    }
}
