package framework.gui.pack;

import framework.gui.BasicFrame;
import framework.gui.database_error.DatabaseErrorWindow;
import interface_adapter.controller.PackController;
import interface_adapter.gateway.DataInOut;
import interface_adapter.gateway.IDataInOut;
import interface_adapter.presenters.ChangePresenter;
import interface_adapter.presenters.DatabaseErrMsgPresenter;
import use_case.input_boundaries.PackInputBoundary;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.manager.PackManager;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.DatabaseErrorOutputBoundary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * A frame for editing pack.
 */
public class EditPackFrame extends BasicFrame implements ActionListener {
    private final JTextField packText = new JTextField(100);
    private final JButton editButton;
    private final JButton backButton;
    private final String old_name;  // the original pack name

    public EditPackFrame(ProgramStateInputBoundary programStateInputBoundary) {
        super("Edit Pack", programStateInputBoundary);
        old_name = psController.getCurrPackName();
        packText.setText(old_name);
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

        add(panel);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editButton) {
            if (checkEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Pack name can't be empty",
                        "Edit fails",
                        JOptionPane.WARNING_MESSAGE);
            } else if (check() || packText.getText().equals(old_name)) {  // Edit succeeds
                new PackListFrame(programStateInputBoundary);
                setVisible(false);
            } else {    // add fails: pack already exists
                JOptionPane.showMessageDialog(this,
                        "This Pack has existed. Edit another one please~", // TODO: constant
                        "Edit Fails",
                        JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource() == backButton) {
            new PackListFrame(programStateInputBoundary);
            setVisible(false);
        }

    }

    /**
     * Check if the user's input (pack name) is empty.
     *
     * @return true if it's empty; false otherwise
     */
    private boolean checkEmpty() {
        return (packText.getText().length() == 0);
    }

    /**
     * Check if this pack name is valid for change.
     *
     * @return true if it's valid; false otherwise.
     */
    protected boolean check() {

        // get new term and def edited by users
        String new_name = packText.getText();

        // Construct PackManager
        DatabaseErrorOutputBoundary dbPresenter = new DatabaseErrMsgPresenter(new DatabaseErrorWindow());
        PackInputBoundary packManager = new PackManager(programStateInputBoundary);
        // Construct PackController
        PackController pkController = new PackController(packManager, dbPresenter);
        // check edit
        IDataInOut dataInOut = new DataInOut();
        ChangeOutputBoundary changePresenter = new ChangePresenter();
        pkController.changePackName(old_name, new_name, dataInOut, changePresenter);
        return changePresenter.getChangeResult();
    }
}
