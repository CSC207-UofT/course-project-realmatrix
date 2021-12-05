package main;


import entity.ProgramState;
import entity.User;
import framework.GUI.start.StartFrame;
import use_case.manager.ProgramStateManager;

public class Main {

    public static void main(String[] args) throws Exception {
//        CommandLineInterface cli = new CommandLineInterface();
//        cli.display();

        new StartFrame(new ProgramStateManager());
    }
}