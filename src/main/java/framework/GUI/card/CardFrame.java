package framework.GUI.card;

import entity.User;
import framework.GUI.BasicFrame;
import framework.GUI.Pack.PackFrame;
import interface_adapter.Controller.CardController;
import interface_adapter.gateway.DataInOut;
import interface_adapter.gateway.dataout.Loader;
import interface_adapter.presenters.*;
import use_case.input_boundaries.CardInputBoundary;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.manager.CardManager;
import use_case.manager.ProgramStateManager;
import use_case.output_boundaries.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class CardFrame extends BasicFrame implements ActionListener {

    private final JPanel panel = new JPanel(null); // The whole panel in the frame

    /** Construct Card Table */
    private final JScrollPane cardTablePanel;  // A scrollable panel that stores a JList of card
    private final String[] colNames = new String[]{"Term", "Definition"};
    private final DefaultTableModel cardTableModel;   // Model for JTable
    private final JTable cardJTable;  // A JTable that contains card terms & definition
    private final String selectedCardTerm = "";

    /** Construct Search */
    private final JLabel searchLabel;
    private final JTextField searchText;    // A text field for user to enter term for sesarch

    /**Construct Sort */
    private final JLabel sortLabel;
    private final JComboBox<String> sortBox;

    /** Buttons */
    private final JButton addButton; // Add card button
    private final JButton editButton; // Edit card button
    private final JButton deleteButton; // Delete card button
    private final JButton reviewButton; // Review card button
    private final JButton learnButton; // Learn card button
    private final JButton backButton; // Back button

    private final CardInputBoundary cardManager = new CardManager(new DataInOut(), programStateInputBoundary);
    private final CardController cardController = new CardController(cardManager, new DatabaseErrMsgPresenter());

    public CardFrame(ProgramStateInputBoundary programStateInputBoundary) {
        super("[" + programStateInputBoundary.getCurrPackName()+ "] Card List", programStateInputBoundary);
        // Construct cardStrListPanel
        cardTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        setCardTableModel(); // By default, the model contains Card by old-to-new order

        cardJTable = new JTable(cardTableModel);
        cardJTable.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION); // User can only select one card at a time
        addTableListener();

        cardTablePanel = new JScrollPane(cardJTable);
        cardTablePanel.setSize(250, 600);
        panel.add(cardTablePanel);

        // Search
        searchLabel = new JLabel("Search:");
        searchLabel.setBounds(280, 20, 50, 30);
        panel.add(searchLabel);

        searchText = new JTextField("");
        searchText.setBounds(335, 20, 140, 30);
        searchText.addActionListener(this);
        panel.add(searchText);

        // Sort
        sortLabel = new JLabel("Sort by: ");
        sortLabel.setBounds(280, 60, 60, 30);
        panel.add(sortLabel);

        String[] sortOptions = new String[] {"Old - New", "A - Z"};
        sortBox = new JComboBox<>(sortOptions);
        sortBox.setBounds(335, 60, 150, 30);
        sortBox.addActionListener(this);
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
     * Helper method for construct cardStrListModel of JList.
     * Set this model to a list of Cards (in old-to-new order) this current pack has.
     */
    private void setCardTableModel() {
        // Get arraylist of Cards
        SortSearchCardOutputBoundary sortSearchCardPresenter = new SortSearchCardPresenter();
        cardController.sortOldToNew(sortSearchCardPresenter);
        String[][] cardStrList = sortSearchCardPresenter.getSortSearchResult();

        cardTableModel.setDataVector(cardStrList, colNames);
    }

    /**
     * Set cardStrListModel with a specified cardStrList, used when search/sort.
     * @param cardStrList An arraylist that contains Cards in specific order.
     */
    private void setCardTableModel(String[][] cardStrList) {
        // Get arraylist of Card
        cardTableModel.setRowCount(0);
        cardTableModel.setDataVector(cardStrList, colNames);
        cardTableModel.setRowCount(cardStrList.length);
    }


    /**
     * Listener for selecting table row.
     * This helps set currCard in program state to the selected card.
     */
    private void addTableListener() {
        cardJTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String cardTerm = cardJTable.getValueAt(cardJTable.getSelectedRow(), 0).toString();
                programStateInputBoundary.setCurrCard(cardTerm);
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
     * Sort Card by A - Z order.
     */
    private void sortAToZ() {
        SortSearchCardOutputBoundary sortCardPresenter = new SortSearchCardPresenter();
        cardController.sortAToZ(sortCardPresenter);
        setCardTableModel(sortCardPresenter.getSortSearchResult());
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchText) {
            search();
        }

        if (e.getSource() == sortBox) {
            String filter = (String) sortBox.getSelectedItem();
            switch (Objects.requireNonNull(filter)) {
                case "A - Z":
                    sortAToZ();
                    break;
                case "Old - New":
                    setCardTableModel();
            }
        }

        if (e.getSource() == addButton) {
            new AddCardFrame(programStateInputBoundary);
            setVisible(false);
        }

        if (e.getSource() == editButton) {
            if (selectedCardTerm == null) {
                JOptionPane.showMessageDialog(this,
                        "Please select a Card first.", // TODO: constant
                        "No Card for editting",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                new EditCardFrame(programStateInputBoundary);
                setVisible(false);
            }
        }

        if (e.getSource() == deleteButton) {
            if (selectedCardTerm == null) {
                JOptionPane.showMessageDialog(this,
                        "Please select a card first.", // TODO: constant
                        "No Card for deletion",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                cardController.deleteCard(selectedCardTerm);
                setCardTableModel();
            }
        }

        if (e.getSource() == reviewButton) {
            if (selectedCardTerm == null) {
                JOptionPane.showMessageDialog(this,
                        "Please select a card first.", // TODO: constant
                        "No Card for deletion",
                        JOptionPane.WARNING_MESSAGE);
            } else {
            // TODO: go review frame.
                setVisible(false);
            }
        }

        if (e.getSource() == learnButton) {
            if (selectedCardTerm == null) {
                JOptionPane.showMessageDialog(this,
                        "Please select a card first.", // TODO: constant
                        "No Card for deletion",
                        JOptionPane.WARNING_MESSAGE);
            } else {
             // TODO: go learn frame.
                setVisible(false);
            }
        }

        if (e.getSource() == backButton) {
            programStateInputBoundary.setCurrPack(null);
            new PackFrame(programStateInputBoundary);
            setVisible(false);
        }

    }

    //Test
    public static void main(String[] args) throws IOException {
        ProgramStateInputBoundary ps = new ProgramStateManager();
        User user = new User("Xing", "password");
        Loader loader = new Loader();
        loader.userLoad(user);
        ps.setCurrUser(user);
        ps.setCurrPack("vocabulary");
        new CardFrame(ps);
    }
}

