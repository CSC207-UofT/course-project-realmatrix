package framework.GUI.login_register;

import entity.User;
import framework.GUI.BasicFrame;
import framework.GUI.start.StartFrame;
//import framework.GUI.user.UserFrame;
import interface_adapter.Controller.LogInOutController;
import interface_adapter.gateway.DataInOut;
import interface_adapter.gateway.IDataInOut;
import interface_adapter.presenters.DatabaseErrMsgPresenter;
import interface_adapter.presenters.LogInOutPresenter;
import use_case.input_boundaries.LogInOutInputBoundary;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.input_boundaries.UserInputBoundary;
import use_case.manager.LogInOutManager;
import use_case.manager.ProgramStateManager;
import use_case.manager.UserManager;
import use_case.output_boundaries.DatabaseErrorOutputBoundary;
import use_case.output_boundaries.LogInOutOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Objects;

/**
 * A login frame which allows users to login.
 */
public class LoginFrame extends LogRegFrame implements ActionListener {
    private final JButton lgButton;       // Button that confirms login

    public LoginFrame() {
        super("Login");

        // Create component: login button
        lgButton = new JButton("Login");
        lgButton.addActionListener(this);

        // Layout Components
        layoutRestComp();
        add(panel);
        setVisible(true);
    }

    /**
     * Layout all components in the panel and add them into the panel.
     */
    @Override
    protected void layoutRestComp() {
        lgButton.setBounds(backButton.getX() + 150, backButton.getY(),
                backButton.getWidth(), backButton.getHeight());
        add(lgButton);
    }


    /**
     * Set actions for clicking login and back buttons.
     * Clicking login button: check login successful or not
     *      - if successful: go to UserFrame
     *      - if not: pop up a fail message window
     * Clicking back button: back to the start frame
     *
     * @param e An ActionEvent item
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == lgButton) {
            if (check()) {  // login is successful
                setVisible(false);
                //TODO: new UserFrame
            } else {    // login fails
                JOptionPane.showMessageDialog(this,
                        "Wrong password  OR  the username doesn't exist", // TODO: constant
                        "Login Fails",
                        JOptionPane.WARNING_MESSAGE);
            }
        }

        if (source == backButton) {
            new StartFrame();
            setVisible(false);
        }
    }

    /**
     * Helper method for actionPerformed
     * Check if the user enters the correct username and password for login.
     */

    protected boolean check() {
        String name = username.getText();
        String password = String.valueOf(pw.getPassword());

        // parameters for logInOutManager
        IDataInOut dataInOut = new DataInOut();
        ProgramStateInputBoundary ps = new ProgramStateManager();

        // construct logInOutManager
        LogInOutInputBoundary logInOutManager = new LogInOutManager(ps, dataInOut);

        // construct LogInOutController
        LogInOutController controller = new LogInOutController(logInOutManager);

        // parameters for login method
        LogInOutOutputBoundary logPresenter = new LogInOutPresenter();
        DatabaseErrorOutputBoundary dbPresenter = new DatabaseErrMsgPresenter();

        controller.login(name, password, logPresenter, dbPresenter);
        return logPresenter.getLogInOutResult();
    }

    // Test
    public static void main(String[] args) {
        new StartFrame();
    }
}
