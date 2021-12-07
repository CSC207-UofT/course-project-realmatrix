package framework.gui.login_register;

import framework.gui.database_error.DatabaseErrorWindow;
import framework.gui.start.StartFrame;
import framework.gui.user.UserFrame;
import interface_adapter.controller.RegisterController;
import interface_adapter.gateway.DataInOut;
import interface_adapter.gateway.IDataInOut;
import interface_adapter.presenters.DatabaseErrMsgPresenter;
import interface_adapter.presenters.RegisterPresenter;
import use_case.constants.Constants;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.input_boundaries.UserInputBoundary;
import use_case.manager.ProgramStateManager;
import use_case.manager.UserManager;
import use_case.output_boundaries.DatabaseErrorOutputBoundary;
import use_case.output_boundaries.RegisterOutputBoundary;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * A register frame which allows users to register.
 */
public class RegisterFrame extends LogRegFrame {
    private final JLabel pw2Label;          // Confirming password JLabel
    private final JPasswordField pw2;       // Confirmation password text field
    private final JButton rgButton;         // Button that confirms registration

    public RegisterFrame(ProgramStateInputBoundary programStateInputBoundary) {
        super("Register", programStateInputBoundary);

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
     * - If fails, pop up a window showing registration fails.
     * - If succeeds, goes to the user's UserFrame.
     * Clicking back button: back to the start frame
     *
     * @param e An ActionEvent item
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == rgButton) {
            if (checkEmpty()) { //username or password is empty
                JOptionPane.showMessageDialog(this,
                        Constants.USERNAME_PW_EMPTY_MSG,
                        Constants.REG_FAIL,
                        JOptionPane.WARNING_MESSAGE);
            } else if (!checkPasswordEqual()) {
                JOptionPane.showMessageDialog(this,
                        Constants.PW_NOT_MATCH,
                        Constants.REG_FAIL,
                        JOptionPane.WARNING_MESSAGE);
            } else if (pw2.getPassword().length == 0) {   // check if password is empty
                JOptionPane.showMessageDialog(this,
                        Constants.PW_EMPTY_MSG,
                        Constants.CHANGE_FAIL,
                        JOptionPane.WARNING_MESSAGE);
            } else if (check()) {  // Registration succeeds
                setVisible(false);
                new UserFrame(username.getText(), programStateInputBoundary);
            } else {    // Registration fails: username already exists
                JOptionPane.showMessageDialog(this,
                        Constants.USERNAME_EXISTED,
                        Constants.REG_FAIL,
                        JOptionPane.WARNING_MESSAGE);
            }
        } else if (source == backButton) {
            new StartFrame(new ProgramStateManager());
            setVisible(false);
        }
    }

    /**
     * Helper for actionPerformed.
     * Checks if the user enters empty username or password.
     *
     * @return true iff both username and password not empty
     */
    private boolean checkEmpty() {
        return username.getText().length() == 0 || String.valueOf(pw.getPassword()).length() == 0;
    }

    /**
     * Helper for actionPerformed.
     * Checks if the user enters same password twice for registration.
     *
     * @return true iff passwords are equal
     */
    private boolean checkPasswordEqual() {
        String password = String.valueOf(pw.getPassword());
        String passwordConfirmation = String.valueOf(pw2.getPassword());
        return password.equals(passwordConfirmation);
    }

    /**
     * Helper for actionPerformed.
     * Check if the user enters a non-existing username and password for registration.
     */
    @Override
    protected boolean check() {
        String name = username.getText();
        String password = String.valueOf(pw.getPassword());

        // Construct UserManager
        IDataInOut dataInOut = new DataInOut();
        DatabaseErrorOutputBoundary dbPresenter = new DatabaseErrMsgPresenter(new DatabaseErrorWindow());
        UserInputBoundary userManager = new UserManager(dataInOut, programStateInputBoundary);

        // Construct RegisterController
        RegisterController rgController = new RegisterController(userManager, dbPresenter);

        // Check registration
        RegisterOutputBoundary rgPresenter = new RegisterPresenter();
        rgController.register(name, password, rgPresenter, new DataInOut());
        return rgPresenter.getRegisterResult();
    }


}
