package framework.GUI.start;

import framework.GUI.login_register.LoginFrame;
import framework.GUI.login_register.RegisterFrame;
import framework.GUI.BasicFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends BasicFrame implements ActionListener {
    JPanel startPanel;      // The panel shown on the start frame
    JLabel helloMessage;    // Greet the user
    StartButton lgButton;   // A JButton for login
    StartButton rgButton;   // A JButton for registration

    /**
     * Build a StartFrame.
     */
    public StartFrame() {
        super("Recaller");
        // 1. Create components shown on the frame
        startPanel = new JPanel(new GridLayout(3, 1));

        helloMessage = new JLabel("Welcome to Recaller!", SwingConstants.CENTER);
        helloMessage.setFont(new Font("verdana", Font.BOLD | Font.ITALIC, 38));

        lgButton = new StartButton("Login");
        lgButton.addActionListener(this);

        rgButton = new StartButton("Register");
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
            new LoginFrame();
        }

        if (e.getSource() == rgButton) {
            new RegisterFrame();
        }
        setVisible(false);
    }

    // Test
    public static void main(String[] args) {
        JFrame f = new StartFrame();
    }

}
