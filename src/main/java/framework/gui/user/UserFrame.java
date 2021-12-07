package framework.gui.user;

import framework.gui.BasicFrame;
import framework.gui.pack.PackListFrame;
import framework.gui.start.StartFrame;
import interface_adapter.controller.LogInOutController;
import interface_adapter.gateway.DataInOut;
import interface_adapter.gateway.IDataInOut;
import interface_adapter.presenters.LogInOutPresenter;
import use_case.constants.Constants;
import use_case.input_boundaries.LogInOutInputBoundary;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.manager.LogInOutManager;
import use_case.output_boundaries.LogInOutOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A user frame where users can choose to
 * - checkout packages
 * - logout
 * - change username
 * - change password
 */
public class UserFrame extends BasicFrame implements ActionListener {
    final String username;
    final JPanel userPanel;
    final JLabel message;
    final JButton signOutButton;
    final JButton changeNameButton;
    final JButton changePasswordButton;
    final JButton checkOutPackagesButton;

    /**
     * Build a UserFrame.
     */
    public UserFrame(String username, ProgramStateInputBoundary programStateInputBoundary) {
        super(username + "'s Home Page", programStateInputBoundary);
        this.username = username;
        // 1. Create components shown on the frame
        userPanel = new JPanel(new GridLayout(5, 1));

        message = new JLabel(username + "'s Home Page", SwingConstants.CENTER);
        message.setFont(new Font("verdana", Font.BOLD | Font.ITALIC, 38));

        checkOutPackagesButton = new JButton(Constants.CHECK_OUT_PACKAGE);
        checkOutPackagesButton.addActionListener(this);

        signOutButton = new JButton(Constants.SIGN_OUT_BTN);
        signOutButton.addActionListener(this);

        changeNameButton = new JButton(Constants.CHANGE_NAME_BTN);
        changeNameButton.addActionListener(this);

        changePasswordButton = new JButton(Constants.CHANGE_PW_BTN);
        changePasswordButton.addActionListener(this);

        // 2. Add components to the panel
        addComp();

        // 3. Add the panel to the frame
        add(userPanel);

        setVisible(true);
    }

    /**
     * Add all components into panel.
     */
    private void addComp() {
        userPanel.add(message);
        userPanel.add(checkOutPackagesButton);
        userPanel.add(signOutButton);
        userPanel.add(changeNameButton);
        userPanel.add(changePasswordButton);
    }

    /**
     * Trigger opening of new frame and closure of the start frame when pressing button
     *
     * @param e ActionEvent item
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signOutButton) {
            signOff();
        } else if (e.getSource() == changeNameButton) {
            new ChangeUsernameFrame(username, this.programStateInputBoundary);
        } else if (e.getSource() == changePasswordButton) {
            new ChangePasswordFrame(username, this.programStateInputBoundary);
        } else if (e.getSource() == checkOutPackagesButton) {
            new PackListFrame(programStateInputBoundary);
        }
        setVisible(false);
    }

    /**
     * Helper method for performing signing off
     */
    private void signOff() {
        // Construct logInOutController
        IDataInOut dataInOut = new DataInOut();
        LogInOutInputBoundary logManager = new LogInOutManager(programStateInputBoundary, dataInOut);
        LogInOutController logController = new LogInOutController(logManager);
        // CheckSignOff
        LogInOutOutputBoundary logPresenter = new LogInOutPresenter();
        logController.signOff(logPresenter);

        if (logPresenter.getLogInOutResult()) {    // log out success
            new StartFrame(programStateInputBoundary);
        }
    }

}
