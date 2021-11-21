package use_case.input_boundaries;

import entity.Card;
import entity.Pack;
import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.SearchOutputBoundary;
import use_case.output_boundaries.SortOutputBoundary;

import java.util.ArrayList;

public interface PackInputBoundary {
    Pack createNewPack(String packName);

    boolean changePackName(String newPackName, ChangeOutputBoundary changePackNameOB);

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
