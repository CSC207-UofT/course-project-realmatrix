package framework.gui.user;

import framework.gui.BasicFrame;
import framework.gui.database_error.DatabaseErrorWindow;
import interface_adapter.controller.UserController;
import interface_adapter.gateway.DataInOut;
import interface_adapter.gateway.IDataInOut;
import interface_adapter.presenters.DatabaseErrMsgPresenter;
import use_case.constants.Constants;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.input_boundaries.UserInputBoundary;
import use_case.manager.UserManager;
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
    final JPasswordField newPassword1 = new JPasswordField(100);
    final JPasswordField newPassword2 = new JPasswordField(100);
    final JLabel newPasswordLabel1;
    final JLabel newPasswordLabel2;
    final JButton finishButton;
    final JButton backButton;

    /**
     * Build a ChangePasswordFrame.
     */
    public ChangePasswordFrame(String username, ProgramStateInputBoundary programStateInputBoundary) {
        super(Constants.CHANGE_PW, programStateInputBoundary);
        this.username = username;
        changePasswordPanel = new JPanel();
        changePasswordPanel.setLayout(null);

        message = new JLabel(Constants.CHANGE_PW, SwingConstants.CENTER);
        message.setFont(new Font("verdana", Font.BOLD | Font.ITALIC, 38));

        newPasswordLabel1 = new JLabel(Constants.NEW_PW_MSG, JLabel.TRAILING);
        newPasswordLabel1.setBounds(20, 20, 155, 25);
        changePasswordPanel.add(newPasswordLabel1);

        newPasswordLabel2 = new JLabel(Constants.REPEAT_PW_MSG, JLabel.TRAILING);
        newPasswordLabel2.setBounds(20, 60, 155, 25);
        changePasswordPanel.add(newPasswordLabel2);

        finishButton = new JButton(Constants.DONE_BTN);
        finishButton.setBounds(400, 200, 80, 40);
        finishButton.addActionListener(this);
        changePasswordPanel.add(finishButton);

        backButton = new JButton("Back");
        backButton.setBounds(10, 200, 80, 40);
        backButton.addActionListener(this);
        changePasswordPanel.add(backButton);

        newPassword1.setBounds(180, 20, 300, 25);
        newPassword1.setEditable(true);
        newPassword2.setBounds(180, 60, 300, 25);
        newPassword2.setEditable(true);

        // Add components to the panel
        addComp();

        // Add the panel to the frame
        add(changePasswordPanel);

        setVisible(true);
    }

    /**
     * Add all components into panel.
     */
    private void addComp() {
        changePasswordPanel.add(message);
        changePasswordPanel.add(newPassword1);
        changePasswordPanel.add(newPassword2);
        changePasswordPanel.add(finishButton);
        changePasswordPanel.add(backButton);
    }

    /**
     * Trigger opening of new frame and closure of the start frame when pressing button
     *
     * @param e ActionEvent item
     */
    @Override
    public void actionPerformed(ActionEvent e) { // user finishes entering new password
        // if user press back
        if (e.getSource() == backButton) {
            programStateInputBoundary.setCurrCard(null);
            new UserFrame(username, programStateInputBoundary);
            setVisible(false);
            return;
        }

        String password1 = String.valueOf(newPassword1.getPassword());
        String password2 = String.valueOf(newPassword2.getPassword());

        // Check if passwords are equal
        if (!password1.equals(password2)) {
            JOptionPane.showMessageDialog(this,
                    Constants.PW_NOT_MATCH,
                    Constants.CHANGE_FAIL,
                    JOptionPane.WARNING_MESSAGE);
        } else if (password1.length() == 0) {   // check if password is null
            JOptionPane.showMessageDialog(this,
                    Constants.PW_NOT_MATCH,
                    Constants.CHANGE_FAIL,
                    JOptionPane.WARNING_MESSAGE);
        } else {  // The password is valid for change
            changePassword();
            JOptionPane.showMessageDialog(this, Constants.PW_CHANGED_SUCCEED);
            new UserFrame(username, programStateInputBoundary);
            setVisible(false);
        }
    }

    /**
     * Change the user's password
     */
    private void changePassword() {
        // Constructs a userManager
        IDataInOut dataInOut = new DataInOut();
        UserInputBoundary manager = new UserManager(dataInOut, programStateInputBoundary);

        // Construct a UserController
        DatabaseErrorOutputBoundary databaseErrorOutputBoundary = new DatabaseErrMsgPresenter(new DatabaseErrorWindow());
        UserController userController = new UserController(manager, databaseErrorOutputBoundary);

        // Call change password method
        userController.changePassword(String.valueOf(newPassword1.getPassword()), new DataInOut());
    }
}