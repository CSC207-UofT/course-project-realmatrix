package GUI.login_register;

import GUI.BasicFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A general login/register frame.
 */
public abstract class LogRegFrame extends BasicFrame implements ActionListener {
    protected final JPanel panel;
    protected final JLabel usernameLabel;   // The JLabel showing "Username:"
    protected final JTextField username;    // The text field for entering username
    protected final JLabel pwLabel;         // The JLabel showing "Password:"
    protected final JTextField pw;          // The text field for entering password
    protected final JButton backButton;     // Button that goes back to StartFrame

    public LogRegFrame(String title) {
        // Set the frame constraints
        super(title);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create component: the panel on this frame
        panel = new JPanel(null);

        // Create component: username label-text field
        usernameLabel = new JLabel("Username: ", JLabel.TRAILING);
        username = new JTextField(20);

        // Create component: password label-text field
        pwLabel = new JLabel("Password: ", JLabel.TRAILING);
        pw = new JTextField(20);

        // Create component: backButton
        backButton = new JButton("Back");
        backButton.addActionListener(this);

        basicLayout();
    }

    /**
     * Set layout for basic components:
     *          username label-text field, password label-text field, back button.
     */
    public void basicLayout() {
        usernameLabel.setBounds(50,20,90,25);
        add(usernameLabel);

        username.setBounds(200, 20, 150, 25);
        add(username);

        pwLabel.setBounds(usernameLabel.getX(), usernameLabel.getY() + 30,
                usernameLabel.getWidth(),usernameLabel.getHeight());
        add(pwLabel);

        pw.setBounds(username.getX(), username.getY() + 30,
                username.getWidth(),username.getHeight());
        add(pw);

        backButton.setBounds(70, 120, 100, 40);
        add(backButton);
    }

    /**
     * Layout all components in the panel and add them into the panel.
     */
    protected abstract void layoutRestComp();

    /**
     * Method for checking whether registration/login is valid.
     */
    protected abstract void check();

    @Override
    public abstract void actionPerformed(ActionEvent e);
}