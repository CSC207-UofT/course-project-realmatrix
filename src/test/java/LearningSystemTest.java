import Controller.LearningSystem;
import entity.Card;
import entity.User;
import input_boundaries.UserInputBoundary;
import manager.UserManager;
import org.junit.Before;
import org.junit.Test;
import output_boundaries.RegisterOutputBoundary;
import presenters.RegisterPresenter;

public class LearningSystemTest {
    User user1;
    UserManager um = new UserManager();
    LearningSystem ls;
    Card c1;
    String c1Term = "c1Term";
    String c1Definition = "c1Definition";

    @Before
    public void createLearningSystem() throws Exception {
        RegisterOutputBoundary registerOB = new RegisterPresenter();
        um.createNewUser("user1Name", "user1Password", registerOB);
        for (User user : um.getItems().values()){
            user1 = user;
        }
        ls = new LearningSystem(user1);
        c1 = new Card("card1Id", c1Term, c1Definition);
    }

    /**
     * Test learnDisplay on c1.
     */
    @Test
    public void testLearnDisplay(){
        assertEquals(c1Term, ls.learnDisplay("t", c1));
        assertEquals(c1Definition, ls.learnDisplay("d", c1));
    }



}
