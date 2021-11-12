package command_line_interface;

import Controller.ProgramState;
import constants.Constants;
import entity.Card;
import entity.Pack;
import manager.CardManager;
import manager.PackManager;

import java.util.Scanner;

public class CreateDisplay implements displayInterface{

    private ProgramState state;

    public CreateDisplay(){
        this.state = new ProgramState();
    }

    public void setState(ProgramState state) {
        this.state.setCurrUser(state.getCurrUser());
        this.state.setCurrPack(state.getCurrPack());
        this.state.setCurrCard(state.getCurrCard());
    }

    public ProgramState getState() {
        return state;
    }

    public void prompt() throws Exception {
        System.out.println("Create a package by entering a package name:");
        Scanner in = new Scanner(System.in);
        String packName = in.nextLine();
        PackManager pm = new PackManager();
        Pack newPack = pm.createNewPack(packName);

        this.state.setCurrPack(newPack);

        System.out.println("enter y to checkout your cards, a to add new card, q to quit");
        String packOpt = in.nextLine();
        while (!packOpt.equals("q")) {
            if (packOpt.equals("a")) {
                System.out.println("press any key to add new card, 99 for quit...");
                String cardOpt = in.nextLine();
                CardManager cm = new CardManager();
                int j = 0;
                while (!cardOpt.equals("99")) {
                    System.out.print("Please type your Term: ");
                    String term = in.nextLine();
                    System.out.print("Please type your Definition: ");
                    String def = in.nextLine();
                    Card card = cm.createNewCard(term, def);
                    newPack.addCard(card);

                    this.state.setCurrCard(card);

                    j++;
                    System.out.println();
                    System.out.println("press any key to add new card, 99 for quit...");
                    cardOpt = in.nextLine();
                }
                this.state.getCurrUser().createPackage(newPack);

                System.out.printf("%d card added", j);
                System.out.println("\n");
                System.out.println("enter y to checkout your cards, a to add new card, q to quit");
                packOpt = in.nextLine();
            }

            if (packOpt.equals("y")) {
                int i = 0;
                for (Card c : newPack.getCards()) {
                    System.out.println();
                    System.out.println(c.toString());
                    i++;
                }
                System.out.printf("%d cards displayed", i);
                System.out.println("\n");
                System.out.println("enter y to checkout your cards, a to add new card, q to quit");
                packOpt = in.nextLine();
            }
            if (packOpt.equals("q")) {
                System.out.println();
                break;
            }

        }
    }
}
