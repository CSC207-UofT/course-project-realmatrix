package main;

import entity.User;
import manager.UserManager;

import java.util.Scanner;

public class CommandLineInterface {

    /**
     * Simple prompt for user login or register
     */
    public static boolean prompt() throws Exception {
        System.out.println("Log in or register?");
        System.out.println("1 for login, 2 for register, 99 for exit");
        Scanner in = new Scanner(System.in);
        String opt = in.nextLine();
        UserManager um = new UserManager();
        if (opt.equals("99")) {
            System.out.println("Exit...");
            return false;
        }
        while (!(opt.equals("1") || opt.equals("2"))) {
            System.out.println("1 for login, 2 for register, 99 for exit");
            opt = in.nextLine();
        }
        if (opt.equals("1")) {

            System.out.print("Please input your username: ");
            Scanner userInput = new Scanner(System.in);
            String userName = userInput.nextLine();
            System.out.print("Please input your password: ");
            String password1 = in.nextLine();
            System.out.print("Please input your password again: ");
            String password2 = in.nextLine();
            if (password2.equals(password1)) {
                um.createNewUser(userName, password1);
            }
        }
        if (opt.equals("2")) {
            System.out.print("Please input your username: ");
            Scanner userInput = new Scanner(System.in);
            String userName = userInput.nextLine();
            System.out.print("Please input your password: ");
            String password1 = in.nextLine();
            try {
                um.logInUser(userName, password1);
            } catch (Exception Exception) {//to be changed more specific
                um.SignOffUser();
            }
            um.logInUser(userName, password1);
        }
        return true;
    }

}
