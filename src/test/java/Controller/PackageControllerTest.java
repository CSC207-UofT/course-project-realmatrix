package Controller;

import entity.Card;
import entity.Pack;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PackageControllerTest {
    PackageController pc;
    Pack p1;
    Card c1;
    Card c2;
    String packName1 = "packName1";
    String cardTerm1 = "cardTerm1";
    String cardDefinition1 = "cardDefinition1";
    String cardTerm2 = "cardTerm2";
    String cardDefinition2 = "cardDefinition2";

    @Before
    public void createPackageController(){
        pc = new PackageController();
        c1 = new Card("someid1", cardTerm1, cardDefinition1);
        c2 = new Card("someid2", cardTerm2, cardDefinition2);
    }

    @Test
    public void testCreate(){
        assertEquals("Pack" + packName1 + "created",pc.create(packName1));
    }

//    @Test
//    public void testAddCard(){
//        assertEquals("Card" + cardTerm1 + "created", pc.addCard(c1));
//    }
//
//    @Test
//    public void testSearchCardByTerm(){
//        pc.addCard(c1);
//        pc.addCard(c2);
//        ArrayList<Card> expectedList = new ArrayList<>();
//        expectedList.add(c1);
//        expectedList.add(c2);
//        ArrayList<Card> actualList = pc.searchCardByTerm("term");
//        for (Card c: expectedList){
//            assertTrue(actualList.contains(c));
//        }
//        for (Card c: actualList){
//            assertTrue(expectedList.contains(c));
//        }
//    }
//
//    @Test
//    public void testSearchByDefinition(){
//        pc.addCard(c1);
//        pc.addCard(c2);
//        ArrayList<Card> expectedList = new ArrayList<>();
//        ArrayList<Card> actualList = pc.searchCardByDefinition("1");
//        expectedList.add(c1);
//        assertEquals(expectedList, actualList);
//    }

    //Todo: cannot test add/delete card, search term/ definition, set/get curr pack, since we cannot access the packInputBoundary and its pack manager to set curr pack.


}