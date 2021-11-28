package framework.GUI.user;

import entity.User;
import framework.GUI.BasicFrame;
import interface_adapter.Controller.UserController;
import interface_adapter.gateway.DataInOut;
import interface_adapter.gateway.IDataInOut;
import interface_adapter.presenters.ChangePresenter;
import interface_adapter.presenters.DatabaseErrMsgPresenter;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.input_boundaries.UserInputBoundary;
import use_case.manager.UserManager;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.DatabaseErrorOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A frame for changing password.
 */
public class ChangePasswordFrame extends BasicFrame implements ActionListener {
    final String username;
    final JPanel changePasswordPanel;
    final JLabel message;
    final JTextField newPassword;
    final JLabel newPasswordLabel;
    final JButton finishButton;

    /**
     * Build a StartFrame.
     */
    public ChangePasswordFrame(String username, ProgramStateInputBoundary programStateInputBoundary) {
        super("Change password", programStateInputBoundary);
        this.username = username;
        // 1. Create components shown on the frame
        changePasswordPanel = new JPanel(new GridLayout(3, 1));

        message = new JLabel("Change password", SwingConstants.CENTER);
        message.setFont(new Font("verdana", Font.BOLD | Font.ITALIC, 38));

        newPassword = new JTextField(20);
        newPasswordLabel = new JLabel("new password: ", JLabel.TRAILING); // TODO: label doesn't show up. fix this
        newPassword.add(newPasswordLabel);

        finishButton = new JButton("done");
        finishButton.addActionListener(this);

        // 2. Add components to the panel
        addComp();

        // 3. Add the panel to the frame
        add(changePasswordPanel);

        setVisible(true);
    }

    /**
     * Add all components into panel.
     */
    private void addComp() {
        changePasswordPanel.add(message);
        changePasswordPanel.add(newPassword);
        changePasswordPanel.add(finishButton);
    }

    /**
     * Trigger opening of new frame and closure of the start frame when pressing button
     *
     * @param e ActionEvent item
     */
    @Override
    public void actionPerformed(ActionEvent e) { // user finishes entering new password
        // Constructs a userManager
        IDataInOut dataInOut = new DataInOut();
        DatabaseErrorOutputBoundary dbPresenter = new DatabaseErrMsgPresenter();
        UserInputBoundary manager = new UserManager(dataInOut, programStateInputBoundary, dbPresenter);

        // Construct a UserController
        DatabaseErrorOutputBoundary databaseErrorOutputBoundary = new DatabaseErrMsgPresenter();
        UserController userController = new UserController(manager, databaseErrorOutputBoundary);

        // Call change password method
        userController.changePassword(newPassword.getText());
        manager.changePassword(newPassword.getText());

        // Check if successfully changed
        JOptionPane.showMessageDialog(this, "Password changed successfully.");
        new UserFrame(username, programStateInputBoundary);
        setVisible(false);
    }
}
