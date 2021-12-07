package use_case.manager;

import entity.Pack;
import use_case.input_boundaries.PackInputBoundary;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.SortSearchPackOutputBoundary;

import java.util.ArrayList;

/**
 * A pack manager manages the current user's packs.
 */
public class PackManager extends Manager<Pack> implements PackInputBoundary {

    public PackManager(ProgramStateInputBoundary programStateInputBoundary) {
        super(programStateInputBoundary);
        this.currItem = this.programStateInputBoundary.getCurrPack();   // current pack the user is working on
        this.items = this.programStateInputBoundary.getCurrUser().getPackageMap(); // a map <packName: Pack>
    }

    /**
     * Create a new pack with specified pack name for the current user.
     * If the pack name already exists, adding fails. Otherwise, adding succeeds.
     *
     * @param packName          The name of the pack
     * @param addOutputBoundary an output boundary for showing the result of adding new pack
     * @return true if the pack is successfully added; false otherwise
     */
    public boolean addNewPack(String packName, AddOutputBoundary addOutputBoundary) {
        if (!this.items.containsKey(packName)) { // no pack has such packname, adding is valid
            Pack p = new Pack(packName);
            this.items.put(packName, p);
            this.currItem = p;
            programStateInputBoundary.getCurrUser().addPackage(p);  // add this pack into user
            addOutputBoundary.setAddResult(true);
            return true;
        }
        addOutputBoundary.setAddResult(false);
        return false;
    }

    /**
     * Delete a pack with specified pack name.
     *
     * @param packName the name of the pack to be deleted
     * @return true if successfully deleted; false otherwise
     */
    @Override
    public boolean deletePack(String packName) {
        currItem = this.items.get(packName);
        if (currItem != null) { // We have the item to be deleted
            items.remove(packName);
            programStateInputBoundary.getCurrUser().deletePackage(currItem);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Change a pack's name.
     * If the new name doesn't exist, change succeeds. Otherwise, change fails.
     *
     * @param newPackName          the new pack name
     * @param changeOutputBoundary the output boundary for getting the result of change (successful or not)
     * @return true if user successfully changed the pack name; false otherwise
     */
    @Override
    public boolean changePackName(String newPackName, ChangeOutputBoundary changeOutputBoundary) {
        Pack item = searchItem(newPackName);
        if (item == null) { // No user of such username, valid for change
            this.items.remove(currItem.getName()); // Remove pack with old name
            currItem.changeName(newPackName);
            this.items.put(newPackName, currItem);     // Add pack with new name
            changeOutputBoundary.setChangeResult(true);
            return true;
        } else {
            changeOutputBoundary.setChangeResult(false);
            return false;
        }
    }

    /**
     * Search packs with specified pack name (Ignore case).
     * All packs that contain (not necessarily equal) packName would be searched.
     *
     * @param packName                     the packName to be searched
     * @param sortSearchPackOutputBoundary an output boundary that gets the searched result
     */
    @Override
    public void searchPack(String packName, SortSearchPackOutputBoundary sortSearchPackOutputBoundary) {
        ArrayList<Pack> packList = programStateInputBoundary.getCurrUser().getPackageList();
        ArrayList<String> result = new ArrayList<>();
        for (Pack p : packList) {
            if (p.getName().toLowerCase().contains(packName.toLowerCase())) {
                result.add(p.getName());
            }
        }
        sortSearchPackOutputBoundary.setSortSearchResult(result);
    }

    /**
     * Sort a pack by date added: oldest to newest.
     *
     * @param sortSearchPackOutputBoundary a sort output boundary for getting the sorted output.
     */
    @Override
    public void sortOldToNew(SortSearchPackOutputBoundary sortSearchPackOutputBoundary) {
        ArrayList<Pack> packList = programStateInputBoundary.getCurrUser().getPackageList();
        ArrayList<String> packNameList = new ArrayList<>();
        for (Pack p : packList) {
            packNameList.add(p.getName());
        }
        sortSearchPackOutputBoundary.setSortSearchResult(packNameList);
    }

    /**
     * Sort a pack by alphabetic order: A to Z.
     *
     * @param sortSearchPackOutputBoundary a sort output boundary for getting the sorted output.
     */
    @Override
    public void sortAtoZ(SortSearchPackOutputBoundary sortSearchPackOutputBoundary) {
        ArrayList<String> packNameList = new ArrayList<>(this.items.keySet());
        packNameList.sort(String::compareToIgnoreCase);
        sortSearchPackOutputBoundary.setSortSearchResult(packNameList);
    }


}
