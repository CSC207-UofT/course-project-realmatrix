import entity.User;
import entity.Pack;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    String password = "001";
    String name = "Ziqi";
    String id = "UserId";
    User u;
    Pack pack1;
    Pack pack2;
    Pack pack3;

    @Before
    public void createUser(){
        u = new User(id, name, password);
        pack1 = new Pack("01", "CSC207");
        pack2 = new Pack("02", "CSC236");
        pack3 = new Pack("03", "COG250");
    }

    @Test
    public void testGetId(){
        assertEquals(id, u.getId());
    }

    @Test
    public void testGetName(){
        assertEquals(name, u.getName());
    }

    @Test
    public void testGetPassword(){
        assertEquals(password, u.getPassword());
    }

    @Test
    public void testChangeName(){
        String newName = "Ziqi Shu";
        u.changeName(newName);
        assertEquals(newName, u.getName());
    }

    @Test
    public void testChangePassword(){
        String newPassWord = "NewPassWord";
        u.changePassword(newPassWord);
        assertEquals(newPassWord, u.getPassword());
    }

    @Test
    public void testCreatePackage(){
        u.createPackage(pack1);
        assertEquals(1, u.getPackages().size());
        assertEquals(pack1, u.getPackages().get(0));
        u.createPackage(pack2);
        assertEquals(2, u.getPackages().size());
        assertEquals(pack1, u.getPackages().get(0));
        assertEquals(pack2, u.getPackages().get(1));
        u.createPackage(pack3);
        assertEquals(3, u.getPackages().size());
        assertEquals(pack1, u.getPackages().get(0));
        assertEquals(pack2, u.getPackages().get(1));
        assertEquals(pack3, u.getPackages().get(2));
    }

    @Test
    public void testGetPackList(){
        u.createPackage(pack1);
        u.createPackage(pack2);
        u.createPackage(pack3);
        assertEquals(3, u.getPackages().size());
        assertEquals(pack1, u.getPackages().get(0));
        assertEquals(pack2, u.getPackages().get(1));
        assertEquals(pack3, u.getPackages().get(2));
    }

    @Test
    public void testDeletePackage(){
        u.createPackage(pack1);
        u.createPackage(pack2);
        u.createPackage(pack3);
        assertTrue(u.deletePackage(pack1));
        assertEquals(2, u.getPackages().size());
        assertEquals(pack2, u.getPackages().get(0));
        assertEquals(pack3, u.getPackages().get(1));
        assertFalse(u.deletePackage(pack1));
        assertEquals(2, u.getPackages().size());
    }

}
