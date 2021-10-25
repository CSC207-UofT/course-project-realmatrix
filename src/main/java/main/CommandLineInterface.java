package main;

import entity.Card;
import entity.Pack;
import manager.CardManager;
import manager.UserManager;
import constants.Constants;
import Controller.System;

import java.util.Scanner;

public class CommandLineInterface {

    private Pack pack;

    /**
     * Simple prompt for user login or register
     */
    public void prompt() throws Exception {
        java.lang.System.out.println(Constants.ANSI_BLUE + "Log in or register?");
        java.lang.System.out.println("1 for login, 2 for register, 99 for exit");
        Scanner in = new Scanner(java.lang.System.in);
        String opt = in.nextLine();
        UserManager um = new UserManager();
        if (opt.equals("99")) {
            java.lang.System.out.println("Exit...");
        }
        while (!(opt.equals("1") || opt.equals("2"))) {
            java.lang.System.out.println("1 for login, 2 for register, 99 for exit");
            opt = in.nextLine();
        }
        if (opt.equals("2")) {

            java.lang.System.out.print("Please input your username: ");
            Scanner userInput = new Scanner(java.lang.System.in);
            String userName = userInput.nextLine();
            java.lang.System.out.print("Please input your password: ");
            String password1 = in.nextLine();
            java.lang.System.out.print("Please input your password again: ");
            String password2 = in.nextLine();
            while (!password2.equals(password1)) {
                java.lang.System.out.println("Password doesn't match, please re-input");
                java.lang.System.out.print("Please input your password: ");
                password1 = in.nextLine();
                java.lang.System.out.print("Please input your password again: ");
                password2 = in.nextLine();
            }
            um.createNewUser(userName, password1);
            um.logInUser(userName, password1);
            java.lang.System.out.println("Logging in...done! You are logged in through your new account!");
        }
        if (opt.equals("1")) {
            java.lang.System.out.print("Please input your username: ");
            Scanner userInput = new Scanner(java.lang.System.in);
            String userName = userInput.nextLine();
            java.lang.System.out.print("Please input your password: ");
            String password1 = in.nextLine();
            try {
                um.logInUser(userName, password1);
            } catch (Exception Exception) {//to be changed more specific
                um.SignOffUser();
            }
            um.logInUser(userName, password1);
        }
    }

    public void createOrChoosePrompt() {
        java.lang.System.out.println("Create or Choose package? 1. create, 2. choose");
    }

    public void createPackagePrompt() throws Exception {
        java.lang.System.out.println(Constants.ANSI_CYAN + "Create a package by entering a package name:");
        Scanner in = new Scanner(java.lang.System.in);
        String packName = in.nextLine();
        Pack newPack = new Pack(packName, packName);

        this.pack = newPack;
        java.lang.System.out.println("enter y to checkout your cards, a to add new card, q to quit");
        String packOpt = in.nextLine();
        while (!packOpt.equals("q")) {
            if (packOpt.equals("a")) {
                java.lang.System.out.println("press any key to add new card, 99 for quit...");
                String cardOpt = in.nextLine();
                CardManager cm = new CardManager();
                int j = 0;
                while (!cardOpt.equals("99")) {
                    java.lang.System.out.print(Constants.ANSI_YELLOW + "Please type your Term: ");
                    String term = in.nextLine();
                    java.lang.System.out.print("Please type your Definition: ");
                    String def = in.nextLine();
                    Card card = cm.createNewCard(term, def);
                    newPack.addCard(card);
                    j++;
                    java.lang.System.out.println();
                    java.lang.System.out.println(Constants.ANSI_CYAN + "press any key to add new card, 99 for quit...");
                    cardOpt = in.nextLine();
                }
//                    Card newCard = new Card("whatever", "computer", "a smart and cool machine");
//                    newPack.add(newCard);
//                    Card anotherCard = new Card("sowhat", "iphone", "an overpriced phone");
//                    newPack.add(anotherCard);
                java.lang.System.out.printf("%d card added", j);
                java.lang.System.out.println("\n");
                java.lang.System.out.println(Constants.ANSI_CYAN + "enter y to checkout your cards, a to add new card, q to quit");
                packOpt = in.nextLine();
            }

            if (packOpt.equals("y")) {
                int i = 0;
                for (Card c : newPack.getCards()) {
                    java.lang.System.out.println();
                    java.lang.System.out.println(Constants.GREEN_BOLD_BRIGHT + c.toString());
                    i++;
                }
                java.lang.System.out.printf(Constants.GREEN_BOLD_BRIGHT + "%d cards displayed", i);
                java.lang.System.out.println("\n");
                java.lang.System.out.println(Constants.ANSI_CYAN + "enter y to checkout your cards, a to add new card, q to quit");
                packOpt = in.nextLine();
            }
            if (packOpt.equals("q")) {
                java.lang.System.out.println(Constants.ANSI_RED);
            }

        }
    }


    /**
     * choose Pack
     *
     * @return void
     * TODO: implement needed
     */
    public void choosePackPrompt(){
        
    }


    public void taskPrompt() {
        Scanner in = new Scanner(java.lang.System.in);
        System con = new System();
        java.lang.System.out.println(Constants.GREEN_BOLD_BRIGHT + "Press any key to start reviewing, type 99 to quit...");
        String option = in.nextLine();
        if (option != "99") {
            for (Card c : con.reviewableCardList()) {
                java.lang.System.out.println(c.getTerm());
                java.lang.System.out.println("Can you recall the definition? Type the most suitable option");
                java.lang.System.out.println("1. Clearly can, 2. Blur memory, 3. Totally forget");
                String memOpt = in.nextLine();
                con.updateMemProficiency(memOpt, c);
                java.lang.System.out.println(c.getDefinition());
                java.lang.System.out.println("Are you memorized correctly?, 1. right, 2. wrong");
                String TestOpt = in.nextLine();
                con.updateTestProficiency(TestOpt, c);
            }
        }
    }
}
