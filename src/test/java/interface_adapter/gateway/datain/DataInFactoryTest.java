package interface_adapter.gateway.datain;

import entity.Card;
import entity.Pack;
import entity.User;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class DataInFactoryTest {
    final String username = "username";
    final String packname = "packname";
    String[] partialPath;
    DataInFactory datainF;
    Card card;
    Pack pack;
    User user;
    final String string = "will throw exception";


    @Before
    public void createDataInFactory() {
        datainF = new DataInFactory();
        partialPath = new String[]{username, packname};
        card = new Card("term", "definition");
        pack = new Pack("name");
        user = new User("name", "password");
    }

    @Test
    public void testCardGetWriter() throws IOException {
        assertTrue(datainF.getWriter(partialPath, card) instanceof CardWriter);
    }

    @Test
    public void testPackGetWriter() throws IOException {
        assertTrue(datainF.getWriter(partialPath, pack) instanceof PackWriter);
    }

    @Test
    public void testUserGetWriter() throws IOException {
        assertTrue(datainF.getWriter(partialPath, user) instanceof UserWriter);
    }

    @Test
    public void testExceptionGetWriter() {
        Exception e = assertThrows(Exception.class, () -> datainF.getWriter(partialPath, string));
    }
}