package framework.gui.pack;

import framework.gui.BasicFrame;
import framework.gui.card.CardListFrame;
import framework.gui.database_error.DatabaseErrorWindow;
import framework.gui.user.UserFrame;
import interface_adapter.controller.PackController;
import interface_adapter.gateway.DataInOut;
import interface_adapter.presenters.DatabaseErrMsgPresenter;
import interface_adapter.presenters.SortSearchPackPresenter;
import use_case.constants.Constants;
import use_case.input_boundaries.PackInputBoundary;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.manager.PackManager;
import use_case.output_boundaries.SortSearchPackOutputBoundary;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A pack frame where users can interact with all packs they've created, such as
 * - creating packs
 * - deleting packs
 * - sorting/searching packs
 * - editing packs (name)
 */
public class PackListFrame extends BasicFrame implements ActionListener {
    // Search
    private final JTextField searchText;    // A text field for user to enter pack name for search
    // Sort
    private final JComboBox<String> sortBox;
    private final JButton addButton; // Add pack button
    private final JButton editButton; // Edit pack button
    private final JButton deleteButton; // Delete pack button
    private final JButton backButton; // Back button
    private final PackController packController;
    private final JPanel panel;
    // Pack name list
    private DefaultListModel<String> packListModel;   // Model for JList
    private JList<String> packJList;  // A JList that contains pack names

    public PackListFrame(ProgramStateInputBoundary programStateInputBoundary) {
        super("Pack List", programStateInputBoundary);
        // Pack & programState controller
        PackInputBoundary packManager = new PackManager(programStateInputBoundary);
        packController = new PackController(packManager, new DatabaseErrMsgPresenter(new DatabaseErrorWindow()));

        // The whole panel in the frame
        panel = new JPanel(null);

        // Construct Pack JList
        constructPackList();

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

        String[] sortOptions = new String[]{"Old - New", "A - Z"};
        sortBox = new JComboBox<>(sortOptions);
        sortBox.setBounds(335, 60, 150, 30);
        sortBox.addActionListener(this);
        panel.add(sortBox);

        // Add pack
        addButton = new JButton("Add pack");
        addButton.setBounds(280, 120, 200, 50);
        addButton.addActionListener(this);
        panel.add(addButton);

        // Edit pack
        editButton = new JButton("Change pack name");
        editButton.setBounds(280, 200, 200, 50);
        editButton.addActionListener(this);
        panel.add(editButton);

        // Delete pack
        deleteButton = new JButton("Delete pack");
        deleteButton.setBounds(280, 280, 200, 50);
        deleteButton.addActionListener(this);
        panel.add(deleteButton);

        // Back button
        backButton = new JButton("Back to Home Page");
        backButton.setBounds(280, 430, 200, 50);
        backButton.addActionListener(this);
        panel.add(backButton);

        add(panel);
        setVisible(true);
    }

    private void constructPackList() {
        // Construct packListPanel
        packListModel = new DefaultListModel<>();
        setPackListModel(); // By default, the model contains pack names by old-to-new order

        packJList = new JList<>(packListModel);
        packJList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION); // User can only select one pack at a time
        addJListListener();

        // A scrollable panel that stores a JList of pack names
        JScrollPane packListPanel = new JScrollPane(packJList);
        packListPanel.setSize(250, 600);
        panel.add(packListPanel);
    }

    /**
     * Helper method for construct packListModel of JList.
     * Set this model to a list of pack names (in old-to-new order) this current user has.
     */
    private void setPackListModel() {
        packListModel.removeAllElements();
        // Get arraylist of pack names
        SortSearchPackOutputBoundary sortPackPresenter = new SortSearchPackPresenter();
        packController.sortOldToNew(sortPackPresenter);
        // Data for packListModel
        ArrayList<String> packList = sortPackPresenter.getSortSearchResult();
        // Add the pack names into model
        packList.forEach(packListModel::addElement);
    }

    /**
     * Set packListModel with a specified packNameList, used when search/sort.
     *
     * @param packNameList An arraylist that contains pack names in specific order.
     */
    private void setPackListModel(ArrayList<String> packNameList) {
        // Get arraylist of pack names
        packListModel.removeAllElements();
        // Add the pack names into model
        packNameList.forEach(packListModel::addElement);
    }

    /**
     * Constructing JList Listener (mouse listener)
     */
    private void addJListListener() {
        // When user double-click the pack, go into CardFrame that shows all cards in this pack
        packJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    psController.setCurrPack(packJList.getSelectedValue());
                    setVisible(false);
                    new CardListFrame(programStateInputBoundary);
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
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // When select packs, set programState to this pack
        String selectedPackName = packJList.getSelectedValue();
        psController.setCurrPack(selectedPackName);

        if (e.getSource() == sortBox) {
            String filter = (String) sortBox.getSelectedItem();
            switch (Objects.requireNonNull(filter)) {
                case "A - Z":
                    sortAToZ();
                    break;
                case "Old - New":
                    setPackListModel();
                    break;
            }
        } else if (e.getSource() == addButton) {
            new framework.gui.pack.AddPackFrame(programStateInputBoundary);
            setVisible(false);
        } else if (e.getSource() == editButton) {
            if (selectedPackName == null) {
                JOptionPane.showMessageDialog(this,
                        Constants.NO_SELECTED_PACK_MSG,
                        Constants.NO_PACK_FOR_EDITING,
                        JOptionPane.WARNING_MESSAGE);
            } else {
                new framework.gui.pack.EditPackFrame(programStateInputBoundary);
                setVisible(false);
            }
        } else if (e.getSource() == deleteButton) {
            if (selectedPackName == null) {
                JOptionPane.showMessageDialog(this,
                        Constants.NO_SELECTED_PACK_MSG,
                        Constants.NO_PACK_FOR_DELETION,
                        JOptionPane.WARNING_MESSAGE);
            } else {
                packController.deletePack(selectedPackName, new DataInOut());
                setPackListModel();
                psController.setCurrPack(null);
            }
        } else if (e.getSource() == backButton) {
            new UserFrame(psController.getCurrUserName(), programStateInputBoundary);
            setVisible(false);
        }

    }

    /**
     * Search functionality.
     */
    private void search() {
        SortSearchPackOutputBoundary sortSearchPackOutputBoundary = new SortSearchPackPresenter();
        packController.searchPack(searchText.getText(), sortSearchPackOutputBoundary);
        setPackListModel(sortSearchPackOutputBoundary.getSortSearchResult());
    }

    /**
     * Sort packs by pack names: A - Z order.
     */
    private void sortAToZ() {
        SortSearchPackOutputBoundary sortPackPresenter = new SortSearchPackPresenter();
        packController.sortAToZ(sortPackPresenter);
        setPackListModel(sortPackPresenter.getSortSearchResult());
    }


}
