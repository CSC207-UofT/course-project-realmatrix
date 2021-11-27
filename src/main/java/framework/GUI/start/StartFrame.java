package framework.GUI.start;

import framework.GUI.BasicFrame;
import framework.GUI.login_register.LoginFrame;
import framework.GUI.login_register.RegisterFrame;
import use_case.constants.Constants;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.manager.ProgramStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends BasicFrame implements ActionListener {
    final JPanel startPanel;      // The panel shown on the start frame
    final JLabel helloMessage;    // Greet the user
    final StartButton lgButton;   // A JButton for login
    final StartButton rgButton;   // A JButton for registration

    /**
     * Build a StartFrame.
     */
    public StartFrame(ProgramStateInputBoundary programStateInputBoundary) {
        super(Constants.RECALLER_BTN, programStateInputBoundary);
        // 1. Create components shown on the frame
        startPanel = new JPanel(new GridLayout(3, 1));

        helloMessage = new JLabel(Constants.WELLCOME_MSG, SwingConstants.CENTER);
        helloMessage.setFont(new Font("verdana", Font.BOLD | Font.ITALIC, 38));

        lgButton = new StartButton(Constants.LOGIN_BTN);
        lgButton.addActionListener(this);

        rgButton = new StartButton(Constants.REG_BTN);
        rgButton.addActionListener(this);

        // 2. Add components to the panel
        addComp();

        // 3. Add the panel to the frame
        add(startPanel);

        setVisible(true);
    }

    private void addComp() {
        startPanel.add(helloMessage);
        startPanel.add(lgButton);
        startPanel.add(rgButton);
    }

    /**
     * Trigger opening of new frame and closure of the start frame when pressing button
     *
     * @param e ActionEvent item
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lgButton) {
            new LoginFrame(this.programStateInputBoundary);
        }

        if (e.getSource() == rgButton) {
            new RegisterFrame(this.programStateInputBoundary);
        }
        setVisible(false);
    }

    // Test
    public static void main(String[] args) {
        ProgramStateInputBoundary ps = new ProgramStateManager();
        JFrame f = new StartFrame(ps);
    }

}