package input_boundaries;

import entity.Card;
import entity.Pack;
import output_boundaries.AddOutputBoundary;
import output_boundaries.ChangeOutputBoundary;
import output_boundaries.SearchOutputBoundary;
import output_boundaries.SortOutputBoundary;

import java.util.ArrayList;

public interface PackInputBoundary {
    Pack createNewPack(String packName);

    void changePackName(String newPackName, ChangeOutputBoundary changePackNameOB);

    void addCard(Card card, AddOutputBoundary AddOutputBoundary);

    void deleteCard(Card card);

    ArrayList<Card> searchCard(String keyword, SearchOutputBoundary<Card> searchOutputBoundary);

    // Maybe too many sort methods
    ArrayList<Card> sortOldToNew(SortOutputBoundary<Card> sortOutputBoundary);

    ArrayList<Card> sortNewToOld(SortOutputBoundary<Card> sortOutputBoundary);

    ArrayList<Card> sortAtoZ(SortOutputBoundary<Card> sortOutputBoundary);

    ArrayList<Card> sortZtoA(SortOutputBoundary<Card> sortOutputBoundary);

    ArrayList<Card> sortProLowToHigh();

    ArrayList<Card> sortProHighToLow();

    ArrayList<Card> sortRandom(SortOutputBoundary<Card> sortOutputBoundary);

    // These two may not be needed if we have observer for tracking program state
    Pack getCurrPack();

    void setCurrPack(Pack pack);
}
