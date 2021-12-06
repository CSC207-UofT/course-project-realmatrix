package interface_adapter.controller;

import interface_adapter.gateway.IDataInOut;
import use_case.input_boundaries.PackInputBoundary;
import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.DatabaseErrorOutputBoundary;
import use_case.output_boundaries.SortSearchPackOutputBoundary;

/**
 * A controller that can create/change packname   and   add/delete/search/sort packs.
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
    public void addNewPack(String packName, AddOutputBoundary addOutputBoundary, IDataInOut dataInOut) {
        if (this.packIB.addNewPack(packName, addOutputBoundary)) {
            this.packIB.write(dataInOut, databaseErrorOutputBoundary);
        }
    }

    /**
     * Change a pack's name with specified pack name.
     *
     * @param newPackName The name of the pack
     * @param dataInOut   the data access interface
     */
    public void changePackName(String oldPackName, String newPackName, IDataInOut dataInOut, ChangeOutputBoundary changeOutputBoundary) {
        if (this.packIB.changePackName(newPackName, changeOutputBoundary)) {
            this.packIB.write(oldPackName, dataInOut, databaseErrorOutputBoundary);
        }
    }

    /**
     * Search packs with specified pack name (ignore case).
     * All packs that contain (not necessarily equal) packName would be searched.
     *
     * @param str                      the text to be searched
     * @param searchPackOutputBoundary an output boundary that gets the searched result
     */
    public void searchPack(String str, SortSearchPackOutputBoundary searchPackOutputBoundary) {
        this.packIB.searchPack(str, searchPackOutputBoundary);
    }

    public void deletePack(String packName, IDataInOut dataInOut) {
        if (this.packIB.deletePack(packName)) {
            this.packIB.delete(dataInOut, databaseErrorOutputBoundary);
        }
    }

    /**
     * Old-to-new is the order of packs shown to the user by default.
     *
     * @param sortSearchPackOutputBoundary an output boundary that gets the result of sorted packs.
     */
    public void sortOldToNew(SortSearchPackOutputBoundary sortSearchPackOutputBoundary) {
        this.packIB.sortOldToNew(sortSearchPackOutputBoundary);
    }

    /**
     * Sort packs by pack names, in alphabetic order: A - Z.
     *
     * @param sortSearchPackOutputBoundary an output boundary that gets the result of sorted packs.
     */
    public void sortAToZ(SortSearchPackOutputBoundary sortSearchPackOutputBoundary) {
        this.packIB.sortAtoZ(sortSearchPackOutputBoundary);
    }
}
