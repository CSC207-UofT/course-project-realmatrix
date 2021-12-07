package framework.gui.pack;

import framework.gui.BasicFrame;
import framework.gui.database_error.DatabaseErrorWindow;
import interface_adapter.controller.PackController;
import interface_adapter.gateway.DataInOut;
import interface_adapter.gateway.IDataInOut;
import interface_adapter.presenters.AddPresenter;
import interface_adapter.presenters.DatabaseErrMsgPresenter;
import use_case.constants.Constants;
import use_case.input_boundaries.PackInputBoundary;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.manager.PackManager;
import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.DatabaseErrorOutputBoundary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * A frame for adding pack.
 */
public class AddPackFrame extends BasicFrame implements ActionListener {
    private final JTextField packText;
    private final JButton addButton;
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

        add(panel);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            if (checkEmpty()) {
                JOptionPane.showMessageDialog(this,
                        Constants.PACK_NAME_EMPTY,
                        Constants.ADD_FAIL,
                        JOptionPane.WARNING_MESSAGE);
            } else if (check()) {  // add succeeds
                setVisible(false);
                new PackListFrame(programStateInputBoundary);
            } else {    // add fails: pack already exists
                JOptionPane.showMessageDialog(this,
                        Constants.PACK_EXISTED,
                        Constants.ADD_FAIL,
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
     * Check if this pack name has already existed.
     *
     * @return true if this pack name hasn't existed yet (can be added); false otherwise.
     */
    protected boolean check() {
        String pack = packText.getText();

        // Construct PackManager
        DatabaseErrorOutputBoundary dbPresenter = new DatabaseErrMsgPresenter(new DatabaseErrorWindow());
        PackInputBoundary pkManager = new PackManager(programStateInputBoundary);
        // Construct PackController
        PackController pkController = new PackController(pkManager, dbPresenter);
        // check add
        IDataInOut dataInOut = new DataInOut();
        AddOutputBoundary addPresenter = new AddPresenter();
        pkController.addNewPack(pack, addPresenter, dataInOut);
        return addPresenter.getAddResult();
    }


}

