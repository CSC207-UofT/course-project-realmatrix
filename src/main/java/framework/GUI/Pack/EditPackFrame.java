package framework.GUI.Pack;

import javax.swing.*;

import entity.Pack;
import framework.GUI.BasicFrame;
import interface_adapter.Controller.PackController;
import interface_adapter.gateway.DataInOut;
import interface_adapter.gateway.IDataInOut;
import interface_adapter.presenters.ChangePresenter;
import interface_adapter.presenters.DatabaseErrMsgPresenter;
import use_case.input_boundaries.PackInputBoundary;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.manager.PackManager;
import use_case.manager.ProgramStateManager;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.DatabaseErrorOutputBoundary;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class EditPackFrame extends BasicFrame implements ActionListener {
    private final JTextField packText = new JTextField(100);
    private final JButton editButton;
    private final JLabel success;
    private final JButton backButton;

    /** get original name of the chosen pack */
    private final String old_name = packText.getText();

    public EditPackFrame(ProgramStateInputBoundary programStateInputBoundary) {
        super("Edit Pack", programStateInputBoundary);
        JPanel panel = new JPanel();

        panel.setLayout(null);

        JLabel termLabel = new JLabel("Pack name");
        termLabel.setBounds(20, 20, 80, 25);
        panel.add(termLabel);

        //set pack text
        packText.setBounds(100, 20, 300, 25);
        panel.add(packText);
        packText.setEditable(true);

        editButton = new JButton("Edit");
        editButton.setBounds(400, 200, 80, 40);
        editButton.addActionListener(this);
        panel.add(editButton);

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
        if (e.getSource() == editButton) {
            if (check()) {  // Edit succeeds
                success.setText("Edit pack successful!");
                packText.setText(packText.getText());
                new PackFrame(programStateInputBoundary);
            } else {    // add fails: pack already exists
                JOptionPane.showMessageDialog(this,
                        "This Pack has existed. Edit another one please~", // TODO: constant
                        "Edit Fails",
                        JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource() == backButton) {
            new PackFrame(programStateInputBoundary);
        }

    }

    protected boolean check() {

        // get new term and def edited by users
        String new_name = packText.getText();

        // Construct PackManager
        IDataInOut dataInOut = new DataInOut();
        DatabaseErrorOutputBoundary dbPresenter = new DatabaseErrMsgPresenter();
        PackInputBoundary packManager = new PackManager(dataInOut, programStateInputBoundary);
        // Construct PackController
        PackController pkController = new PackController(packManager, dbPresenter);
        // check edit
        ChangeOutputBoundary changePresenter = new ChangePresenter();
        pkController.changePackName(old_name, new_name, changePresenter);
        return changePresenter.getChangeResult();
    }

    // Test
    public static void main(String[] args) {
        ProgramStateInputBoundary ps = new ProgramStateManager();
        new EditPackFrame(ps);
    }
}
