import entity.Pack;
import entity.User;
import interface_adapter.gateway.DataInOut;
import interface_adapter.gateway.datain.PackWriter;
import interface_adapter.gateway.datain.UserWriter;
import interface_adapter.presenters.*;
import org.junit.Before;
import org.junit.Test;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.manager.ProgramStateManager;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.RegisterOutputBoundary;
import use_case.manager.UserManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class UserManagerTest {
    UserManager um;
    final String user1Name = "newUser1";
    final String user1Password = "user_password1";
    final String user2Name = "newUser2";
    final String user2Password = "user_password2";
    User user2;
    final ChangeOutputBoundary changeOutputBoudary = new ChangePresenter();
    final RegisterOutputBoundary registerOB = new RegisterPresenter();
    final DataInOut data = new DataInOut();
    final ProgramStateInputBoundary programStateInputBoundary = new ProgramStateManager();
    final DatabaseErrMsgPresenter dataBaseP = new DatabaseErrMsgPresenter();

    @Before
    public void createUserManager() {
        um = new UserManager(data, programStateInputBoundary);
        um.initialLoad(dataBaseP);
        um.createNewUser(user1Name, user1Password, registerOB);
        user2 = new User(user2Name, user2Password);

    }

    /**
     * Test createNewUser with unique username.
     */
    @Test
    public void testCreateNewUser() {
        assertEquals(user1Name, programStateInputBoundary.getCurrUser().getName());
        assertEquals(user1Password, programStateInputBoundary.getCurrUser().getPassword());
    }

    /**
     * Test changeInfo.
     */
    @Test
    public void testChangeInfo() {
        assertEquals(user1Name, programStateInputBoundary.getCurrUser().getName());
        assertEquals(user1Password, programStateInputBoundary.getCurrUser().getPassword());
        String newName = "user2_newName";
        String newPassword = "user2_newPassword";
        um.changeName(newName, changeOutputBoudary);
        assertEquals(newName, programStateInputBoundary.getCurrUser().getName());
        um.changePassword(newPassword);
        assertEquals(newPassword, programStateInputBoundary.getCurrUser().getPassword());
    }

}
