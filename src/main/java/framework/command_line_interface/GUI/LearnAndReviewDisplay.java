package framework.command_line_interface.GUI;

import use_case.constants.Constants;
import interface_adapter.Controller.ProgramState;

import java.util.Scanner;

public class LearnAndReviewDisplay implements displayInterface {

    private ProgramState state;
    private final LearnDisplay learnDisplay;
    private final ReviewDisplay reviewDisplay;

    public LearnAndReviewDisplay() {
        this.state = new ProgramState();
        this.learnDisplay = new LearnDisplay();
        this.reviewDisplay = new ReviewDisplay();
    }

    public void setState(ProgramState state) {
        this.state.setCurrUser(state.getCurrUser());
        this.state.setCurrPack(state.getCurrPack());
        if (state.getCurrCard() != null) {
            this.state.setCurrCard(state.getCurrCard());
        }
    }

    public ProgramState getState() {
        return state;
    }

    public void prompt() {
        // Let user choose to learn or review the package.
        Scanner in = new Scanner(System.in);
        System.out.println("enter l to learn this package, r to review this package, q to quit");
        String packOpt = in.nextLine();
        while (!packOpt.equals("q")) {
            // if user choose to learn, then call the helper method taskPrompt1()
            if (packOpt.equals("l")) {
                //TODO Exception java.lang.NullPointerException need to be handled
//                System.out.println(this.state);
//
                this.learnDisplay.setState(this.state);
                this.learnDisplay.prompt();
                this.state = this.learnDisplay.getState();
                System.out.println("enter l to learn this package, r to review this package, q to quit");
                packOpt = in.nextLine();
            }
            // if user choose to review, then call the helper method taskPrompt2()
            if (packOpt.equals("r")) {
                this.reviewDisplay.setState(this.state);
                this.reviewDisplay.prompt();
                this.state = this.learnDisplay.getState();
                System.out.println("enter l to learn this package, r to review this package, q to quit");
                packOpt = in.nextLine();
            }
        }
        System.out.print(Constants.CLEAR_CONSOLE);
        System.out.flush();
    }

//    public static void main(String[] args) throws Exception {
//        LearnAndReviewDisplay lr = new LearnAndReviewDisplay();
//        User u = new User("123","yifan","123");
//        Pack p = new Pack("p1","vocab");
//        Card c1 = new Card("c1","apple","a fruit");
//        p.addCard(c1);
//        ProgramState ps = new ProgramState();
//
//        ps.setCurrUser(u);
//        ps.setCurrPack(p);
//
//        lr.setState(ps);
//        lr.prompt();
//    }
}
