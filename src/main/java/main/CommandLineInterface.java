package main;

import entity.Card;
import entity.Pack;
import entity.User;
import manager.CardManager;
import manager.UserManager;

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class CommandLineInterface {


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN

    /**
     * Simple prompt for user login or register
     */
    public static boolean prompt() throws Exception {
        System.out.println(ANSI_BLUE+"Log in or register?");
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
            System.out.println(ANSI_CYAN+ "Create a package by entering a package name:");
            String packName = in.nextLine();
            Pack newPack = new Pack(packName, packName);

            System.out.println("enter y to checkout your cards, a to add new card, q to quit");
            String packOpt = in.nextLine();
            while(!packOpt.equals("q")) {
                if (packOpt.equals("a")) {
                    System.out.println("press any key to add new card, 99 for quit...");
                    String cardOpt = in.nextLine();
                    CardManager cm = new CardManager();
                    int j = 0;
                    while(!cardOpt.equals("99")){
                        System.out.print(ANSI_YELLOW+"Please type your Term: ");
                        String term = in.nextLine();
                        System.out.print("Please type your Definition: ");
                        String def = in.nextLine();
                        Card card = cm.createNewCard(term,def);
                        newPack.add(card);
                        j++;
                        System.out.println();
                        System.out.println(ANSI_CYAN+"press any key to add new card, 99 for quit...");
                        cardOpt = in.nextLine();
                    }
//                    Card newCard = new Card("whatever", "computer", "a smart and cool machine");
//                    newPack.add(newCard);
//                    Card anotherCard = new Card("sowhat", "iphone", "an overpriced phone");
//                    newPack.add(anotherCard);
                    System.out.printf("%d card added", j);
                    System.out.println("\n");
                    System.out.println(ANSI_CYAN+ "enter y to checkout your cards, a to add new card, q to quit");
                    packOpt = in.nextLine();
                    }

                if (packOpt.equals("y")) {
                    int i = 0;
                    for (Card c : newPack.getCards()) {
                        System.out.println();
                        System.out.println(GREEN_BOLD_BRIGHT + c.toString());
                        i ++;
                    }
                    System.out.printf(GREEN_BOLD_BRIGHT+"%d cards displayed", i);
                    System.out.println("\n");
                    System.out.println(ANSI_CYAN+"enter y to checkout your cards, a to add new card, q to quit");
                    packOpt = in.nextLine();
                }
                if (packOpt.equals("q")) {
                    System.out.println(ANSI_RED);
                    return false;
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
