package command_line_interface;

import Controller.LearnController;
import Controller.LearningSystem;
import Controller.ProgramState;
import constants.Constants;
import entity.Card;

import java.util.Scanner;

public class LearnDisplay implements displayInterface{
    private ProgramState state;

    public LearnDisplay(){
        this.state = new ProgramState();
    }

    public void setState(ProgramState state) {
        this.state.setCurrUser(state.getCurrUser());
        this.state.setCurrPack(state.getCurrPack());
        this.state.setCurrCard(state.getCurrCard());
    }

    public ProgramState getState() {
        return state;
    }

    public void prompt() {
        Scanner in = new Scanner(System.in);
        LearnController con = new LearnController();
        System.out.println("Press any key to start learning, type 99 to quit...");
        String option = in.nextLine();
        if (!option.equals("99")) {
            for (Card c : con.learnableCardList(this.state.getCurrPack())) {

                this.state.setCurrCard(c);

                System.out.println(c.getTerm());
                System.out.println(c.getDefinition());
                System.out.println("How hard do you think it is to remember this definition? Type the most suitable option");
                System.out.println("1. Pretty easy, 2. Not very hard, 3. Super difficult");
                String memOpt = in.nextLine();
                con.updateMemProficiency(memOpt, c);
//                if(memOpt.equals("1")){
//                    System.out.println(c.getDefinition());
//                    System.out.println("Are you memorized correctly?, 1. right, 2. wrong");
//                    String TestOpt = in.nextLine();
//                    con.updateTestProficiency(TestOpt, c);
//                }
                System.out.print(Constants.CLEAR_CONSOLE);
                System.out.flush();
            }
        }
    }
}
