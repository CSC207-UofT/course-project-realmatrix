package framework.GUI.user;

import framework.GUI.BasicFrame;
import framework.GUI.start.StartFrame;
import use_case.constants.Constants;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.manager.ProgramStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserFrame extends BasicFrame implements ActionListener {
    final String username;
    final JPanel userPanel;
    final JLabel message;
    final JButton signOutButton;
    final JButton changeNameButton;
    final JButton changePasswordButton;
    final JButton createPackageButton;
    final JButton checkOutPackagesButton;

    /**
     * Build a UserFrame.
     */
    public UserFrame(String username, ProgramStateInputBoundary programStateInputBoundary) {
        super(username + "'s Home Page", programStateInputBoundary);
        this.username = username;
        // 1. Create components shown on the frame
        userPanel = new JPanel(new GridLayout(6, 1));

        message = new JLabel(username + "'s Home Page", SwingConstants.CENTER);
        message.setFont(new Font("verdana", Font.BOLD | Font.ITALIC, 38));

        signOutButton = new JButton(Constants.SIGN_OUT_BTN);
        signOutButton.addActionListener(this);

        changeNameButton = new JButton(Constants.CHANGE_NAME_BTN);
        changeNameButton.addActionListener(this);

        changePasswordButton = new JButton(Constants.CHANGE_PW_BTN);
        changePasswordButton.addActionListener(this);

        createPackageButton = new JButton(Constants.CREATE_NEW_PACKAGE);
        createPackageButton.addActionListener(this);

        checkOutPackagesButton = new JButton(Constants.CHECK_OUT_PACKAGE);
        checkOutPackagesButton.addActionListener(this);

        // 2. Add components to the panel
        addComp();

        // 3. Add the panel to the frame
        add(userPanel);

        setVisible(true);
    }

    private void addComp() {
        userPanel.add(message);
        userPanel.add(signOutButton);
        userPanel.add(changeNameButton);
        userPanel.add(changePasswordButton);
        userPanel.add(createPackageButton);
        userPanel.add(checkOutPackagesButton);
    }

    /**
     * Trigger opening of new frame and closure of the start frame when pressing button
     *
     * @param e ActionEvent item
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signOutButton) {
            new StartFrame(new ProgramStateManager());
        } else if (e.getSource() == changeNameButton) {
            new ChangeUsernameFrame(username, this.programStateInputBoundary);
        } else if (e.getSource() == changePasswordButton) {
            new ChangePasswordFrame(username, this.programStateInputBoundary);
        }
        // TODO: Complete conditionals for each of the five buttons
        setVisible(false);
    }

    public static void main(String[] args) {
        ProgramStateInputBoundary ps = new ProgramStateManager();
        new UserFrame("haha", ps);
    }
}