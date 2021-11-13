package input_boundaries;

import entity.Card;
import entity.Pack;

import java.util.ArrayList;

public interface PackInputBoundary {
    Pack createNewPack(String packName);
    void addCard(Card card) throws Exception;
    void deleteCard(Card card) throws Exception;
    ArrayList<Card> searchCardByTerm(String term);
    ArrayList<Card> searchCardByDefinition(String keyWord);
    ArrayList<Card> sortOldToNew();
    ArrayList<Card> sortNewToOld();
    ArrayList<Card> sortAtoZ();
    ArrayList<Card> sortZtoA();
    ArrayList<Card> sortProLowToHigh();
    ArrayList<Card> sortProHighToLow();
    ArrayList<Card> sortRandom();
    Pack getCurrPack();
    void setCurrPack(Pack pack);
}
