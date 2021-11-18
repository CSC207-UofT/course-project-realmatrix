package framework.GUI.user;

import entity.User;
import framework.GUI.BasicFrame;
import use_case.input_boundaries.UserInputBoundary;
import use_case.manager.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ChangePasswordFrame extends BasicFrame implements ActionListener {
    final User user;
    final JPanel changePasswordPanel;
    final JLabel message;
    final JTextField newPassword;
    final JLabel newPasswordLabel;
    final JButton finishButton;

    /**
     * Build a StartFrame.
     */
    public ChangePasswordFrame(User user) {
        super("Recaller");
        this.user = user;
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
        UserInputBoundary manager = new UserManager();
        manager.changePassword(user, newPassword.getText());
        new UserFrame(user);
        setVisible(false);
    }
}
