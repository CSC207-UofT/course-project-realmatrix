package framework.gui;

import interface_adapter.controller.ProgramStateController;
import use_case.input_boundaries.ProgramStateInputBoundary;

import javax.swing.*;

/**
 * A basic frame that all other frames can extend to.
 */
public class BasicFrame extends JFrame {
    protected final ProgramStateInputBoundary programStateInputBoundary;
    protected final ProgramStateController psController;

    public BasicFrame(String title, ProgramStateInputBoundary programStateInputBoundary) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);
        this.programStateInputBoundary = programStateInputBoundary;
        psController = new ProgramStateController(programStateInputBoundary);
    }
}
