package framework.GUI.Pack;

import javax.swing.*;
import framework.GUI.BasicFrame;
import interface_adapter.Controller.PackController;
import interface_adapter.gateway.DataInOut;
import interface_adapter.gateway.IDataInOut;
import interface_adapter.presenters.DatabaseErrMsgPresenter;
import interface_adapter.presenters.AddPresenter;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.input_boundaries.PackInputBoundary;
import use_case.manager.ProgramStateManager;
import use_case.manager.PackManager;
import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.DatabaseErrorOutputBoundary;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class AddPackFrame extends BasicFrame implements ActionListener {
    private final JTextField packText;
    private final JButton addButton;
    private final JLabel success;
    private final JButton backButton;


    public AddPackFrame(ProgramStateInputBoundary programStateInputBoundary) {
        super("Add Pack", programStateInputBoundary);
        JPanel panel = new JPanel();

        panel.setLayout(null);

        JLabel termLabel = new JLabel("Pack name");
        termLabel.setBounds(20, 20, 80, 25);
        panel.add(termLabel);

        packText = new JTextField(100);
        packText.setBounds(100, 20, 300, 25);
        panel.add(packText);

        addButton = new JButton("Add");
        addButton.setBounds(400, 200, 80, 40);
        addButton.addActionListener(this);
        panel.add(addButton);

        backButton = new JButton("Back");
        backButton.setBounds(10, 200, 80, 40);
        backButton.addActionListener(this);
        panel.add(backButton);

        success = new JLabel("");
        success.setBounds(40, 400, 300, 50);
        success.setFont(new Font("verdana", Font.BOLD | Font.ITALIC, 18));
        success.setForeground(Color.red);
        panel.add(success);

        add(panel);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            if (check()) {  // add succeeds
                success.setText("Add pack successful!");
                packText.setText(packText.getText());
                //TODO: go to PackFrame.
            } else {    // add fails: pack already exists
                JOptionPane.showMessageDialog(this,
                        "This Pack has existed. Add another one please~", // TODO: constant
                        "Add Fails",
                        JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource() == backButton) {
            // TODO: go to PackFrame
        }

    }

    protected boolean check() {
        String pack = packText.getText();

        // Construct PackManager
        IDataInOut dataInOut = new DataInOut();
        DatabaseErrorOutputBoundary dbPresenter = new DatabaseErrMsgPresenter();
        PackInputBoundary pkManager = new PackManager(dataInOut, programStateInputBoundary);
        // Construct PackController
        PackController pkController = new PackController(pkManager, dbPresenter);
        // check add
        AddOutputBoundary addPresenter = new AddPresenter();
        pkController.addNewPack(pack, addPresenter);
        return addPresenter.getAddResult();
    }

    // Test
    public static void main(String[] args) {
        ProgramStateInputBoundary ps = new ProgramStateManager();
        new AddPackFrame(ps);
    }
}

