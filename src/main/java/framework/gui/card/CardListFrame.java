package framework.gui.card;

import framework.gui.BasicFrame;
import framework.gui.database_error.DatabaseErrorWindow;
import framework.gui.learn_review.LearnFrame;
import framework.gui.learn_review.ReviewFrame;
import framework.gui.pack.PackListFrame;
import interface_adapter.controller.CardController;
import interface_adapter.controller.ProgramStateController;
import interface_adapter.gateway.DataInOut;
import interface_adapter.presenters.DatabaseErrMsgPresenter;
import interface_adapter.presenters.SortSearchCardPresenter;
import use_case.constants.Constants;
import use_case.input_boundaries.CardInputBoundary;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.manager.CardManager;
import use_case.output_boundaries.SortSearchCardOutputBoundary;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

/**
 * A card frame class where users can interact with all cards in current pack, such as
 * - adding card
 * - editing card
 * - deleting card
 * - sorting/searching cards
 * - reviewing/learning all cards.
 */
public class CardListFrame extends BasicFrame implements ActionListener {
    private final CardController cardController;
    private final JPanel panel; // Whole panel in the frame.

    // Card Table
    private final String[] colNames = new String[]{"Term", "Definition"};
    private DefaultTableModel cardTableModel;   // Model for JTable
    private JTable cardJTable;  // A JTable that contains card terms & definition
    private String selectedCardTerm;

    private final JTextField searchText;    // A text field for user to enter term for search

    private final JComboBox<String> sortBox;

    // Buttons
    private final JButton addButton; // Add card button
    private final JButton editButton; // Edit card button
    private final JButton deleteButton; // Delete card button
    private final JButton reviewButton; // Review card button
    private final JButton learnButton; // Learn card button
    private final JButton backButton; // Back button


    public CardListFrame(ProgramStateInputBoundary programStateInputBoundary) {
        super("Card List", programStateInputBoundary);
        setTitle("[" + psController.getCurrPackName() + "] Card List");
        // Card Controller
        CardInputBoundary cardManager = new CardManager(programStateInputBoundary);
        cardController = new CardController(cardManager, new DatabaseErrMsgPresenter(new DatabaseErrorWindow()));

        // The whole panel in the frame
        panel = new JPanel(null);

        //Construct a CardTable.
        constructCardTable();

        // Search
        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setBounds(280, 20, 50, 30);
        panel.add(searchLabel);

        searchText = new JTextField("");
        searchText.setBounds(335, 20, 140, 30);
        addSearchTextListener();
        panel.add(searchText);

        // Sort
        JLabel sortLabel = new JLabel("Sort by: ");
        sortLabel.setBounds(280, 60, 60, 30);
        panel.add(sortLabel);

        String[] sortOptions = new String[]{Constants.SORT_FROM_OLD_TO_NEW, Constants.SORT_FROM_A_TO_Z,
                Constants.SORT_BY_MANAGED};
        sortBox = new JComboBox<>(sortOptions);
        sortBox.setBounds(335, 60, 150, 30);
        addSortBoxListener();
        panel.add(sortBox);

        // Add Card
        addButton = new JButton("Add Card");
        addButton.setBounds(280, 120, 200, 50);
        addButton.addActionListener(this);
        panel.add(addButton);

        // Edit Card
        editButton = new JButton("Edit Card");
        editButton.setBounds(280, 200, 200, 50);
        editButton.addActionListener(this);
        panel.add(editButton);

        // Delete Card
        deleteButton = new JButton("Delete Card");
        deleteButton.setBounds(280, 280, 200, 50);
        deleteButton.addActionListener(this);
        panel.add(deleteButton);

        // Review Cards
        reviewButton = new JButton("Review Cards");
        reviewButton.setBounds(280, 360, 200, 50);
        reviewButton.setForeground(Color.red);
        reviewButton.addActionListener(this);
        panel.add(reviewButton);

        //Learn Cards
        learnButton = new JButton("Learn Cards");
        learnButton.setBounds(280, 440, 200, 50);
        learnButton.setForeground(Color.red);
        learnButton.addActionListener(this);
        panel.add(learnButton);

        // Back button
        backButton = new JButton("Back to PackList Page");
        backButton.setBounds(280, 520, 200, 50);
        backButton.addActionListener(this);
        panel.add(backButton);

        add(panel);
        setVisible(true);
    }

    /**
     * Construct a JTable that shows all cards.
     */
    private void constructCardTable() {
        // Construct CardTable
        cardTableModel = new DefaultTableModel() {  // Construct model that stores data
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        setCardTableModel(); // By default, the model contains Card by old-to-new order
        cardJTable = new JTable(cardTableModel);    // Construct table with model
        cardJTable.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION); // User can only select one card at a time
        addCardTableListener();

        // A scrollable panel that stores a JTable of card
        JScrollPane cardTablePanel = new JScrollPane(cardJTable);
        cardTablePanel.setSize(250, 600);
        panel.add(cardTablePanel);
    }

