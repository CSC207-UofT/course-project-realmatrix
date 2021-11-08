package command_line_interface;

import Controller.ProgramState;
import constants.Constants;

import java.util.Scanner;

public class CommandLineInterface {

    private ProgramState state;

    private CreateOrChooseDisplay createOrChooseDisplay;
    private LoginAndRegisterDisplay loginAndRegisterDisplay;
    private LearnAndReviewDisplay learnAndReviewDisplay;


    public CommandLineInterface(){
        this.state = new ProgramState();

        this.createOrChooseDisplay = new CreateOrChooseDisplay();
        this.loginAndRegisterDisplay = new LoginAndRegisterDisplay();
        this.learnAndReviewDisplay = new LearnAndReviewDisplay();
    }


    /**
     * The command line display
     */
    public void display() throws Exception {
        this.loginAndRegisterDisplay.setState(this.state);
        this.loginAndRegisterDisplay.prompt();
        this.state = this.loginAndRegisterDisplay.getState();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Type 99 to quit, else to continue");
        String quit = scanner.nextLine();
        while(!quit.equals("99")){
            System.out.println(Constants.ANSI_YELLOW + "Current State is: \n" + this.state.toString() + "\n");
            this.createOrChooseDisplay.setState(this.state);
            this.createOrChooseDisplay.prompt();
            this.state = this.createOrChooseDisplay.getState();

            this.learnAndReviewDisplay.setState(this.state);
            this.learnAndReviewDisplay.prompt();
            this.state = this.learnAndReviewDisplay.getState();
        }
    }
}



