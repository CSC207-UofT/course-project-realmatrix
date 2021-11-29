package framework.GUI.user;

import framework.GUI.BasicFrame;
import interface_adapter.Controller.UserController;
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
import java.util.Arrays;

/**
 * A frame for changing password.
 */
public class ChangePasswordFrame extends BasicFrame implements ActionListener {
    final String username;
    final JPanel changePasswordPanel;
    final JLabel message;
    final JPasswordField newPassword1;
    final JPasswordField newPassword2;
    final JLabel newPasswordLabel;
    final JButton finishButton;

    /**
     * Build a StartFrame.
     */
    public ChangePasswordFrame(String username, ProgramStateInputBoundary programStateInputBoundary) {
        super(Constants.CHANGE_PW, programStateInputBoundary);
        this.username = username;
        // 1. Create components shown on the frame
        changePasswordPanel = new JPanel(new GridLayout(4, 1));

        message = new JLabel(Constants.CHANGE_PW, SwingConstants.CENTER);
        message.setFont(new Font("verdana", Font.BOLD | Font.ITALIC, 38));

        newPassword1 = new JPasswordField(Constants.COLUMNS1);
        newPasswordLabel = new JLabel(Constants.NEW_PW_MSG, JLabel.TRAILING); // TODO: label doesn't show up. fix this
        newPassword1.add(newPasswordLabel);

        newPassword2 = new JPasswordField(Constants.COLUMNS1);

        finishButton = new JButton(Constants.DONE_BTN);
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
        changePasswordPanel.add(newPassword1);
        changePasswordPanel.add(newPassword2);
        changePasswordPanel.add(finishButton);
    }

    /**
     * Trigger opening of new frame and closure of the start frame when pressing button
     *
     * @param e ActionEvent item
     */
    @Override
    public void actionPerformed(ActionEvent e) { // user finishes entering new password
        String password1 = String.valueOf(newPassword1.getPassword());
        String password2 = String.valueOf(newPassword2.getPassword());
        // Check if passwords are equal
        if (!password1.equals(password2)) {
            JOptionPane.showMessageDialog(this,
                    "Passwords don't match",
                    "Change fails",
                    JOptionPane.WARNING_MESSAGE);
        } else if (password1.length() == 0) {   // check if password is null
                JOptionPane.showMessageDialog(this,
                        "Passwords can't be empty",
                        "Change fails",
                        JOptionPane.WARNING_MESSAGE);
            }
        else {  // The password is valid for change
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
        DatabaseErrorOutputBoundary dbPresenter = new DatabaseErrMsgPresenter();
        UserInputBoundary manager = new UserManager(dataInOut, programStateInputBoundary, dbPresenter);

        // Construct a UserController
        DatabaseErrorOutputBoundary databaseErrorOutputBoundary = new DatabaseErrMsgPresenter();
        UserController userController = new UserController(manager, databaseErrorOutputBoundary);

        // Call change password method
        userController.changePassword(String.valueOf(newPassword1.getPassword()));
    }
}