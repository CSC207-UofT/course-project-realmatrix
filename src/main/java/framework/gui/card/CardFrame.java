package framework.gui.card;

import framework.gui.BasicFrame;
import use_case.input_boundaries.ProgramStateInputBoundary;

import javax.swing.*;

/**
 * A CardFrame shows a card's term and definition.
 * This frame shows up when user double-clicks a card in CardListFrame.
 */
public class CardFrame extends BasicFrame {

    public CardFrame(ProgramStateInputBoundary programStateInputBoundary) {
        super("Card", programStateInputBoundary);
        JPanel panel = new JPanel(null);

        // Term label
        JLabel termLabel = new JLabel("Term: ");
        termLabel.setBounds(10, 10, 80, 20);
        panel.add(termLabel);

        // Term text area
        JTextArea term = new JTextArea(psController.getCurrCardTerm());
        term.setLineWrap(true);
        term.setEditable(false);
        JScrollPane termScrollable = new JScrollPane(term);
        termScrollable.setBounds(100, 10, 400, 100);
        panel.add(termScrollable);

        // Definition Label
        JLabel defLabel = new JLabel("Definition: ");
        defLabel.setBounds(10, 130, 80, 20);
        panel.add(defLabel);

        // Definition Text area
        JTextArea def = new JTextArea(psController.getCurrCardDef());
        def.setLineWrap(true);
        def.setEditable(false);
        JScrollPane defScrollable = new JScrollPane(def);
        defScrollable.setBounds(100, 130, 400, 150);
        panel.add(defScrollable);

        add(panel);

        setSize(550, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
