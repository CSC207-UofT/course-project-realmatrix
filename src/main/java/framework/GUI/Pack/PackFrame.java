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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class PackFrame extends BasicFrame implements ActionListener {
    private final JPanel panel = new JPanel(null); // The whole panel in the frame
    // Pack name list
    private final JScrollPane packListPanel;  // A scrollable panel that stores a JList of pack names
    private final JList<String> packJList;  // A JList that contains pack names
    // Search
    private final JLabel searchLabel;
    private final JTextField searchText;    // A text field for user to enter pack name for sesarch
    // Sort  
    private final JLabel sortLabel;
    private final JComboBox<String> sortBox;

    private final JButton addButton; // Add pack
    private final JButton editButton; // Edit pack
    private final JButton deletePack; // Delete pack
    private final JButton backButton; // Back button
    private final PackInputBoundary packManager = new PackManager(new DataInOut(), programStateInputBoundary);
    private final PackController packController = new PackController(packManager, new DatabaseErrMsgPresenter());

    public PackFrame(ProgramStateInputBoundary programStateInputBoundary) {
        super("Pack List", programStateInputBoundary);
        // Construct packListPanel
        packJList = new JList(getPackList().toArray());
        packListPanel = new JScrollPane(packJList);
        packListPanel.setSize(250, 600);
        panel.add(packListPanel);

        // Search
        searchLabel = new JLabel("Search:");
        searchLabel.setBounds(280, 20, 50, 30);
        panel.add(searchLabel);

        searchText = new JTextField("");
        searchText.setBounds(335, 20, 140, 30);
        searchFunctionality();
        panel.add(searchText);

        // Sort
        sortLabel = new JLabel("Sort by: ");
        sortLabel.setBounds(280, 60, 60, 30);
        panel.add(sortLabel);

        String[] sortOptions = new String[] {"Old - New", "A - Z"};
        sortBox = new JComboBox<>(sortOptions);
        sortBox.setBounds(335, 60, 150, 30);
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
        // TODO: add action listener for edit button
        panel.add(editButton);

        // Delete pack
        deletePack = new JButton("Delete pack");
        deletePack.setBounds(280, 280, 200, 50);
        // TODO: add action listener
        panel.add(deletePack);

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

    private void searchFunctionality() {
        searchText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchPackOutputBoundary searchPackOutputBoundary = new SearchPackPresenter();
                packController.searchPack(searchText.getText(), searchPackOutputBoundary);
                searchPackOutputBoundary.getSearchResult();
//                checkSearch()
                // TODO: if no item, pop window
            }
        });
    }

    /**
     * Helper method for construct JList of packs.
     * Get a list of pack names this current user has.
     * @return an arraylist of pack names.
     */
    private ArrayList<String> getPackList() {
        // Construct packManager
        IDataInOut dataInOut = new DataInOut();
        PackInputBoundary packManager = new PackManager(dataInOut, programStateInputBoundary);
        // Construct packController
        DatabaseErrorOutputBoundary dbPresenter = new DatabaseErrMsgPresenter();
        PackController packController = new PackController(packManager, dbPresenter);
        // Get arraylist of pack names
        SortPackOutputBoundary sortPackPresenter = new SortPackPresenter();
        packController.sortOldToNew(sortPackPresenter);
        return sortPackPresenter.getSortResult();
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

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
