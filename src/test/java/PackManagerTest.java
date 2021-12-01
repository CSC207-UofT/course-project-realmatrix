import entity.Pack;
import entity.User;
import interface_adapter.presenters.SortSearchPackPresenter;
import org.junit.Before;
import org.junit.Test;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.manager.ProgramStateManager;
import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.SortSearchPackOutputBoundary;
import interface_adapter.presenters.AddPresenter;
import use_case.manager.PackManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PackManagerTest {
    PackManager pm;
    final String pName1 = "COG250";
    final String pName2 = "CSC207";
    Pack p1;
    Pack p2;
    final AddOutputBoundary addOutputBoundary = new AddPresenter();
    final SortSearchPackOutputBoundary sortSearchPackOutputBoundary = new SortSearchPackPresenter();
    final ProgramStateInputBoundary programStateInputBoundary = new ProgramStateManager();

    @Before
    public void createPackManager() {
        User user = new User("test_user", "test");
        programStateInputBoundary.setCurrUser(user);
        pm = new PackManager(programStateInputBoundary);
        pm.addNewPack(pName1, addOutputBoundary);
        pm.addNewPack(pName2, addOutputBoundary);
        p1 = programStateInputBoundary.getCurrUser().getPackageMap().get(pName1);
        p2 = programStateInputBoundary.getCurrUser().getPackageMap().get(pName2);
    }

    @Test(timeout = 50)
    public void TestCreateNewPack() {
        assertEquals(pName1, p1.getName());
        assertEquals(pName2, p2.getName());
    }

    @Test
    public void testDeletePack() {
        pm.deletePack(pName2);
        assertNull(programStateInputBoundary.getCurrUser().getPackageMap().get(pName2));
    }
//
//    @Test
//    public void TestSearchCardTerm() {
//        pm.addCard(c2, AddOutputBoundary);
//        List<Card> cardList = pm.searchCard(c2Term, searchCardOutputBoundary);
//        assertEquals(c2, cardList.get(0));
//        assertEquals(1, cardList.size());
//        List<Card> cardList2 = pm.searchCard("tion", searchCardOutputBoundary);
//        assertEquals(2, cardList2.size());
//    }
//
//    @Test
//    public void TestSearchCardByDefinition() {
//        pm.addCard(c2, AddOutputBoundary);
//        List<Card> cardList = pm.searchCard(c2Definition, searchCardOutputBoundary);
//        assertEquals(c2, cardList.get(0));
//        assertEquals(1, cardList.size());
//        List<Card> cardList2 = pm.searchCard("self", searchCardOutputBoundary);
//        assertEquals(2, cardList2.size());
//
//    }
//
//    @Test
//    public void TestSortOldToNew() {
//        pm.addCard(c2, AddOutputBoundary);
//        List<Card> cardList = pm.sortOldToNew(sortCardOutputBoundary);
//        List<Card> actual = new ArrayList<>();
//        actual.add(c1);
//        actual.add(c2);
//        assertEquals(actual, cardList);
//
//    }
//
//    @Test
//    public void TestSortAToZ() {
//        pm.addCard(c2, AddOutputBoundary);
//        List<Card> cardList = pm.sortAtoZ(sortCardOutputBoundary);
//        List<Card> actual = new ArrayList<>();
//        actual.add(c1);
//        actual.add(c2);
//        assertEquals(actual, cardList);
//    }
//
//    @Test
//    public void TestSortZToA() {
//        pm.addCard(c2, AddOutputBoundary);
//        List<Card> cardList = pm.sortZtoA(sortCardOutputBoundary);
//        List<Card> actual = new ArrayList<>();
//        actual.add(c2);
//        actual.add(c1);
//        assertEquals(actual, cardList);
//    }
//
//    @Test
//    public void TestSorProLowToHigh() {
//        cm2.increaseProficiency();
//        pm.addCard(c2, AddOutputBoundary);
//        List<Card> cardList = pm.sortProLowToHigh();
//        List<Card> actual = new ArrayList<>();
//        actual.add(c1);
//        actual.add(c2);
//        assertEquals(actual, cardList);
//    }
//
//    @Test
//    public void TestSortProHighToLow() {
//        cm2.increaseProficiency();
//        pm.addCard(c2, AddOutputBoundary);
//        List<Card> cardList = pm.sortProHighToLow();
//        List<Card> actual = new ArrayList<>();
//        actual.add(c2);
//        actual.add(c1);
//        assertEquals(actual, cardList);
//        cm1.increaseProficiency();
//        cm1.increaseProficiency();
//        List<Card> cardList2 = pm.sortProHighToLow();
//        List<Card> actual2 = new ArrayList<>();
//        actual2.add(c1);
//        actual2.add(c2);
//        assertEquals(actual2, cardList2);
//
//    }

}
