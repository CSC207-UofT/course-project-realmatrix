import entity.Pack;
import entity.User;
import interface_adapter.presenters.ChangePresenter;
import interface_adapter.presenters.SortSearchPackPresenter;
import org.junit.Before;
import org.junit.Test;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.manager.ProgramStateManager;
import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.SortSearchPackOutputBoundary;
import interface_adapter.presenters.AddPresenter;
import use_case.manager.PackManager;

import java.util.ArrayList;

import static org.junit.Assert.*;

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
    public void testAddNewPack() {
        assertEquals(pName1, p1.getName());
        assertEquals(pName2, p2.getName());
    }

    @Test
    public void testDeletePack() {
        pm.deletePack(pName2);
        assertNull(programStateInputBoundary.getCurrUser().getPackageMap().get(pName2));
    }

    @Test
    public void testChangePackName(){
        ChangePresenter changeP = new ChangePresenter();
        String pName2New = "pName2New";
        assertTrue(pm.changePackName(pName2New, changeP));
        assertEquals(pName2New, p2.getName());
        assertFalse(pm.changePackName(pName1, changeP));
        assertEquals(pName2New, p2.getName());
    }



    @Test
    public void testSearchPack(){
        pm.searchPack(pName1, sortSearchPackOutputBoundary);
        ArrayList<String> expect = new ArrayList<>();
        expect.add(pName1);
        assertEquals(expect, sortSearchPackOutputBoundary.getSortSearchResult());
    }

    @Test
    public void testSortOldToNew(){
        pm.sortOldToNew(sortSearchPackOutputBoundary);
        ArrayList<String> expect = new ArrayList<>();
        expect.add(pName1);
        expect.add(pName2);
        assertEquals(expect, sortSearchPackOutputBoundary.getSortSearchResult());
    }

    @Test
    public void testSortAToZ(){
        pm.sortAtoZ(sortSearchPackOutputBoundary);
        ArrayList<String> expect = new ArrayList<>();
        expect.add(pName1);
        expect.add(pName2);
        assertEquals(expect, sortSearchPackOutputBoundary.getSortSearchResult());
    }

}
