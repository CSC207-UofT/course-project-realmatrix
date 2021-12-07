package framework.gui.login_register;

import framework.gui.database_error.DatabaseErrorWindow;
import framework.gui.start.StartFrame;
import framework.gui.user.UserFrame;
import interface_adapter.controller.LogInOutController;
import interface_adapter.gateway.DataInOut;
import interface_adapter.gateway.IDataInOut;
import interface_adapter.presenters.DatabaseErrMsgPresenter;
import interface_adapter.presenters.LogInOutPresenter;
import use_case.constants.Constants;
import use_case.input_boundaries.LogInOutInputBoundary;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.manager.LogInOutManager;
import use_case.manager.ProgramStateManager;
import use_case.output_boundaries.DatabaseErrorOutputBoundary;
import use_case.output_boundaries.LogInOutOutputBoundary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A login frame which allows users to login.
 */
public class LoginFrame extends LogRegFrame implements ActionListener {
    private final JButton lgButton;       // Button that confirms login

    public LoginFrame(ProgramStateInputBoundary programStateInputBoundary) {
        super("Login", programStateInputBoundary);

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
     * - if successful: go to UserFrame
     * - if not: pop up a fail message window
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
                new UserFrame(username.getText(), this.programStateInputBoundary);
            } else {    // login fails
                JOptionPane.showMessageDialog(this,
                        Constants.LOGIN_FAIL_MSG,
                        Constants.LOGIN_FAIL,
                        JOptionPane.WARNING_MESSAGE);
            }
        }

        if (source == backButton) {
            new StartFrame(new ProgramStateManager());
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

        // construct logInOutManager
        LogInOutInputBoundary logInOutManager = new LogInOutManager(programStateInputBoundary, dataInOut);

        // construct LogInOutController
        LogInOutController controller = new LogInOutController(logInOutManager);

        // parameters for login method
        LogInOutOutputBoundary logPresenter = new LogInOutPresenter();
        DatabaseErrorOutputBoundary dbPresenter = new DatabaseErrMsgPresenter(new DatabaseErrorWindow());

        controller.login(name, password, logPresenter, dbPresenter);
        return logPresenter.getLogInOutResult();
    }


}
