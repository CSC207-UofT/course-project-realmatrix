package framework.GUI.card;

import javax.swing.*;
import framework.GUI.BasicFrame;
import interface_adapter.controller.CardController;
import interface_adapter.gateway.DataInOut;
import interface_adapter.gateway.IDataInOut;
import interface_adapter.presenters.DatabaseErrMsgPresenter;
import interface_adapter.presenters.AddPresenter;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.input_boundaries.CardInputBoundary;
import use_case.manager.ProgramStateManager;
import use_case.manager.CardManager;
import use_case.output_boundaries.AddOutputBoundary;
import use_case.output_boundaries.DatabaseErrorOutputBoundary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * A frame for adding card.
 */
public class AddCardFrame extends BasicFrame implements ActionListener {
    private final JTextField termText;
    private final JTextArea defText;
    private final JButton addButton;
    private final JButton backButton;


    public AddCardFrame(ProgramStateInputBoundary programStateInputBoundary) {
        super("Add Card", programStateInputBoundary);
        JPanel panel = new JPanel();

        panel.setLayout(null);

        JLabel termLabel = new JLabel("Term");
        termLabel.setBounds(20,20,80,25);
        panel.add(termLabel);

        termText = new JTextField(100);
        termText.setBounds(100,20,300,25);
        panel.add(termText);

        JLabel defLabel = new JLabel("Definition");
        defLabel.setBounds(20,50,80,25);
        panel.add(defLabel);

        defText = new JTextArea();
        defText.setBounds(100,50,390,100);
        defText.setLineWrap(true);
        panel.add(defText);

        addButton = new JButton("Add");
        addButton.setBounds(400,200,80,40);
        addButton.addActionListener(this);
        panel.add(addButton);

        backButton = new JButton("Back");
        backButton.setBounds(10,200,80,40);
        backButton.addActionListener(this);
        panel.add(backButton);

        add(panel);
        setVisible(true);

    }

    /**
     * Actions for clicking add/back button.
     * @param e an action event triggered by user.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            if (check()) {  // add succeeds
                setVisible(false);
                new CardListFrame(programStateInputBoundary);
            } else {    // add fails: card already exists
                JOptionPane.showMessageDialog(this,
                        "This Card term has existed. Add another one please~", // TODO: constant
                        "Add Fails",
                        JOptionPane.WARNING_MESSAGE);
            }
        }

        else if (e.getSource() == backButton) {
            new CardListFrame(programStateInputBoundary);
            setVisible(false);
        }

    }

    protected boolean check(){
        String term = termText.getText();
        String def = defText.getText();

        // Construct CardManager
        IDataInOut dataInOut = new DataInOut();
        DatabaseErrorOutputBoundary dbPresenter = new DatabaseErrMsgPresenter();
        CardInputBoundary cardManager = new CardManager(dataInOut, programStateInputBoundary);
        // Construct CardController
        CardController cdController = new CardController(cardManager, dbPresenter);
        // check add
        AddOutputBoundary addPresenter = new AddPresenter();
        cdController.addNewCard(term, def, addPresenter);
        return addPresenter.getAddResult();
    }
    // Test
    public static void main(String[] args) {
        ProgramStateInputBoundary ps = new ProgramStateManager();
        new AddCardFrame(ps);
    }

}
