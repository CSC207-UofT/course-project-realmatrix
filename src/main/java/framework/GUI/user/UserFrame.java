package framework.GUI.user;

import entity.User;
import framework.GUI.BasicFrame;
import framework.GUI.start.StartFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserFrame extends BasicFrame implements ActionListener {
    final User user;
    final JPanel userPanel;
    final JLabel message;
    final JButton signOutButton;
    final JButton changeNameButton;
    final JButton changePasswordButton;
    final JButton createPackageButton;
    final JButton checkOutPackagesButton;

    /**
     * Build a StartFrame.
     */
    public UserFrame(User user) {
        super("Recaller");
        this.user = user;
        // 1. Create components shown on the frame
        userPanel = new JPanel(new GridLayout(6, 1));

        message = new JLabel("User homepage", SwingConstants.CENTER);
        message.setFont(new Font("verdana", Font.BOLD | Font.ITALIC, 38));

        signOutButton = new JButton("Back");
        signOutButton.addActionListener(this);

        changeNameButton = new JButton("change name");
        changeNameButton.addActionListener(this);

        changePasswordButton = new JButton("change password");
        changePasswordButton.addActionListener(this);

        createPackageButton = new JButton("Create a package");
        createPackageButton.addActionListener(this);

        checkOutPackagesButton = new JButton("Checkout my packages");
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
            new StartFrame();
        }
        // TODO: Complete else if...else conditionals for each button click (go to other frames, e.g. change name frame)
        setVisible(false);
    }
}
