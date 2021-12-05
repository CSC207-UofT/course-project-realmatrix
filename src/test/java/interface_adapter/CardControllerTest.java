package interface_adapter;

import entity.Card;
import entity.Pack;
import entity.User;
import framework.GUI.database_error.DatabaseErrorWindow;
import interface_adapter.controller.CardController;
import interface_adapter.gateway.DataInOut;
import interface_adapter.gateway.IDataInOut;
import interface_adapter.gateway.datain.CardWriter;
import interface_adapter.gateway.datain.PackWriter;
import interface_adapter.gateway.datain.UserWriter;
import interface_adapter.presenters.AddPresenter;
import interface_adapter.presenters.ChangePresenter;
import interface_adapter.presenters.DatabaseErrMsgPresenter;
import interface_adapter.presenters.SortSearchCardPresenter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import use_case.manager.CardManager;
import use_case.manager.ProgramStateManager;
import use_case.output_boundaries.SortSearchCardOutputBoundary;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class CardControllerTest {
    CardController cc;
    CardManager cm;
    final String c1Term = "closet";
    final String c1TermNew = "c1TermNew";
    final String c1Def = "a tall cupboard or wardrobe with a door, used for storage.";
    final String c1DefNew = "c1DefNew";
    final Card c1 = new Card(c1Term, c1Def);
    ChangePresenter cp;
    final IDataInOut dataInOut = new DataInOut();
    final ProgramStateManager programStateInputBoundary = new ProgramStateManager();
    final DatabaseErrMsgPresenter databaseErrorOutputBoundary = new DatabaseErrMsgPresenter(new DatabaseErrorWindow());
    final AddPresenter addOutputBoundary = new AddPresenter();
    final ChangePresenter changeOutputBoundary = new ChangePresenter();
    final String user1Name = "user1Name";
    final String user1Pw = "user1Pw";
    final String pack1Name = "pack1Name";
    final User user1 = new User(user1Name, user1Pw);
    final Pack pack1 = new Pack(pack1Name);

    @Before
    public void createCardController() {
        user1.addPackage(pack1);
        pack1.addCard(c1);
        programStateInputBoundary.setCurrUser(user1);
        programStateInputBoundary.setCurrPack(pack1Name);
        programStateInputBoundary.setCurrCard(c1Term);
        cm = new CardManager(programStateInputBoundary);
        cc = new CardController(cm, databaseErrorOutputBoundary);
        cp = new ChangePresenter();
    }

    @Test
    public void testCreateNewCard() {
        assertEquals(1, programStateInputBoundary.getCurrPack().getCardList().size());
        cc.addNewCard("newTerm", "newDef", dataInOut, addOutputBoundary);
        assertEquals(2, programStateInputBoundary.getCurrPack().getCardList().size());
    }

    @Test
    public void testChangeCardTerm() {
        cc.changeCardTerm(c1Term, c1TermNew, dataInOut, changeOutputBoundary);
        assertEquals(c1TermNew, c1.getTerm());
    }

    @Test
    public void testChangeCardDefinition() {
        cc.changeCardDefinition(c1DefNew, dataInOut);
        assertEquals(c1DefNew, c1.getDefinition());
    }

    @Test
    public void testDeleteCard(){
        assertEquals(1, pack1.getCardList().size());
        cc.deleteCard(c1Term, dataInOut);
        assertEquals(0, pack1.getCardList().size());
    }

    @Test
    public void testSearchCard(){
        SortSearchCardOutputBoundary sortSearchCardOutputBoundary = new SortSearchCardPresenter();
        cc.searchCard(c1Term, sortSearchCardOutputBoundary);
        String[][] actual = sortSearchCardOutputBoundary.getSortSearchResult();
        String[][] expected = new String[][]{{c1Term, c1Def}};
        assertEquals(Arrays.toString(actual[0]), Arrays.toString(expected[0]));
    }

    @Test
    public void testSortOldToNew(){
        SortSearchCardOutputBoundary sortSearchCardOutputBoundary = new SortSearchCardPresenter();
        String c2Term = "c2Term";
        String c2Def = "c2Def";
        cc.addNewCard(c2Term, c2Def, dataInOut, addOutputBoundary);
        cc.sortOldToNew(sortSearchCardOutputBoundary);
        String[][] actual = sortSearchCardOutputBoundary.getSortSearchResult();
        String[][] expected = new String[][]{{c1Term, c1Def}, {c2Term, c2Def}};
        assertEquals(Arrays.toString(actual[0]), Arrays.toString(expected[0]));
        assertEquals(Arrays.toString(actual[1]), Arrays.toString(expected[1]));
    }

    @Test
    public void testSortAToZ(){
        SortSearchCardOutputBoundary sortSearchCardOutputBoundary = new SortSearchCardPresenter();
        String c2Term = "c2Term";
        String c2Def = "c2Def";
        cc.addNewCard(c2Term, c2Def, dataInOut, addOutputBoundary);
        cc.sortAToZ(sortSearchCardOutputBoundary);
        String[][] actual = sortSearchCardOutputBoundary.getSortSearchResult();
        String[][] expected = new String[][]{{c2Term, c2Def}, {c1Term, c1Def}};
        assertEquals(Arrays.toString(actual[0]), Arrays.toString(expected[0]));
        assertEquals(Arrays.toString(actual[1]), Arrays.toString(expected[1]));
    }

    @Test
    public void testSortProLowToHigh(){
        SortSearchCardOutputBoundary sortSearchCardOutputBoundary = new SortSearchCardPresenter();
        String c2Term = "c2Term";
        String c2Def = "c2Def";
        cc.addNewCard(c2Term, c2Def, dataInOut, addOutputBoundary);
        c1.setProficiency(2);
        cc.sortProLowToHigh(sortSearchCardOutputBoundary);
        String[][] actual = sortSearchCardOutputBoundary.getSortSearchResult();
        String[][] expected = new String[][]{{c2Term, c2Def}, {c1Term, c1Def}};
        assertEquals(Arrays.toString(actual[0]), Arrays.toString(expected[0]));
        assertEquals(Arrays.toString(actual[1]), Arrays.toString(expected[1]));
    }

    @After
    public void tearDone() {
        String[] partialPath;
        partialPath = new String[]{user1Name, pack1Name};
        for (Card c: programStateInputBoundary.getCurrPack().getCardList()){
            CardWriter cw = new CardWriter(partialPath, c);
            cw.delete();
        }
        PackWriter pw = new PackWriter(partialPath, pack1);
        UserWriter uw = new UserWriter(partialPath, user1);
        pw.delete();
        uw.delete();

    }
}

