package framework.GUI.card;

import entity.User;
import framework.GUI.BasicFrame;
import framework.GUI.Pack.PackFrame;
import framework.GUI.user.UserFrame;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CardFrame extends BasicFrame implements ActionListener {

    private final JPanel panel = new JPanel(null); // The whole panel in the frame

    /** Construct Card List */
    private final JScrollPane cardListPanel;  // A scrollable panel that stores a JList of card
    private ArrayList<String[]> cardList;     // Data for cardListModel
    private final DefaultListModel<String[]> cardListModel;   // Model for JList
    private final JList<String> cardJList;  // A JList that contains card names
    private String selectedCardName; // The term in cards that the user selects

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
        super("Card List", programStateInputBoundary);
        // Construct cardListPanel
        cardListModel = new DefaultListModel<>();
        setCardListModel(); // By default, the model contains Card by old-to-new order

        cardJList = new JList(cardListModel);
        cardJList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION); // User can only select one card at a time
        cardJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedCardName = cardJList.getSelectedValue();
                programStateInputBoundary.setCurrCard(selectedCardName);
            }
        });

        cardListPanel = new JScrollPane(cardJList);
        cardListPanel.setSize(250, 600);
        panel.add(cardListPanel);

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
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCardFrame(programStateInputBoundary);
                setVisible(false);
            }
        });
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

        // Review Card
        reviewButton = new JButton("Review Card");
        reviewButton.setBounds(280, 280, 200, 50);
        reviewButton.addActionListener(this);
        panel.add(reviewButton);

        //Learn Card
        learnButton = new JButton("Learn Card");
        learnButton.setBounds(280, 280, 200, 50);
        learnButton.addActionListener(this);
        panel.add(learnButton);

        // Back button
        backButton = new JButton("Back to Pack Page");
        backButton.setBounds(280, 430, 200, 50);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PackFrame(programStateInputBoundary);
                setVisible(false);
            }
        });
        panel.add(backButton);

        add(panel);
        setVisible(true);
    }

    /**
     * Helper method for construct cardListModel of JList.
     * Set this model to a list of Cards (in old-to-new order) this current pack has.
     */
    private void setCardListModel() {
        cardListModel.removeAllElements();
        // Get arraylist of Cards
        SortCardOutputBoundary sortCardPresenter = new SortCardPresenter();
        cardController.sortOldToNew(sortCardPresenter);
        cardList = sortCardPresenter.getSortResult();

        for (String[] cardName : cardList) {
            cardListModel.addElement(cardName);
        }
    }

    /**
     * Set cardListModel with a specified cardNameList, used when search/sort.
     * @param cardNameList An arraylist that contains Cards in specific order.
     */
    private void setCardListModel(ArrayList<String[]> cardNameList) {
        // Get arraylist of Card
        cardListModel.removeAllElements();
        for (String[] cardName : cardNameList) {
            cardListModel.addElement(cardName);
        }
    }

    /**
     * Search functionality.
     */
    private void search() {
        SearchCardOutputBoundary searchCardOutputBoundary = new SearchCardPresenter();
        cardController.searchCard(searchText.getText(), searchCardOutputBoundary);
        ArrayList result = new ArrayList(searchCardOutputBoundary.getSearchResult().keySet());
        setCardListModel(result);
    }

    /**
     * Sort Card by A - Z order.
     */
    private void sortAToZ() {
        SortCardOutputBoundary sortCardPresenter = new SortCardPresenter();
        cardController.sortAToZ(sortCardPresenter);
        setCardListModel(sortCardPresenter.getSortResult());
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
                    setCardListModel();
            }
        }

        if (e.getSource() == editButton) {
            if (selectedCardName == null) {
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
            if (selectedCardName == null) {
                JOptionPane.showMessageDialog(this,
                        "Please select a card first.", // TODO: constant
                        "No Card for deletion",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                cardController.deleteCard(selectedCardName);
                setCardListModel();
            }
        }

        if (e.getSource() == reviewButton) {
            if (selectedCardName == null) {
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
            if (selectedCardName == null) {
                JOptionPane.showMessageDialog(this,
                        "Please select a card first.", // TODO: constant
                        "No Card for deletion",
                        JOptionPane.WARNING_MESSAGE);
            } else {
             // TODO: go learn frame.
                setVisible(false);
            }
        }


    }

    //Test
    public static void main(String[] args) throws IOException {
        ProgramStateInputBoundary ps = new ProgramStateManager();
        User user = new User("Xing", "password");
        Loader loader = new Loader();
        loader.userLoad(user);
        ps.setCurrUser(user);
        new CardFrame(ps);
    }
}

