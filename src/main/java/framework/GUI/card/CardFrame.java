package framework.GUI.card;

import framework.GUI.BasicFrame;
import use_case.input_boundaries.ProgramStateInputBoundary;

import javax.swing.*;
import java.awt.*;

/**
 * A CardFrame shows a card's term and definition.
 */
public class CardFrame extends BasicFrame {

    public CardFrame(ProgramStateInputBoundary programStateInputBoundary) {
        super("Card", programStateInputBoundary);
        JPanel panel = new JPanel(new GridLayout(2, 1));

        // Add card term
        JLabel term = new JLabel(psController.getCurrCardTerm(), SwingConstants.CENTER);
        panel.add(term);

        // Add card definition
        JTextArea def = new JTextArea(psController.getCurrCardDef());
        def.setEditable(false);
        panel.add(def);

        add(panel);

        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
