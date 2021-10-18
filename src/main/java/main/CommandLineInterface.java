package main;

import entity.Card;
import entity.Pack;
import entity.User;
import manager.UserManager;

import java.util.Objects;
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
        if (opt.equals("2")) {

            System.out.print("Please input your username: ");
            Scanner userInput = new Scanner(System.in);
            String userName = userInput.nextLine();
            System.out.print("Please input your password: ");
            String password1 = in.nextLine();
            System.out.print("Please input your password again: ");
            String password2 = in.nextLine();
            if (password2.equals(password1)) {
                um.createNewUser(userName, password1);
                um.logInUser(userName, password1);
            }
            System.out.println("Logging in...done! You are logged in through your new account!");
            System.out.println("Create a package by entering a package name:");
            String packName = in.nextLine();
            Pack newPack = new Pack(um.getCurrUser(), packName, packName);
            Card newCard = new Card("whatever", "computer", "a smart and cool machine");
            newPack.add(newCard);
            Card anotherCard = new Card("sowhat", "iphone", "an overpriced phone");
            newPack.add(anotherCard);
            System.out.println("enter y to checkout your cards");
            if (Objects.equals(in.nextLine(), "y")) {
                for (Card c : newPack.cardList) {
                    System.out.println(c.toString());
                }
            }
        }
        if (opt.equals("1")) {
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
