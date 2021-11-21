//package framework.command_line_interface;
//
//import entity.Card;
//import interface_adapter.Controller.ProgramState;
//import interface_adapter.Controller.ReviewController;
//import use_case.constants.Constants;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class ReviewDisplay implements displayInterface {
//    private final ProgramState state;
//
//    public ReviewDisplay() {
//        this.state = new ProgramState();
//    }
//
//    public void setState(ProgramState state) {
//        this.state.setCurrUser(state.getCurrUser());
//        this.state.setCurrPack(state.getCurrPack());
//        this.state.setCurrCard(state.getCurrCard());
//    }
//
//    public ProgramState getState() {
//        return state;
//    }
//
//    public void prompt() {
//        Scanner in = new Scanner(System.in);
//        ReviewController con = new ReviewController(this.state.getCurrPack());
//        System.out.println("Press any key to start reviewing, type 99 to quit...");
//        String option = in.nextLine();
//        ArrayList<Card> temp = new ArrayList<>();
//        if (!option.equals("99")) {
//
//            try {
//                temp = con.reviewableCardList();
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
////            System.out.println("test:"+ temp.toString());
//            System.out.println();
//            for (Card c : temp) {
//                System.out.println(c.getTerm());
//                System.out.println("Can you recall the definition? Type the most suitable option");
//                System.out.println("1. Clearly can, 2. Blur memory, 3. Totally forget");
//                String memOpt = in.nextLine();
//                con.updateMemProficiency(memOpt, c);
//                if (memOpt.equals("1")) {
//                    System.out.println(c.getDefinition());
//                    System.out.println("Are you memorized correctly?, 1. right, 2. wrong");
//                    String TestOpt = in.nextLine();
//                    con.updateTestProficiency(TestOpt, c);
//                }
//                System.out.print(Constants.CLEAR_CONSOLE);
//                System.out.flush();
//            }
//        }
//    }
//}