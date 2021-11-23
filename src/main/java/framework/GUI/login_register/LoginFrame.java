package framework.GUI.login_register;

import entity.User;
import framework.GUI.start.StartFrame;
import framework.GUI.user.UserFrame;
import interface_adapter.Controller.LogInOutController;
import interface_adapter.gateway.DataInOut;
import interface_adapter.gateway.IDataInOut;
import interface_adapter.presenters.LogInOutPresenter;
import use_case.input_boundaries.LogInOutInputBoundary;
import use_case.manager.LogInOutManager;
import use_case.manager.UserManager;
import use_case.output_boundaries.LogInOutOutputBoundary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Objects;

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
     * <p>
     * Clicking login button: check login successful or not
     * Clicking back button: back to the start frame
     *
     * @param e An ActionEvent item
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == lgButton) {
            try {
                check();
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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

    protected void check() throws Exception {
        String name = username.getText();
        String password = pw.getText();
        UserManager userManager = new UserManager();

        // Load all usernames and passwords into userManager
        IDataInOut dbConnector = new DataInOut();
        Map<String, String> nameToPassword = dbConnector.initialLoad();
        for (String username : nameToPassword.keySet()) {
            userManager.putUser(new User(username, nameToPassword.get(username)));
        }

        LogInOutInputBoundary logInOutManager = new LogInOutManager(userManager);
        LogInOutController controller = new LogInOutController(userManager, logInOutManager);
        LogInOutOutputBoundary presenter = new LogInOutPresenter();
        controller.login(name, password, presenter);
        String result = presenter.presentLogInOutResult();
        if (Objects.equals(result, "Login succeeds!")) {
            User user = logInOutManager.getCurrUser();
            new UserFrame(user); // TODO: use program state to get current user
        } // TODO: handle other cases...
    }

    // Test
    public static void main(String[] args) {
        new LoginFrame();
    }
}
