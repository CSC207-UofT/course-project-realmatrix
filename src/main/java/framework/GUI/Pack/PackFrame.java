package framework.GUI.Pack;

import entity.User;
import framework.GUI.BasicFrame;
import framework.GUI.user.UserFrame;
import interface_adapter.Controller.PackController;
import interface_adapter.gateway.DataInOut;
import interface_adapter.gateway.IDataInOut;
import interface_adapter.gateway.dataout.Loader;
import interface_adapter.presenters.DatabaseErrMsgPresenter;
import interface_adapter.presenters.SearchPackPresenter;
import interface_adapter.presenters.SortPackPresenter;
import use_case.input_boundaries.PackInputBoundary;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.manager.PackManager;
import use_case.manager.ProgramStateManager;
import use_case.output_boundaries.DatabaseErrorOutputBoundary;
import use_case.output_boundaries.SearchPackOutputBoundary;
import use_case.output_boundaries.SortPackOutputBoundary;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class PackFrame extends BasicFrame implements ActionListener {
    private final JPanel panel = new JPanel(null); // The whole panel in the frame
    // Pack name list
    private final JScrollPane packListPanel;  // A scrollable panel that stores a JList of pack names
    private ArrayList<String> packList;     // Data for packListModel
    private final DefaultListModel<String> packListModel;   // Model for JList
    private final JList<String> packJList;  // A JList that contains pack names
    private String selectedPackName; // The pack name that the user selects
    // Search
    private final JLabel searchLabel;
    private final JTextField searchText;    // A text field for user to enter pack name for sesarch
    // Sort  
    private final JLabel sortLabel;
    private final JComboBox<String> sortBox;

    private final JButton addButton; // Add pack button
    private final JButton editButton; // Edit pack button
    private final JButton deleteButton; // Delete pack button
    private final JButton backButton; // Back button

    private final PackInputBoundary packManager = new PackManager(new DataInOut(), programStateInputBoundary);
    private final PackController packController = new PackController(packManager, new DatabaseErrMsgPresenter());

    public PackFrame(ProgramStateInputBoundary programStateInputBoundary) {
        super("Pack List", programStateInputBoundary);
        // Construct packListPanel
        packListModel = new DefaultListModel<>();
        setPackListModel(); // By default, the model contains pack names by old-to-new order

        packJList = new JList<>(packListModel);
        packJList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION); // User can only select one pack at a time
        packJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedPackName = packJList.getSelectedValue();
                programStateInputBoundary.setCurrPack(selectedPackName);
            }
        });

        packListPanel = new JScrollPane(packJList);
        packListPanel.setSize(250, 600);
        panel.add(packListPanel);

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
        
        // Add pack
        addButton = new JButton("Add pack");
        addButton.setBounds(280, 120, 200, 50);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddPackFrame(programStateInputBoundary);
            }
        });
        panel.add(addButton);

        // Edit pack
        editButton = new JButton("Edit pack");
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
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserFrame(programStateInputBoundary.getCurrUserName(), programStateInputBoundary);
            }
        });
        panel.add(backButton);

        add(panel);
        setVisible(true);
    }

    /**
     * Helper method for construct packListModel of JList.
     * Set this model to a list of pack names (in old-to-new order) this current user has.
     */
    private void setPackListModel() {
        packListModel.removeAllElements();
        // Get arraylist of pack names
        SortPackOutputBoundary sortPackPresenter = new SortPackPresenter();
        packController.sortOldToNew(sortPackPresenter);
        packList = sortPackPresenter.getSortResult();

        for (String packName : packList) {
            packListModel.addElement(packName);
        }
    }

    /**
     * Set packListModel with a specified packNameList, used when search/sort.
     * @param packNameList An arraylist that contains pack names in specific order.
     */
    private void setPackListModel(ArrayList<String> packNameList) {
        // Get arraylist of pack names
        packListModel.removeAllElements();
        for (String packName : packNameList) {
            packListModel.addElement(packName);
        }
    }

    /**
     * Search functionality.
     */
    private void search() {
        SearchPackOutputBoundary searchPackOutputBoundary = new SearchPackPresenter();
        packController.searchPack(searchText.getText(), searchPackOutputBoundary);
        setPackListModel(searchPackOutputBoundary.getSearchResult());
    }

    /**
     * Sort packs by pack names: A - Z order.
     */
    private void sortAToZ() {
        SortPackOutputBoundary sortPackPresenter = new SortPackPresenter();
        packController.sortAToZ(sortPackPresenter);
        setPackListModel(sortPackPresenter.getSortResult());
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
                    setPackListModel();
            }
        }

        if (e.getSource() == editButton) {
            if (selectedPackName == null) {
                JOptionPane.showMessageDialog(this,
                        "Please select a pack first.", // TODO: constant
                        "No pack for editting",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                new EditPackFrame(selectedPackName, programStateInputBoundary);
            }
        }

        if (e.getSource() == deleteButton) {
            if (selectedPackName == null) {
                JOptionPane.showMessageDialog(this,
                        "Please select a pack first.", // TODO: constant
                        "No pack for deletion",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                packController.deletePack(selectedPackName);
                setPackListModel();
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
        new PackFrame(ps);
    }
}
