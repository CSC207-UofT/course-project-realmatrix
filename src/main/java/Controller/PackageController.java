package Controller;

import entity.Card;
import entity.Pack;
import manager.LoginManager;
import manager.PackManager;
import manager.UserManager;

import java.util.ArrayList;

public class PackageController {
    /**
     * A package Controller
     */
    private PackManager pm;

    public PackageController(){
        this.pm = new PackManager();
    }

    /**
     * Create and return a new pack with specified pack name.
     * Stores id and the corresponding pack in idToItem.
     *
     * @param packName   The name of the pack
     * @return           The newly-created pack
     */
    public String create(String packName){
        this.pm.createNewPack(packName);
        return "Pack" + packName + "created";
    }

    /**
     * Add a new card into current pack.
     *
     */
    public String addCard(Card c){
        try {
            this.pm.addCard(c);
            return "Card" + c.getTerm() + "created";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Delete a specific card in the current pack.
     *
     */
    public String deleteCard(Card card){
        try{
            this.pm.deleteCard(card);
            return "Card" + card.getTerm() + "deleted";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    /**
     * Users can search cards by card's term.
     * Return an arraylist of cards that contain (not necessarily equal to) term.
     * @param term  the term that the user searches
     * @return      an arraylist of cards that contain term
     */
    public ArrayList<Card> searchCardByTerm(String term) {
        return this.pm.searchCardByTerm(term);
    }

    /**
     * Users can search cards by card's definition.
     * Return an arraylist of cards that contain (not necessarily equal to) definition.
     * @param keyWord  the key word in the definition that the user searches
     * @return      an arraylist of cards that contain the key word of definition
     */
    public ArrayList<Card> searchCardByDefinition(String keyWord) {
        return this.pm.searchCardByDefinition(keyWord);
    }

    /**
     * Getter for the current pack the user is in.
     * @return the current pack.
     */
    public Pack getCurrPack() {
        return this.pm.getCurrPack();
    }

    /**
     * Change to the current pack the user is in.
     */
    public void setCurrPack(Pack pack) {
        this.pm.setCurrPack(pack);
    }

}
