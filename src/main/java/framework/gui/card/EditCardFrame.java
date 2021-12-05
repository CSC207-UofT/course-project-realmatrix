package framework.gui.card;

import framework.gui.BasicFrame;
import framework.gui.database_error.DatabaseErrorWindow;
import interface_adapter.controller.CardController;
import interface_adapter.gateway.DataInOut;
import interface_adapter.presenters.ChangePresenter;
import interface_adapter.presenters.DatabaseErrMsgPresenter;
import use_case.input_boundaries.CardInputBoundary;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.manager.CardManager;
import use_case.output_boundaries.ChangeOutputBoundary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A frame for editing card.
 */
public class EditCardFrame extends BasicFrame implements ActionListener {
    private final JTextArea termText;
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
        termText = new JTextArea(psController.getCurrCardTerm());
        termText.setLineWrap(true);
        JScrollPane termTextScroll = new JScrollPane(termText);
        termTextScroll.setBounds(100, 20, 390, 70);
        panel.add(termTextScroll);

        JLabel defLabel = new JLabel("Definition");
        defLabel.setBounds(20, 100, 80, 25);
        panel.add(defLabel);

        // set defText
        defText = new JTextArea(psController.getCurrCardDef());
        defText.setLineWrap(true);
        defText.setEditable(true);
        JScrollPane defTextScroll = new JScrollPane(defText);
        defTextScroll.setBounds(100, 100, 390, 100);
        panel.add(defTextScroll);

        editButton = new JButton("Edit");
        editButton.setBounds(400, 200, 80, 40);
        editButton.addActionListener(this);
        panel.add(editButton);

        backButton = new JButton("Back");
        backButton.setBounds(10, 200, 80, 40);
        backButton.addActionListener(this);
        panel.add(backButton);

        // Construct cardController
        CardInputBoundary cardManager = new CardManager(programStateInputBoundary);
        cardController = new CardController(cardManager, new DatabaseErrMsgPresenter(new DatabaseErrorWindow()));

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
            if (checkEmpty()) { // check if user's input is empty.
                JOptionPane.showMessageDialog(this,
                        "Term and definition can't be empty",
                        "Edit fails",
                        JOptionPane.WARNING_MESSAGE);
            }
            // if the user changed definition, always succeeds.
            else if (termText.getText().equals(psController.getCurrCardTerm())) {
                cardController.changeCardDefinition(defText.getText(), new DataInOut());
                psController.setCurrCard(null);
                new CardListFrame(programStateInputBoundary);
                setVisible(false);
            } else if (check()){ // edit card term succeeds
                psController.setCurrCard(null);
                new CardListFrame(programStateInputBoundary);
                setVisible(false);
            } else {    // edit fails: card term already exists
                JOptionPane.showMessageDialog(this,
                        "This Card term has existed. Edit another one please~", // TODO: constant
                        "Edit Fails",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource() == backButton) {
            psController.setCurrCard(null);
            new CardListFrame(programStateInputBoundary);
            setVisible(false);
        }
    }

    /**
     * Check if the user's input (term/definition) is empty.
     * @return true if it's empty; false otherwise
     */
    private boolean checkEmpty() {
        return (termText.getText().length() == 0 || defText.getText().length() == 0);
    }

    /**
     * Check if edit card term is successful.
     * @return if edit is successful. If card term already exist, fails. Otherwise, success.
     */
    private boolean check() {
        String oldTerm = psController.getCurrCardTerm();
        String newTerm = termText.getText();
        // check edit
        ChangeOutputBoundary changePresenter = new ChangePresenter();
        cardController.changeCardTerm(oldTerm, newTerm, new DataInOut(), changePresenter);
        return changePresenter.getChangeResult();
    }
}


