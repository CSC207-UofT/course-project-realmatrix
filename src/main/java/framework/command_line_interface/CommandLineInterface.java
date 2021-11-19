package framework.command_line_interface;

import interface_adapter.Controller.ProgramState;

import java.util.Scanner;

public class CommandLineInterface {

    private ProgramState state;

    private final CreateOrChooseDisplay createOrChooseDisplay;
    private final LoginAndRegisterDisplay loginAndRegisterDisplay;
    private final LearnAndReviewDisplay learnAndReviewDisplay;


    public CommandLineInterface() {
        this.state = ProgramState.getState();

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
        while (!quit.equals("99")) {
            this.createOrChooseDisplay.setState(this.state);
            this.createOrChooseDisplay.prompt();
            this.state = this.createOrChooseDisplay.getState();

            this.learnAndReviewDisplay.setState(this.state);
            this.learnAndReviewDisplay.prompt();
            this.state = this.learnAndReviewDisplay.getState();
        }
    }
}



