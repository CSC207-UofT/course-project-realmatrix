package framework.GUI.card;

import framework.GUI.BasicFrame;
import interface_adapter.Controller.CardController;
import interface_adapter.gateway.DataInOut;
import interface_adapter.presenters.ChangePresenter;
import interface_adapter.presenters.DatabaseErrMsgPresenter;
import use_case.input_boundaries.CardInputBoundary;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.manager.CardManager;
import use_case.manager.ProgramStateManager;
import use_case.output_boundaries.ChangeOutputBoundary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A frame for editing card.
 */
public class EditCardFrame extends BasicFrame implements ActionListener {
    private final JTextField termText;
    private final JTextArea defText;
    private final JButton editButton;
    private final JButton backButton;

    private final CardController cardController;

    public EditCardFrame(ProgramStateInputBoundary programStateInputBoundary) {
        super("Edit Card", programStateInputBoundary);
        JPanel panel = new JPanel();

        panel.setLayout(null);

        JLabel termLabel = new JLabel("Term");
        termLabel.setBounds(20, 20, 80, 25);
        panel.add(termLabel);

        // set termText
        termText = new JTextField(programStateInputBoundary.getCurrCardTerm(), 100);
        termText.setBounds(100, 20, 300, 25);
        panel.add(termText);
        termText.setEditable(true);

        JLabel defLabel = new JLabel("Definition");
        defLabel.setBounds(20, 50, 80, 25);
        panel.add(defLabel);

        // set defText
        defText = new JTextArea(programStateInputBoundary.getCurrCardDefinition());
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

        // Construct cardController
        CardInputBoundary cardManager = new CardManager(new DataInOut(), programStateInputBoundary);
        cardController = new CardController(cardManager, new DatabaseErrMsgPresenter());

        add(panel);
        setVisible(true);

    }

    /**
     * Actions for edit/back.
     * @param e an action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editButton) {
            // if the user changed definition, always succeeds.
            if (termText.getText().equals(programStateInputBoundary.getCurrCardTerm())) {
                cardController.changeCardDefinition(defText.getText());
                programStateInputBoundary.setCurrCard(null);
                new CardFrame(programStateInputBoundary);
                setVisible(false);
            } else if (check()){ // edit card term succeeds
                programStateInputBoundary.setCurrCard(null);
                new CardFrame(programStateInputBoundary);
                setVisible(false);
            } else {    // edit fails: card term already exists
                JOptionPane.showMessageDialog(this,
                        "This Card term has existed. Edit another one please~", // TODO: constant
                        "Edit Fails",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == backButton) {
            programStateInputBoundary.setCurrCard(null);
            new CardFrame(programStateInputBoundary);
            setVisible(false);
        }
    }

    /**
     * Check if edit card term is successful.
     * @return if edit is successful. If card term already exist, fails. Otherwise, success.
     */
    private boolean check() {
        String oldTerm = programStateInputBoundary.getCurrCardTerm();
        String newTerm = termText.getText();
        // check edit
        ChangeOutputBoundary changePresenter = new ChangePresenter();
        cardController.changeCardTerm(oldTerm, newTerm, changePresenter);
        return changePresenter.getChangeResult();
    }
}


