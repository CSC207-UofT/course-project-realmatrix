package framework.GUI.login_register;

import entity.User;
import framework.GUI.start.StartFrame;
import framework.GUI.user.UserFrame;
import interface_adapter.presenters.RegisterPresenter;
import use_case.input_boundaries.UserInputBoundary;
import use_case.manager.UserManager;
import use_case.output_boundaries.RegisterOutputBoundary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

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
     * password confirming label-text field, register button
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
     * <p>
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
            setVisible(false);
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
        String name = username.getText();
        String password = pw.getText();
        String passwordConfirmation = pw2.getText();
        if (!Objects.equals(password, passwordConfirmation)) {
            // TODO: handle this case
        } else {
            UserInputBoundary userManager = new UserManager();
            RegisterOutputBoundary presenter = new RegisterPresenter();
            User user = (User) userManager.createNewUser(name, password, presenter);
            String result = presenter.presentRegisterResult();
            if (Objects.equals(result, "Registration succeeds!")) {
                new UserFrame(user); // TODO: use program state to get current user
            } // TODO: handle other cases...
        }
    }

    // Test
    public static void main(String[] args) {
        new RegisterFrame();
    }
}
