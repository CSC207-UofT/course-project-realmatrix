package framework.GUI.card;

import javax.swing.*;
import framework.GUI.BasicFrame;
import interface_adapter.Controller.CardController;
import interface_adapter.gateway.DataInOut;
import interface_adapter.gateway.IDataInOut;
import interface_adapter.presenters.ChangePresenter;
import interface_adapter.presenters.DatabaseErrMsgPresenter;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.input_boundaries.CardInputBoundary;
import use_case.manager.ProgramStateManager;
import use_case.manager.CardManager;
import use_case.output_boundaries.ChangeOutputBoundary;
import use_case.output_boundaries.DatabaseErrorOutputBoundary;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class EditCardFrame extends BasicFrame implements ActionListener {
    private final JTextField termText = new JTextField(100);
    private final JTextArea defText = new JTextArea();
    private final JButton editButton;
    private final JLabel success;
    private final JButton backButton;

    /** get original term in the card */
    private String old_term = termText.getText();

    public EditCardFrame(ProgramStateInputBoundary programStateInputBoundary) {
        super("Edit Card", programStateInputBoundary);
        JPanel panel = new JPanel();

        panel.setLayout(null);

        JLabel termLabel = new JLabel("Term");
        termLabel.setBounds(20, 20, 80, 25);
        panel.add(termLabel);

        // set termText
        termText.setBounds(100, 20, 300, 25);
        panel.add(termText);
        termText.setEditable(true);

        JLabel defLabel = new JLabel("Definition");
        defLabel.setBounds(20, 50, 80, 25);
        panel.add(defLabel);

        // set defText
        defText.setBounds(100, 50, 390, 100);
        defText.setLineWrap(true);
        panel.add(defText);
        defText.setEditable(true);

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
                success.setText("Edit card successful!");
                termText.setText(termText.getText());
                defText.setText(defText.getText());
                //TODO: go to CardFrame.
            } else {    // add fails: card already exists
                JOptionPane.showMessageDialog(this,
                        "This Card term has existed. Edit another one please~", // TODO: constant
                        "Edit Fails",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == backButton) {
            programStateInputBoundary.setCurrCard(null);
            // TODO: go to CardFrame
        }
    }

    protected boolean check() {
        // get new term and def edited by users
        String new_term = termText.getText();
        String new_def = defText.getText();
        // Construct CardManager
        IDataInOut dataInOut = new DataInOut();
        DatabaseErrorOutputBoundary dbPresenter = new DatabaseErrMsgPresenter();
        CardInputBoundary cardManager = new CardManager(dataInOut, programStateInputBoundary);
        // Construct CardController
        CardController cdController = new CardController(cardManager, dbPresenter);
        // check edit
        ChangeOutputBoundary changePresenter = new ChangePresenter();
        cdController.changeCardTerm(old_term, new_term, changePresenter);
        cdController.changeCardDefinition(new_def);
        return changePresenter.getChangeResult();
    }

    // Test
    public static void main(String[] args) {
        ProgramStateInputBoundary ps = new ProgramStateManager();
        new EditCardFrame(ps);
    }
}


