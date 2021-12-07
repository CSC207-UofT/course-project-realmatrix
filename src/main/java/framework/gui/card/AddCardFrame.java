package framework.gui.card;

import framework.gui.BasicFrame;
import framework.gui.database_error.DatabaseErrorWindow;
import interface_adapter.controller.CardController;
import interface_adapter.gateway.DataInOut;
import interface_adapter.gateway.IDataInOut;
import interface_adapter.presenters.AddPresenter;
import interface_adapter.presenters.DatabaseErrMsgPresenter;
import use_case.constants.Constants;
import use_case.input_boundaries.CardInputBoundary;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.manager.CardManager;
import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.DatabaseErrorOutputBoundary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * A frame for adding card.
 */
public class AddCardFrame extends BasicFrame implements ActionListener {
    private final JTextArea termText;
    private final JTextArea defText;
    private final JButton addButton;
    private final JButton backButton;


    public AddCardFrame(ProgramStateInputBoundary programStateInputBoundary) {
        super("Add Card", programStateInputBoundary);
        JPanel panel = new JPanel();

        panel.setLayout(null);

        JLabel termLabel = new JLabel("Term");
        termLabel.setBounds(20, 20, 80, 25);
        panel.add(termLabel);

        termText = new JTextArea();
        termText.setLineWrap(true);
        JScrollPane termTextScroll = new JScrollPane(termText);
        termTextScroll.setBounds(100, 20, 390, 70);
        panel.add(termTextScroll);

        JLabel defLabel = new JLabel("Definition");
        defLabel.setBounds(20, 100, 80, 25);
        panel.add(defLabel);

        defText = new JTextArea();
        defText.setLineWrap(true);
        JScrollPane defTextScroll = new JScrollPane(defText);
        defTextScroll.setBounds(100, 100, 390, 100);
        panel.add(defTextScroll);

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

    /**
     * Actions for clicking add/back button.
     *
     * @param e an action event triggered by user.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            if (checkEmpty()) {
                JOptionPane.showMessageDialog(this,
                        Constants.EMPTY_TERM_DEF,
                        Constants.ADD_FAIL,
                        JOptionPane.WARNING_MESSAGE);
            } else if (check()) {  // add succeeds
                setVisible(false);
                new CardListFrame(programStateInputBoundary);
            } else {    // add fails: card already exists
                JOptionPane.showMessageDialog(this,
                        Constants.ADD_CARD_EXISTED,
                        Constants.ADD_FAIL,
                        JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource() == backButton) {
            new CardListFrame(programStateInputBoundary);
            setVisible(false);
        }

    }

    /**
     * Check if the user's input (term/definition) is empty.
     *
     * @return true if it's empty; false otherwise
     */
    private boolean checkEmpty() {
        return (termText.getText().length() == 0 || defText.getText().length() == 0);
    }

    protected boolean check() {
        String term = termText.getText();
        String def = defText.getText();

        // Construct CardManager
        DatabaseErrorOutputBoundary dbPresenter = new DatabaseErrMsgPresenter(new DatabaseErrorWindow());
        CardInputBoundary cardManager = new CardManager(programStateInputBoundary);
        // Construct CardController
        CardController cdController = new CardController(cardManager, dbPresenter);
        // check add
        AddOutputBoundary addPresenter = new AddPresenter();
        IDataInOut dataInOut = new DataInOut();
        cdController.addNewCard(term, def, dataInOut, addPresenter);
        return addPresenter.getAddResult();
    }


}