    /**
     * Helper method for construct cardTableModel.
     * Set this model to a list of Cards (in old-to-new order) this current pack has.
     */
    private void setCardTableModel() {
        // Get array of Cards
        SortSearchCardOutputBoundary sortSearchCardPresenter = new SortSearchCardPresenter();
        cardController.sortOldToNew(sortSearchCardPresenter);
        String[][] cardStrList = sortSearchCardPresenter.getSortSearchResult();

        cardTableModel.setDataVector(cardStrList, colNames);
    }

    /**
     * Set cardTableModel with a specified cardStrList, used when search/sort.
     *
     * @param cardStrList An array that contains Cards in specific order.
     */
    private void setCardTableModel(String[][] cardStrList) {
        // Get arraylist of Card
        cardTableModel.setDataVector(cardStrList, colNames);
    }

    /**
     * Constructing JTable Listener (mouse listener) for cards: double-click to view all cards.
     */
    private void addCardTableListener() {
        // When user double-click the pack, go into CardFrame that shows all cards in this pack
        cardJTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    setSelectedCardTerm();
                    new CardFrame(programStateInputBoundary);
                }
            }
        });
    }

    /**
     * Constructing search text field Document Listener
     */
    private void addSearchTextListener() {
        searchText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search();
            }
        });
    }

    /**
     * Search functionality.
     */
    private void search() {
        SortSearchCardOutputBoundary sortSearchCardOutputBoundary = new SortSearchCardPresenter();
        cardController.searchCard(searchText.getText(), sortSearchCardOutputBoundary);
        String[][] result = sortSearchCardOutputBoundary.getSortSearchResult();
        setCardTableModel(result);
    }

    /**
     * Add sort box listener and sort card according to user's choice.
     */
    private void addSortBoxListener() {
        sortBox.addActionListener(e -> {
            String filter = (String) sortBox.getSelectedItem();
            SortSearchCardOutputBoundary sortCardPresenter = new SortSearchCardPresenter();
            switch (Objects.requireNonNull(filter)) {
                case Constants.SORT_FROM_A_TO_Z:
                    cardController.sortAToZ(sortCardPresenter);
                    setCardTableModel(sortCardPresenter.getSortSearchResult());
                    break;
                case Constants.SORT_FROM_OLD_TO_NEW:
                    setCardTableModel();
                    break;
                case Constants.SORT_BY_MANAGED:
                    cardController.sortProLowToHigh(sortCardPresenter);
                    setCardTableModel(sortCardPresenter.getSortSearchResult());
                    break;
            }
        });
    }

    /**
     * Invoked when user presses add/delete/edit/review/learn/back button.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        setSelectedCardTerm();

        if (e.getSource() == addButton) {
            new AddCardFrame(programStateInputBoundary);
            setVisible(false);
        } else if (e.getSource() == editButton) {
            if (selectedCardTerm == null) {
                JOptionPane.showMessageDialog(this,
                        Constants.NO_SELECTED_CARD_MSG,
                        Constants.NO_CARD_FOR_EDITING,
                        JOptionPane.WARNING_MESSAGE);
            } else {
                new EditCardFrame(programStateInputBoundary);
                setVisible(false);
            }
        } else if (e.getSource() == deleteButton) {
            if (selectedCardTerm == null) {
                JOptionPane.showMessageDialog(this,
                        Constants.NO_SELECTED_CARD_MSG,
                        Constants.NO_CARD_FOR_DELETION,
                        JOptionPane.WARNING_MESSAGE);
            } else {
                cardController.deleteCard(selectedCardTerm, new DataInOut());
                setCardTableModel();
            }
        } else if (e.getSource() == reviewButton) {
            psController.setCurrCard(null);
            new ReviewFrame(programStateInputBoundary);
            setVisible(false);
        } else if (e.getSource() == learnButton) {
            psController.setCurrCard(null);
            new LearnFrame(programStateInputBoundary);
            setVisible(false);
        } else if (e.getSource() == backButton) {
            psController.setCurrPack(null);
            new PackListFrame(programStateInputBoundary);
            setVisible(false);
        }

    }

    /**
     * Get the card term that the user is currently selecting.
     */
    private void setSelectedCardTerm() {
        if (cardJTable.getSelectedRow() != -1) {
            selectedCardTerm = cardJTable.getValueAt(cardJTable.getSelectedRow(), 0).toString();
            ProgramStateController psController = new ProgramStateController(programStateInputBoundary);
            psController.setCurrCard(selectedCardTerm);
        }
    }


}
