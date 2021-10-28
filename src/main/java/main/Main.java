package main;

public class Main {

    public static void main(String[] args) throws Exception {
        CommandLineInterface cli = new CommandLineInterface();
        cli.prompt();
        cli.createPackagePrompt();
    }
}