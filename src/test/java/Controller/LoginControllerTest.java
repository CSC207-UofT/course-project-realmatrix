package Controller;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoginControllerTest {
    LoginController lc;

    @Before
    public void setUp() throws Exception {
        lc = new LoginController();
        lc.um.createNewUser("Ziqi", "ycfszd");
    }

    @Test
    public void testLogin() {
        try{
            lc.login("Ziqi", "ycfszd");
            assertEquals(lc.getCurrentUser().getName(), "Ziqi");
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void testSignOff(){
        try{
            lc.login("Ziqi", "ycfszd");
            assertEquals("User signed off", lc.signOff());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testGetCurrUser(){
        try{
            lc.login("Ziqi", "ycfszd");
            assertEquals("Ziqi", lc.getCurrentUser().getName());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}