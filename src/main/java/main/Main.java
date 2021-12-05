package main;


import framework.GUI.start.StartFrame;
import use_case.manager.ProgramStateManager;

public class Main {

    public static void main(String[] args) {

        new StartFrame(new ProgramStateManager());
    }
}