package framework.GUI.user;

import framework.GUI.BasicFrame;
import interface_adapter.controller.UserController;
import interface_adapter.gateway.DataInOut;
import interface_adapter.gateway.IDataInOut;
import interface_adapter.presenters.ChangePresenter;
import interface_adapter.presenters.DatabaseErrMsgPresenter;
import use_case.constants.Constants;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.input_boundaries.UserInputBoundary;
import use_case.manager.UserManager;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.DatabaseErrorOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A frame for changing username.
 */
public class ChangeUsernameFrame extends BasicFrame implements ActionListener {
    final String username;
    final JPanel changeNamePanel;
    final JLabel message;
    final JTextField newName;
    final JLabel newNameLabel;
    final JButton finishButton;

    /**
     * Build a StartFrame.
     */
    public ChangeUsernameFrame(String username, ProgramStateInputBoundary programStateInputBoundary) {
        super(Constants.RECALLER_BTN, programStateInputBoundary);
        this.username = username;
        // 1. Create components shown on the frame
        changeNamePanel = new JPanel(new GridLayout(3, 1));

        message = new JLabel(Constants.CHANGE_USERNAME, SwingConstants.CENTER);
        message.setFont(new Font("verdana", Font.BOLD | Font.ITALIC, 38));

        newName = new JTextField(Constants.COLUMNS1);
        newNameLabel = new JLabel(Constants.NEW_USERNAME_MSG, JLabel.TRAILING); // TODO: label doesn't show up. fix this
        newName.add(newNameLabel);

        finishButton = new JButton(Constants.DONE_BTN);
        finishButton.addActionListener(this);

        // 2. Add components to the panel
        addComp();

        // 3. Add the panel to the frame
        add(changeNamePanel);

        setVisible(true);
    }

    /**
     * Add all components into panel.
     */
    private void addComp() {
        changeNamePanel.add(message);
        changeNamePanel.add(newName);
        changeNamePanel.add(finishButton);
    }

    /**
     * Trigger opening of new frame and closure of the start frame when pressing button
     *
     * @param e ActionEvent item
     */
    @Override
    public void actionPerformed(ActionEvent e) { // user finishes entering new name
        // Constructs a userManager
        IDataInOut dataInOut = new DataInOut();
        DatabaseErrorOutputBoundary dbPresenter = new DatabaseErrMsgPresenter();
        UserInputBoundary manager = new UserManager(dataInOut, programStateInputBoundary, dbPresenter);

        // Construct a UserController
        DatabaseErrorOutputBoundary databaseErrorOutputBoundary = new DatabaseErrMsgPresenter();
        UserController userController = new UserController(manager, databaseErrorOutputBoundary);

        // Call change name method
        ChangeOutputBoundary presenter = new ChangePresenter();
        userController.changeUserName(this.username, newName.getText(), presenter);

        // Check if successfully changed
        boolean result = presenter.getChangeResult();
        if (result) {
            JOptionPane.showMessageDialog(this, Constants.USERNAME_CHANGED_SUCCEED);
            new UserFrame(newName.getText(), programStateInputBoundary);
            setVisible(false);
        } else {
            // Pop up a window showing failing message
            JOptionPane.showMessageDialog(this,
                    Constants.USER_NAME_TAKEN,
                    Constants.USERNAME_CHANGED_FAILED,
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}