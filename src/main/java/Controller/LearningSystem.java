package Controller;

import entity.Pack;
import entity.User;
import use_case.LearnGenerator;
import use_case.ReviewGenerator;
import use_case.manager.CardManager;
import use_case.manager.PackManager;

public class LearningSystem {

    private final User currUser;
    private final Pack currPack;

    private final PackManager pm;
    private final CardManager cm;
    private final LearnGenerator lg;
    private final ReviewGenerator rg;
    private final ProgramState state;

    /***
     * A learning system for a user
     * @param user current user
     */
    public LearningSystem(User user) {
        this.currPack = null;
        this.currUser = user;
        this.pm = new PackManager();
        this.cm = new CardManager();
        this.lg = new LearnGenerator(this.currPack);
        this.rg = new ReviewGenerator(this.currPack);
        this.state = new ProgramState();
    }


}
