import entity.Pack;
import entity.Card;
import manager.CardManager;
import manager.PackManager;
//import manager.Manager;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
//import java.util.Collections;
//import java.util.Comparator;

import static org.junit.Assert.*;

public class PackManagerTest {
    PackManager pm;
    String pName = "COG250";
    Pack p;
    String c1Term = "Equivocation";
    String c1Definition = "Explaining something by assuming itself.";
    String c2Term = "Fragmentation";
    String c2Definition = "Don't feel himself as a unit.";
    CardManager cm1;
    CardManager cm2;
    Card c1;
    Card c2;

    @Before
    public void createPackManager() throws Exception {
        pm = new PackManager();
        p = pm.createNewPack(pName);
        pm.setCurrPack(p);
        cm1 = new CardManager();
        c1 = cm1.createNewCard(c1Term, c1Definition);
        cm1.setCurrCard(c1);
        cm2 = new CardManager();
        c2 = cm2.createNewCard(c2Term, c2Definition);
        cm2.setCurrCard(c2);
        pm.addCard(c1);
    }

    @Test
    public void TestCreateNewPack(){
        assertEquals(pName, p.getName());
    }

    @Test
    public void TestAddCard(){
        List<Card> cardList = pm.sortZtoA();
        assertEquals(c1, cardList.get(0));
    }

    @Test
    public void TestDeleteCard() throws Exception {
        pm.deleteCard(c1);
        List<Card> cardList = pm.sortZtoA();
        assertEquals(0, cardList.size());
    }

    @Test
    public void TestSearchCardByTerm() throws Exception {
        pm.addCard(c2);
        List<Card> cardList = pm.searchCardByTerm(c2Term);
        assertEquals(c2, cardList.get(0));
        assertEquals(1, cardList.size());
        List<Card> cardList2 = pm.searchCardByTerm("tion");
        assertEquals(2, cardList2.size());
    }

    @Test
    public void TestSearchCardByDefinition() throws Exception {
        pm.addCard(c2);
        List<Card> cardList = pm.searchCardByDefinition(c2Definition);
        assertEquals(c2, cardList.get(0));
        assertEquals(1, cardList.size());
        List<Card> cardList2 = pm.searchCardByDefinition("self");
        assertEquals(2, cardList2.size());

    }

    @Test
    public void TestSortOldToNew() throws Exception {
        pm.addCard(c2);
        List<Card> cardList = pm.sortOldToNew();
        List<Card> actual = new ArrayList<>();
        actual.add(c1);
        actual.add(c2);
        assertEquals(actual, cardList);

    }

    @Test
    public void TestSortAToZ() throws Exception {
        pm.addCard(c2);
        List<Card> cardList = pm.sortAtoZ();
        List<Card> actual = new ArrayList<>();
        actual.add(c1);
        actual.add(c2);
        assertEquals(actual, cardList);
    }

    @Test
    public void TestSortZToA() throws Exception {
        pm.addCard(c2);
        List<Card> cardList = pm.sortZtoA();
        List<Card> actual = new ArrayList<>();
        actual.add(c2);
        actual.add(c1);
        assertEquals(actual, cardList);
    }

    @Test
    public void TestSorProLowToHigh() throws Exception {
        cm2.increaseProficiency();
        pm.addCard(c2);
        List<Card> cardList = pm.sortProLowToHigh();
        List<Card> actual = new ArrayList<>();
        actual.add(c1);
        actual.add(c2);
        assertEquals(actual, cardList);
    }

    @Test
    public void TestSortProHighToLow() throws Exception {
        cm2.increaseProficiency();
        pm.addCard(c2);
        List<Card> cardList = pm.sortProHighToLow();
        List<Card> actual = new ArrayList<>();
        actual.add(c2);
        actual.add(c1);
        assertEquals(actual, cardList);
        cm1.increaseProficiency();
        cm1.increaseProficiency();
        List<Card> cardList2 = pm.sortProHighToLow();
        List<Card> actual2 = new ArrayList<>();
        actual2.add(c1);
        actual2.add(c2);
        assertEquals(actual2, cardList2);

    }

//    @Test
//    public void TestAlphabetComparator(){
//
//    }
//
//    @Test
//    public void TestProficiencyComparator(){}
//
//    @Test
//    public void TestGetCurrPack(){}
//
//    @Test
//    public void TestSetCurrPack(){}
}