import entity.Pack;
import entity.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    final String password = "001";
    final String name = "Ziqi";
    final String id = "UserId";
    User u;
    Pack pack1;
    Pack pack2;
    Pack pack3;

    @Before
    public void createUser() {
        u = new User(name, password);
        pack1 = new Pack("CSC207");
        pack2 = new Pack("CSC236");
        pack3 = new Pack("COG250");
    }

    @Test
    public void testGetName() {
        assertEquals(name, u.getName());
    }

    @Test
    public void testGetPassword() {
        assertEquals(password, u.getPassword());
    }

    @Test
    public void testChangeName() {
        String newName = "Ziqi Shu";
        u.changeName(newName);
        assertEquals(newName, u.getName());
    }

    @Test
    public void testChangePassword() {
        String newPassWord = "NewPassWord";
        u.changePassword(newPassWord);
        assertEquals(newPassWord, u.getPassword());
    }

    @Test
    public void testAddPackage() {
        try {
            u.addPackage(pack1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(1, u.getPackages().size());
        assertEquals(pack1, u.getPackages().get(0));
        try {
            u.addPackage(pack2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(2, u.getPackages().size());
        assertEquals(pack1, u.getPackages().get(0));
        assertEquals(pack2, u.getPackages().get(1));
        try {
            u.addPackage(pack3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(3, u.getPackages().size());
        assertEquals(pack1, u.getPackages().get(0));
        assertEquals(pack2, u.getPackages().get(1));
        assertEquals(pack3, u.getPackages().get(2));
    }

    @Test
    public void testGetPackList() {
        try {
            u.addPackage(pack1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            u.addPackage(pack2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            u.addPackage(pack3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(3, u.getPackages().size());
        assertEquals(pack1, u.getPackages().get(0));
        assertEquals(pack2, u.getPackages().get(1));
        assertEquals(pack3, u.getPackages().get(2));
    }

    @Test
    public void testDeletePackage() {
        try {
            u.addPackage(pack1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            u.addPackage(pack2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            u.addPackage(pack3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(u.deletePackage(pack1));
        assertEquals(2, u.getPackages().size());
        assertEquals(pack2, u.getPackages().get(0));
        assertEquals(pack3, u.getPackages().get(1));
        assertFalse(u.deletePackage(pack1));
        assertEquals(2, u.getPackages().size());
    }

}
