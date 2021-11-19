package framework.command_line_interface;

import entity.Pack;
import interface_adapter.Controller.ProgramState;

import java.util.ArrayList;
import java.util.Scanner;

public class CreateOrChooseDisplay implements displayInterface {

    private ProgramState state;
    private final CreateDisplay createDisplay;
    private final ChooseDisplay chooseDisplay;

    public CreateOrChooseDisplay() {
        this.state = ProgramState.getState();
        this.chooseDisplay = new ChooseDisplay();
        this.createDisplay = new CreateDisplay();
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
        //TODO: cannot display the user's packages, but there
        ArrayList<Pack> userPackages = this.state.getCurrUser().getPackages();
        System.out.println("The following are all packages you have created:");
        for (Pack p : userPackages) {
            System.out.println(p.getName());
        }
        System.out.println();

        System.out.println("Create or Choose package? 1. create, 2. choose");
        Scanner in = new Scanner(System.in);
        String opt = in.nextLine();
        while (!(opt.equals("1") || opt.equals("2"))) {
            System.out.println("Create or Choose package? 1. create, 2. choose");
            opt = in.nextLine();
        }
        if (opt.equals("1")) {
            this.createDisplay.setState(this.state);
            this.createDisplay.prompt();
            this.state = this.createDisplay.getState();
        }
        if (opt.equals("2")) {
            this.chooseDisplay.setState(this.state);
            this.chooseDisplay.prompt();
            this.state = this.chooseDisplay.getState();
        }
    }
}
