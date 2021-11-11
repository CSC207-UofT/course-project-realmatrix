package GUI.login_register;

import GUI.start.StartFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RegisterFrame extends LogRegFrame {
    private final JLabel pw2Label;          // Confirming password JLabel
    private final JTextField pw2;           // Confirmation password text field
    private final JButton rgButton;         // Button that confirms registration

    public RegisterFrame() {
        super("Register");

        // Create component: register button
        rgButton = new JButton("Register");
        rgButton.addActionListener(this);
        
        // Create component: confirm password label-text field
        pw2Label = new JLabel("Password Again: ", JLabel.TRAILING);
        pw2 = new JPasswordField(20);

        // Layout Components
        layoutRestComp();
        add(panel);
        setVisible(true);
    }

    /**
     * Layout the rest of the components relative to basic components:
     *      password confirming label-text field, register button
     */
    @Override
    protected void layoutRestComp() {
        pw2Label.setBounds(pwLabel.getX(), pwLabel.getY() + 30,
                130, pwLabel.getHeight());
        add(pw2Label);

        pw2.setBounds(pw.getX(), pw.getY() + 30,
                pw.getWidth(), pw.getHeight());
        add(pw2);

        rgButton.setBounds(backButton.getX() + 150, backButton.getY(),
                backButton.getWidth(), backButton.getHeight());
        add(rgButton);
    }

    /**
     * Set actions for clicking register and back buttons.
     *
     * Clicking register button: check register successful or not
     * Clicking back button: back to the start frame
     *
     * @param e An ActionEvent item
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == rgButton) {
            check();
            // TODO: need a controller that controls the whole registration process
            // TODO: need a RegisterManager
        }

        if (source == backButton) {
            new StartFrame();
            setVisible(false);
        }
    }

    /**
     * Check if the user enters a non-existing username and password for registration.
     * If not, pop up a window showing registration fails.
     * If yes, goes to the user's UserFrame.
     * // TODO: implement this method
     */
    @Override
    protected void check() {
    }

    public static void main(String[] args) {
        new RegisterFrame();
    }
}
