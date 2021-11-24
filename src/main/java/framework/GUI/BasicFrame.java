package framework.GUI;

import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.manager.ProgramStateManager;

import javax.swing.*;

public class BasicFrame extends JFrame {
    protected final ProgramStateInputBoundary programStateInputBoundary;

    public BasicFrame(String title, ProgramStateInputBoundary programStateInputBoundary) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);
        this.programStateInputBoundary = programStateInputBoundary;
    }
}
