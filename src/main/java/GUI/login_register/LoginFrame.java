package GUI.login_register;

import GUI.start.StartFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
     *
     * Clicking login button: check login successful or not
     * Clicking back button: back to the start frame
     *
     * @param e An ActionEvent item
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == lgButton) {
            check();
        }

        if (source == backButton) {
            new StartFrame();
            setVisible(false);
        }
    }

    /**
     * Check if the user enters the correct username and password for login.
     * If not, pop up a window showing login fails.
     * If yes, goes to the user's UserFrame.
     */

    protected void check() {
        String name = username.getText();
        String password = pw.getText();
        // TODO: implement Controller that can call LoginManager to check login

    }

    public static void main(String[] args) {
        new LoginFrame();
    }

}
