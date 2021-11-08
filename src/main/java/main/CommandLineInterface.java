package main;

import entity.Card;
import entity.Pack;
import entity.User;
import manager.CardManager;
import manager.UserManager;
import constants.Constants;
import Controller.LearningSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class CommandLineInterface {

    private Pack pack;

    /**
     * Simple prompt for user login or register
     */
    public void prompt() throws Exception {
        System.out.println(Constants.ANSI_BLUE + "Log in or register?");
        System.out.println("1 for login, 2 for register, 99 for exit");
        Scanner in = new Scanner(System.in);
        String opt = in.nextLine();
        UserManager um = new UserManager();
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
            um.logInUser(userName, password1);
            System.out.println("Logging in...done! You are logged in through your new account!");
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
    }

    public void createOrChoosePrompt() throws Exception {
        System.out.println("Create or Choose package? 1. create, 2. choose");
        Scanner in = new Scanner(System.in);
        String opt = in.nextLine();
        while(!(opt.equals("1") || opt.equals("2"))){
            System.out.println("Create or Choose package? 1. create, 2. choose");
        }
        if(opt.equals("1")){
            this.createPackagePrompt();
        }
        if(opt.equals("2")){
            this.choosePackPrompt();
        }
    }

    public void createPackagePrompt() throws Exception {
        System.out.println(Constants.ANSI_CYAN + "Create a package by entering a package name:");
        Scanner in = new Scanner(System.in);
        String packName = in.nextLine();
        Pack newPack = new Pack(packName, packName);

        this.pack = newPack;
        System.out.println("enter y to checkout your cards, a to add new card, q to quit");
        String packOpt = in.nextLine();
        while (!packOpt.equals("q")) {
            if (packOpt.equals("a")) {
                System.out.println("press any key to add new card, 99 for quit...");
                String cardOpt = in.nextLine();
                CardManager cm = new CardManager();
                int j = 0;
                while (!cardOpt.equals("99")) {
                    System.out.print(Constants.ANSI_YELLOW + "Please type your Term: ");
                    String term = in.nextLine();
                    System.out.print("Please type your Definition: ");
                    String def = in.nextLine();
                    Card card = cm.createNewCard(term, def);
                    newPack.addCard(card);
                    j++;
                    System.out.println();
                    System.out.println(Constants.ANSI_CYAN + "press any key to add new card, 99 for quit...");
                    cardOpt = in.nextLine();
                }
//                    Card newCard = new Card("whatever", "computer", "a smart and cool machine");
//                    newPack.add(newCard);
//                    Card anotherCard = new Card("sowhat", "iphone", "an overpriced phone");
//                    newPack.add(anotherCard);
                System.out.printf("%d card added", j);
                System.out.println("\n");
                System.out.println(Constants.ANSI_CYAN + "enter y to checkout your cards, a to add new card, q to quit");
                packOpt = in.nextLine();
            }

            if (packOpt.equals("y")) {
                int i = 0;
                for (Card c : newPack.getCards()) {
                    System.out.println();
                    System.out.println(Constants.GREEN_BOLD_BRIGHT + c.toString());
                    i++;
                }
                System.out.printf(Constants.GREEN_BOLD_BRIGHT + "%d cards displayed", i);
                System.out.println("\n");
                System.out.println(Constants.ANSI_CYAN + "enter y to checkout your cards, a to add new card, q to quit");
                packOpt = in.nextLine();
            }
            if (packOpt.equals("q")) {
                System.out.println(Constants.ANSI_RED);
            }

        }
    }


    /**
     * Review or learn a package.
     */
    public void choosePackPrompt(){
        // TODO: Find the user who is using the program.
        User u = new User("abc", "abc", "abc");

        // TODO: Print the list of packs of the user who is using the program.
        ArrayList<Pack>  userPackages = u.getPackList();
        System.out.println("The following are all packages you have created:" + userPackages);

        // Find the chosen package.
        System.out.println(Constants.ANSI_CYAN + "Choose a package by entering a package name:");
        Scanner in = new Scanner(System.in);
        String packName = in.nextLine();
        Pack chosenPack = null;
        for (Pack p : userPackages) {
            if (p.getName().equals(packName)) {
                chosenPack = p;
            }
        }
        this.pack = chosenPack;

        // Let user choose to learn or review the package.
        System.out.println("enter l to learn this package, r to review this package, q to quit");
        String packOpt = in.nextLine();
        while (!packOpt.equals("q")) {
            // if user choose to learn, then call the helper method taskPrompt1()
            if (packOpt.equals("l")) {taskPrompt1();}
            // if user choose to review, then call the helper method taskPrompt2()
            if (packOpt.equals("r")) {taskPrompt2();}
        }
        System.out.println(Constants.ANSI_RED);
    }

    /**
     * A helper method to deal with learning process.
     */
    public void taskPrompt1() {
        Scanner in = new Scanner(System.in);
        LearningSystem con = new LearningSystem();
        System.out.println(Constants.GREEN_BOLD_BRIGHT + "Press any key to start learning, type 99 to quit...");
        String option = in.nextLine();
        if (!option.equals("99")) {
            for (Card c : con.learnableCardList()) {
                System.out.println(c.getTerm());
                System.out.println(c.getDefinition());
                System.out.println("How hard do you think it is to remember this definition? Type the most suitable option");
                System.out.println("1. Pretty easy, 2. Not very hard, 3. Super difficult");
                String memOpt = in.nextLine();
                con.updateMemProficiency(memOpt, c);
                if(memOpt.equals("1")){
                    System.out.println(c.getDefinition());
                    System.out.println("Are you memorized correctly?, 1. right, 2. wrong");
                    String TestOpt = in.nextLine();
                    con.updateTestProficiency(TestOpt, c);
                }
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        }
    }

    /**
     * A helper method to deal with reviewing process.
     */
    public void taskPrompt2() {
        Scanner in = new Scanner(System.in);
        LearningSystem con = new LearningSystem();
        System.out.println(Constants.GREEN_BOLD_BRIGHT + "Press any key to start reviewing, type 99 to quit...");
        String option = in.nextLine();
        if (!option.equals("99")) {
            for (Card c : con.reviewableCardList()) {
                System.out.println(c.getTerm());
                System.out.println("Can you recall the definition? Type the most suitable option");
                System.out.println("1. Clearly can, 2. Blur memory, 3. Totally forget");
                String memOpt = in.nextLine();
                con.updateMemProficiency(memOpt, c);
                if(memOpt.equals("1")){
                    System.out.println(c.getDefinition());
                    System.out.println("Are you memorized correctly?, 1. right, 2. wrong");
                    String TestOpt = in.nextLine();
                    con.updateTestProficiency(TestOpt, c);
                }
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        }
    }

    }




