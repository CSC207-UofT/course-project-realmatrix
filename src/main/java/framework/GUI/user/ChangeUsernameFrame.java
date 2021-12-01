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
    final JTextField newName = new JTextField(100);
    final JLabel newNameLabel;
    final JButton finishButton;
    final JButton backButton;

    /**
     * Build a StartFrame.
     */
    public ChangeUsernameFrame(String username, ProgramStateInputBoundary programStateInputBoundary) {
        super(Constants.RECALLER_BTN, programStateInputBoundary);
        this.username = username;
//        String old_name = username;
//        newName.setText(old_name);
        changeNamePanel = new JPanel();

        changeNamePanel.setLayout(null);

        newNameLabel = new JLabel("User name");
        newNameLabel.setBounds(20, 20, 80, 25);
        changeNamePanel.add(newNameLabel);

        message = new JLabel(Constants.CHANGE_USERNAME, SwingConstants.CENTER);
        message.setFont(new Font("verdana", Font.BOLD | Font.ITALIC, 38));

        //Set username text
        newName.setBounds(100, 20, 300, 25);
        newName.setEditable(true);

        finishButton = new JButton(Constants.DONE_BTN);
        finishButton.setBounds(400, 200, 80, 40);
        finishButton.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setBounds(10, 200, 80, 40);
        backButton.addActionListener(this);

        addComp();

        add(changeNamePanel);
        setVisible(true);

//        changeNamePanel = new JPanel();
//        changeNamePanel.setLayout(null);
//        // 1. Create components shown on the frame
//        changeNamePanel = new JPanel(new GridLayout(3, 2));
//
//        message = new JLabel(Constants.CHANGE_USERNAME, SwingConstants.CENTER);
//        message.setFont(new Font("verdana", Font.BOLD | Font.ITALIC, 38));
//
//        newName = new JTextField(Constants.COLUMNS1);
//        newNameLabel = new JLabel(Constants.NEW_USERNAME_MSG, JLabel.TRAILING); // TODO: label doesn't show up. fix this
//        newName.add(newNameLabel);
//
//        finishButton = new JButton(Constants.DONE_BTN);
//        finishButton.addActionListener(this);
//
//        backButton = new JButton("Back");
//        backButton.addActionListener(this);
//        backButton.setBounds(280, 430, 200, 50);
//        changeNamePanel.add(backButton);
//
//        // 2. Add components to the panel
//        addComp();
//
//        // 3. Add the panel to the frame
//        add(changeNamePanel);
//
//        setVisible(true);
    }

    /**
     * Add all components into panel.
     */
    private void addComp() {
        changeNamePanel.add(message);
        changeNamePanel.add(newName);
        changeNamePanel.add(finishButton);
        changeNamePanel.add(backButton);
    }

    /**
     * Trigger opening of new frame and closure of the start frame when pressing button
     *
     * @param e ActionEvent item
     */
    @Override
    public void actionPerformed(ActionEvent e) { // user finishes entering new name
        // if user press back
        if (e.getSource() == backButton) {
            programStateInputBoundary.setCurrCard(null);
            new UserFrame(username, programStateInputBoundary);
            setVisible(false);
            return;
        }
        // Check if new username is null
        if (newName.getText().length() == 0){
            JOptionPane.showMessageDialog(this,
                    "Username can't be empty",
                    "Change fails",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

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