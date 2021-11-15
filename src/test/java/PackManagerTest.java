import entity.Card;
import entity.Pack;
import org.junit.Before;
import org.junit.Test;
import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.SearchOutputBoundary;
import use_case.output_boundaries.SortOutputBoundary;
import interface_adapter.presenters.AddPresenter;
import interface_adapter.presenters.SearchPresenter;
import interface_adapter.presenters.SortPresenter;
import use_case.manager.CardManager;
import use_case.manager.PackManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
    AddOutputBoundary AddOutputBoundary = new AddPresenter();
    SortOutputBoundary<Card> sortOutputBoundary = new SortPresenter<>();
    SearchOutputBoundary<Card> searchOutputBoundary = new SearchPresenter<>();

    @Before
    public void createPackManager() {
        pm = new PackManager();
        p = pm.createNewPack(pName);
        pm.setCurrPack(p);
        cm1 = new CardManager();
        c1 = cm1.createNewCard(c1Term, c1Definition);
        cm1.setCurrCard(c1);
        cm2 = new CardManager();
        c2 = cm2.createNewCard(c2Term, c2Definition);
        cm2.setCurrCard(c2);
        pm.addCard(c1, AddOutputBoundary);
    }

    @Test
    public void TestCreateNewPack() {
        assertEquals(pName, p.getName());
    }

    @Test
    public void TestAddCard() {
        List<Card> cardList = pm.sortZtoA(sortOutputBoundary);
        assertEquals(c1, cardList.get(0));
    }

    @Test
    public void TestDeleteCard() {
        pm.deleteCard(c1);
        List<Card> cardList = pm.sortZtoA(sortOutputBoundary);
        assertEquals(0, cardList.size());
    }

    @Test
    public void TestSearchCardTerm() {
        pm.addCard(c2, AddOutputBoundary);
        List<Card> cardList = pm.searchCard(c2Term, searchOutputBoundary);
        assertEquals(c2, cardList.get(0));
        assertEquals(1, cardList.size());
        List<Card> cardList2 = pm.searchCard("tion", searchOutputBoundary);
        assertEquals(2, cardList2.size());
    }

    @Test
    public void TestSearchCardByDefinition() {
        pm.addCard(c2, AddOutputBoundary);
        List<Card> cardList = pm.searchCard(c2Definition, searchOutputBoundary);
        assertEquals(c2, cardList.get(0));
        assertEquals(1, cardList.size());
        List<Card> cardList2 = pm.searchCard("self", searchOutputBoundary);
        assertEquals(2, cardList2.size());

    }

    @Test
    public void TestSortOldToNew() {
        pm.addCard(c2, AddOutputBoundary);
        List<Card> cardList = pm.sortOldToNew(sortOutputBoundary);
        List<Card> actual = new ArrayList<>();
        actual.add(c1);
        actual.add(c2);
        assertEquals(actual, cardList);

    }

    @Test
    public void TestSortAToZ() {
        pm.addCard(c2, AddOutputBoundary);
        List<Card> cardList = pm.sortAtoZ(sortOutputBoundary);
        List<Card> actual = new ArrayList<>();
        actual.add(c1);
        actual.add(c2);
        assertEquals(actual, cardList);
    }

    @Test
    public void TestSortZToA() {
        pm.addCard(c2, AddOutputBoundary);
        List<Card> cardList = pm.sortZtoA(sortOutputBoundary);
        List<Card> actual = new ArrayList<>();
        actual.add(c2);
        actual.add(c1);
        assertEquals(actual, cardList);
    }

    @Test
    public void TestSorProLowToHigh() {
        cm2.increaseProficiency();
        pm.addCard(c2, AddOutputBoundary);
        List<Card> cardList = pm.sortProLowToHigh();
        List<Card> actual = new ArrayList<>();
        actual.add(c1);
        actual.add(c2);
        assertEquals(actual, cardList);
    }

    @Test
    public void TestSortProHighToLow() {
        cm2.increaseProficiency();
        pm.addCard(c2, AddOutputBoundary);
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
