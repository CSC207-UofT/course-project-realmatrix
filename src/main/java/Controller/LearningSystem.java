package Controller;

import entity.*;
import manager.CardManager;
import manager.PackManager;
import use_case.ReviewGenerator;
import use_case.LearnGenerator;
import Controller.ProgramState;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Properties;

public class LearningSystem {

    private User currUser;
    private Pack currPack;

    private PackManager pm;
    private CardManager cm;
    private LearnGenerator lg;
    private ReviewGenerator rg;
    private ProgramState state;

    /***
     * A learning system for a user
     * @param user current user
     */
    public LearningSystem(User user){
        this.currPack = null;
        this.currUser = user;
        this.pm = new PackManager();
        this.cm = new CardManager();
        this.lg = new LearnGenerator(this.currPack);
        this.rg = new ReviewGenerator(this.currPack);
        this.state = new ProgramState();
    }





}
