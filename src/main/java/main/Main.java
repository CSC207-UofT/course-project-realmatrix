package main;

import GUI.start.StartFrame;
import command_line_interface.CommandLineInterface;

public class Main {

    public static void main(String[] args) throws Exception {
//        CommandLineInterface cli = new CommandLineInterface();
//        cli.display();

        // Use GUI
        new StartFrame();
    }
}