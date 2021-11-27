package framework.GUI.login_register;

import framework.GUI.start.StartFrame;
//import framework.GUI.user.UserFrame;
import interface_adapter.Controller.RegisterController;
import interface_adapter.gateway.DataInOut;
import interface_adapter.gateway.IDataInOut;
import interface_adapter.presenters.DatabaseErrMsgPresenter;
import interface_adapter.presenters.RegisterPresenter;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.input_boundaries.UserInputBoundary;
import use_case.manager.ProgramStateManager;
import use_case.manager.UserManager;
import use_case.output_boundaries.DatabaseErrorOutputBoundary;
import use_case.output_boundaries.RegisterOutputBoundary;
import use_case.constants.Constants;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RegisterFrame extends LogRegFrame {
    private final JLabel pw2Label;          // Confirming password JLabel
    private final JPasswordField pw2;       // Confirmation password text field
    private final JButton rgButton;         // Button that confirms registration

    public RegisterFrame(ProgramStateInputBoundary programStateInputBoundary) {
        super(Constants.REG_BTN, programStateInputBoundary);

        // Create component: register button
        rgButton = new JButton(Constants.REG_BTN);
        rgButton.addActionListener(this);

        // Create component: confirm password label-text field
        pw2Label = new JLabel(Constants.PW_AGAIN, JLabel.TRAILING);
        pw2 = new JPasswordField(Constants.COLUMNS1);

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
        pw2Label.setBounds(pwLabel.getX(), pwLabel.getY() + Constants.EXTRA_Y,
                Constants.PW_WIDTH, pwLabel.getHeight());
        add(pw2Label);

        pw2.setBounds(pw.getX(), pw.getY() + Constants.EXTRA_Y,
                pw.getWidth(), pw.getHeight());
        add(pw2);

        rgButton.setBounds(backButton.getX() + Constants.EXTRA_X, backButton.getY(),
                backButton.getWidth(), backButton.getHeight());
        add(rgButton);
    }

    /**
     * Set actions for clicking register and back buttons.
     * <p>
     * Clicking register button: check register successful or not
     *      - If fails, pop up a window showing registration fails.
     *      - If succeeds, goes to the user's UserFrame.
     * Clicking back button: back to the start frame
     *
     * @param e An ActionEvent item
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == rgButton) {
            if (!checkPasswordEqual()) {
                JOptionPane.showMessageDialog(this,
                        Constants.PASSWORD_NOT_MATCH,
                        Constants.REG_FAIL,
                        JOptionPane.WARNING_MESSAGE);
            }

            if (check()) {  // Registration succeeds
                setVisible(false);
                //TODO: go to UserFrame.
            } else {    // Registration fails: username already exists
                JOptionPane.showMessageDialog(this,
                        Constants.USER_NAME_TAKEN,
                        Constants.REG_FAIL,
                        JOptionPane.WARNING_MESSAGE);
            }
        }

        if (source == backButton) {
            new StartFrame(new ProgramStateManager());
            setVisible(false);
        }
    }

    /**
     * Helper for actionPerformed.
     * Checks if the user enters same password twice for registration.
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
        DatabaseErrorOutputBoundary dbPresenter = new DatabaseErrMsgPresenter();
        UserInputBoundary userManager = new UserManager(dataInOut, programStateInputBoundary, dbPresenter);

        // Construct RegisterController
        RegisterController rgController = new RegisterController(userManager, dbPresenter);

        // Check registration
        RegisterOutputBoundary rgPresenter = new RegisterPresenter();
        rgController.register(name, password, rgPresenter);
        return rgPresenter.getRegisterResult();
    }

    // Test
    public static void main(String[] args) {
        ProgramStateInputBoundary ps = new ProgramStateManager();
        new RegisterFrame(ps);
    }
}