package interface_adapter.Controller;

import entity.Card;
import entity.Pack;
import use_case.input_boundaries.PackInputBoundary;
import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.SearchOutputBoundary;
import use_case.output_boundaries.SortOutputBoundary;

/**
 * A package interface_adapter.Controller that can create/change packname   and   add/delete/search/sort card in a pack.
 */
public class PackController {
    private final PackInputBoundary packIB;

    public PackController(PackInputBoundary packIB) {
        this.packIB = packIB;
    }

    /**
     * Create and return a new pack with specified pack name.
     *
     * @param packName The name of the pack
     */
    public void createPack(String packName) {
        this.packIB.createNewPack(packName);
    }

    /**
     * Change a pack's name with specified pack name.
     *
     * @param newPackName The name of the pack
     */
    public void changePackName(String newPackName, ChangeOutputBoundary changeOutputBoudary) {
        this.packIB.changePackName(newPackName, changeOutputBoudary);
    }

    /**
     * Add a new card into current pack.
     */
    public String addCard(Card c, AddOutputBoundary AddOutputBoundary) {
        try {
            this.packIB.addCard(c, AddOutputBoundary);
            return "Card" + c.getTerm() + "created";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Delete a specific card in the current pack.
     */
    public void deleteCard(Card card) {
        this.packIB.deleteCard(card);
    }

    /**
     * Users can search cards in this pack by card's term and definition.
     * Return an arraylist of cards that contain (not necessarily equal to) keyword.
     *
     * @param keyword the keyword that the user searches
     */
    public void searchCard(String keyword, SearchOutputBoundary<Card> searchOutputBoundary) {
        this.packIB.searchCard(keyword, searchOutputBoundary);
    }


    // The following 3 methods sort card in a pack
    public void sortCardAtoZ(SortOutputBoundary<Card> sortOutputBoundary) {
        this.packIB.sortAtoZ(sortOutputBoundary);
    }

    public void sortPackNewToOld(SortOutputBoundary<Card> sortOutputBoundary) {
        this.packIB.sortNewToOld(sortOutputBoundary);
    }

    public void sortPackRandom(SortOutputBoundary<Card> sortOutputBoundary) {
        this.packIB.sortRandom(sortOutputBoundary);
    }

    /**
     * Getter for the current pack the user is in.
     *
     * @return the current pack.
     */
    public Pack getCurrPack() {
        return this.packIB.getCurrPack();
    }

    /**
     * Change to the current pack the user is in.
     */
    public void setCurrPack(Pack pack) {
        this.packIB.setCurrPack(pack);
    }

}