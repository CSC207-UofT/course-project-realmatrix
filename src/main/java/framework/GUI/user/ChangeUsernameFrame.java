package framework.GUI.user;

import entity.User;
import framework.GUI.BasicFrame;
import interface_adapter.presenters.ChangePresenter;
import use_case.input_boundaries.UserInputBoundary;
import use_case.manager.UserManager;
import use_case.output_boundaries.ChangeOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ChangeUsernameFrame extends BasicFrame implements ActionListener {
    final User user;
    final JPanel changeNamePanel;
    final JLabel message;
    final JTextField newName;
    final JLabel newNameLabel;
    final JButton finishButton;

    /**
     * Build a StartFrame.
     */
    public ChangeUsernameFrame(User user) {
        super("Recaller");
        this.user = user;
        // 1. Create components shown on the frame
        changeNamePanel = new JPanel(new GridLayout(3, 1));

        message = new JLabel("Change username", SwingConstants.CENTER);
        message.setFont(new Font("verdana", Font.BOLD | Font.ITALIC, 38));

        newName = new JTextField(20);
        newNameLabel = new JLabel("new username: ", JLabel.TRAILING);
        newName.add(newNameLabel);

        finishButton = new JButton("done");
        finishButton.addActionListener(this);

        // 2. Add components to the panel
        addComp();

        // 3. Add the panel to the frame
        add(changeNamePanel);

        setVisible(true);
    }

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
        UserInputBoundary manager = new UserManager();
        ChangeOutputBoundary presenter = new ChangePresenter();
        manager.changeName(user, newName.getText(), presenter);
        String result = presenter.presentChangeResult();
        if (Objects.equals(result, "OK! You have the new username now.")) {
            new UserFrame(user);
        } // TODO: handle the case when name change somehow fails
        setVisible(false);
    }
}
