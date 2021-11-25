package interface_adapter.Controller;

import entity.ProgramState;
import interface_adapter.gateway.IDataInOut;
import use_case.input_boundaries.PackInputBoundary;
import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.DatabaseErrorOutputBoundary;

import java.io.IOException;

/**
 * A package interface_adapter.Controller that can create/change packname   and   add/delete/search/sort card in a pack.
 */
public class PackController {
    private final PackInputBoundary packIB;
    private final DatabaseErrorOutputBoundary databaseErrorOutputBoundary;

    public PackController(PackInputBoundary packIB, DatabaseErrorOutputBoundary databaseErrorOutputBoundary) {
        this.packIB = packIB;
        this.databaseErrorOutputBoundary = databaseErrorOutputBoundary;
    }

    /**
     * Create and return a new pack with specified pack name.
     *
     * @param packName The name of the pack
     */
    public void addNewPack(String packName, AddOutputBoundary addOutputBoundary) {
        if (this.packIB.addNewPack(packName, addOutputBoundary)) {
            this.packIB.write(databaseErrorOutputBoundary);
        }
    }

    /**
     * Change a pack's name with specified pack name.
     *
     * @param newPackName The name of the pack
     */
    public void changePackName(String oldPackName, String newPackName, ChangeOutputBoundary changeOutputBoudary) {
        if (this.packIB.changePackName(newPackName, changeOutputBoudary)) {
            this.packIB.write(oldPackName, databaseErrorOutputBoundary);
        }
    }

    public void deletePack(String packName) {
        if (this.packIB.deletePack(packName)) {
            this.packIB.archive(databaseErrorOutputBoundary);
        }
    }
//    /**
//     * Getter for the current pack the user is in.
//     *
//     * @return the current pack.
//     */
//    public Pack getCurrPack() {
//        return this.packIB.getCurrPack();
//    }
//
//    /**
//     * Change to the current pack the user is in.
//     */
//    public void setCurrPack(Pack pack) {
//        this.packIB.setCurrPack(pack);
//    }

}
