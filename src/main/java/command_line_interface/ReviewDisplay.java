package command_line_interface;

import Controller.LearningSystem;
import Controller.ProgramState;
import constants.Constants;
import entity.Card;

import java.util.Scanner;

public class ReviewDisplay implements displayInterface{
    private ProgramState state;

    public ReviewDisplay() {
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
        LearningSystem con = new LearningSystem(state.getCurrUser());
        System.out.println(Constants.GREEN_BOLD_BRIGHT + "Press any key to start reviewing, type 99 to quit...");
        String option = in.nextLine();
        if (!option.equals("99")) {
            for (Card c : con.reviewableCardList(this.state.getCurrPack())) {
                System.out.println(c.getTerm());
                System.out.println("Can you recall the definition? Type the most suitable option");
                System.out.println("1. Clearly can, 2. Blur memory, 3. Totally forget");
                String memOpt = in.nextLine();
                con.updateMemProficiency(memOpt, c);
                if (memOpt.equals("1")) {
                    System.out.println(c.getDefinition());
                    System.out.println("Are you memorized correctly?, 1. right, 2. wrong");
                    String TestOpt = in.nextLine();
                    con.updateTestProficiency(TestOpt, c);
                }
                System.out.print(Constants.CLEAR_CONSOLE);
                System.out.flush();
            }
        }
    }
}