import entity.Card;
import entity.Pack;
import entity.User;
import interface_adapter.gateway.DataInOut;
import interface_adapter.presenters.AddPresenter;
import interface_adapter.presenters.ChangePresenter;
import interface_adapter.presenters.SortSearchCardPresenter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.manager.CardManager;
import use_case.manager.ProgramStateManager;
import use_case.output_boundaries.SortSearchCardOutputBoundary;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class CardManagerTest {
    CardManager cm;
    String term;
    String def;
    Card c;
    ChangePresenter cp;
    ProgramStateInputBoundary programStateInputBoundary;

    @Before
    public void setUp() {
        programStateInputBoundary = new ProgramStateManager();
        User user = new User("test_user", "password");
        user.addPackage(new Pack("test"));
        programStateInputBoundary.setCurrUser(user);
        programStateInputBoundary.setCurrPack("test");
    }
    @Before
    public void createCardManager() {
        term = "Homoncular Fallacy";
        def = "Assuming a mental fallacy";

        cp = new ChangePresenter();
        cm = new CardManager(new DataInOut(), programStateInputBoundary);
        cm.addNewCard(term, def, new AddPresenter());
        c = programStateInputBoundary.getCurrPack().getCardMap().get(term);
    }

    @Test(timeout = 50)
    public void TestAddNewCard() {
        String term = "Homoncular Fallacy";
        String definition = "Assuming a mental fallacy";

        assertEquals(term, c.getTerm());
        assertEquals(0, c.getProficiency());
        assertEquals(definition, c.getDefinition());
    }

    @Test(timeout = 50)
    public void TestChangeCardTerm() {
        String newTerm = "Equivocation";
        cm.changeCardTerm(newTerm, cp);
        assertEquals(newTerm, c.getTerm());
    }

    @Test(timeout = 50)
    public void TestChangeCardDefinition() {
        String newDefinition = "leading misunderstanding.";
        cm.changeCardDefinition(newDefinition);
        assertEquals(newDefinition, c.getDefinition());
    }

    @Test(timeout = 50)
    public void testSearchCardTerm() {
        SortSearchCardOutputBoundary sortSearchCardOutputBoundary = new SortSearchCardPresenter();
        cm.searchCard(term, sortSearchCardOutputBoundary);
        String[][] actual = sortSearchCardOutputBoundary.getSortSearchResult();
        String[][] expected = new String[][]{{term, def}};
        assertEquals(Arrays.toString(actual[0]), Arrays.toString(expected[0]));

    }

    @Test(timeout = 50)
    public void testSearchCardDef() {
        SortSearchCardOutputBoundary sortSearchCardOutputBoundary = new SortSearchCardPresenter();
        cm.searchCard(def, sortSearchCardOutputBoundary);
        String[][] actual = sortSearchCardOutputBoundary.getSortSearchResult();
        String[][] expected = new String[][]{{term, def}};
        Assert.assertArrayEquals(expected, actual);

    }

    @Test(timeout = 50)
    public void testSortOldToNew() {
        SortSearchCardOutputBoundary sortSearchCardOutputBoundary = new SortSearchCardPresenter();
        cm.addNewCard("term2", "def2", new AddPresenter());
        cm.sortOldToNew(sortSearchCardOutputBoundary);
        String[][] actual = sortSearchCardOutputBoundary.getSortSearchResult();
        String[][] expected = new String[][]{{term, def}, {"term2", "def2"}};

        Assert.assertArrayEquals(expected, actual);
    }

    @Test(timeout = 50)
    public void testSortAtoZ() {
        SortSearchCardOutputBoundary sortSearchCardOutputBoundary = new SortSearchCardPresenter();
        cm.addNewCard("a", "a", new AddPresenter());
        cm.sortAtoZ(sortSearchCardOutputBoundary);
        String[][] actual = sortSearchCardOutputBoundary.getSortSearchResult();
        String[][] expected = new String[][]{{"a", "a"}, {term, def}};

        Assert.assertArrayEquals(expected, actual);
    }

    @Test(timeout = 50)
    public void testDeleteCard() {
        cm.deleteCard(term);
        assertEquals(programStateInputBoundary.getCurrPack().getCardList().size(), 0);

    }

}

