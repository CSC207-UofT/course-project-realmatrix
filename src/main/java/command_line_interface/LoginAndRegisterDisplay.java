package command_line_interface;

import Controller.ProgramState;
import constants.Constants;
import manager.LoginManager;
import manager.UserManager;

import java.util.Scanner;

public class LoginAndRegisterDisplay {

    private ProgramState state;

    public LoginAndRegisterDisplay(){
        this.state = new ProgramState();
    }

    public void setState(ProgramState state) {
        this.state.setCurrUser(state.getCurrUser());
        this.state.setCurrPack(state.getCurrPack());
        this.state.setCurrCard(state.getCurrCard());
    }

    public ProgramState getState() {
        return this.state;
    }

    public void prompt() throws Exception {
        System.out.println(Constants.ANSI_BLUE + "Log in or register?");
        System.out.println("1 for login, 2 for register, 99 for exit");
        Scanner in = new Scanner(System.in);
        String opt = in.nextLine();

        UserManager um = new UserManager();
        LoginManager lm = new LoginManager(um);

        if (opt.equals("99")) {
            System.out.println("Exit...");
        }
        while (!(opt.equals("1") || opt.equals("2"))) {
            System.out.println("1 for login, 2 for register, 99 for exit");
            opt = in.nextLine();
        }
        if (opt.equals("2")) {

            System.out.print("Please input your username: ");
            Scanner userInput = new Scanner(System.in);
            String userName = userInput.nextLine();
            System.out.print("Please input your password: ");
            String password1 = in.nextLine();
            System.out.print("Please input your password again: ");
            String password2 = in.nextLine();
            while (!password2.equals(password1)) {
                System.out.println("Password doesn't match, please re-input");
                System.out.print("Please input your password: ");
                password1 = in.nextLine();
                System.out.print("Please input your password again: ");
                password2 = in.nextLine();
            }
            um.createNewUser(userName, password1);
            lm.logInUser(userName, password1);

            this.state.setCurrUser(lm.getCurrUser());

            System.out.println("Logging in...done! You are logged in through your new account!");
        }
        if (opt.equals("1")) {
            System.out.print("Please input your username: ");
            Scanner userInput = new Scanner(System.in);
            String userName = userInput.nextLine();
            System.out.print("Please input your password: ");
            String password1 = in.nextLine();
            try {
                lm.logInUser(userName, password1);
            } catch (Exception Exception) {//to be changed more specific
                Exception.getMessage();
            }
            lm.logInUser(userName, password1);
            this.state.setCurrUser(lm.getCurrUser());
        }
    }
}
