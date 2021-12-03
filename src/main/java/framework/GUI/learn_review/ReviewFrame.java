package framework.GUI.learn_review;

import entity.Card;
import entity.Pack;
import entity.User;
import framework.GUI.BasicFrame;
import framework.GUI.card.CardListFrame;
import interface_adapter.controller.ReviewController;
import interface_adapter.presenters.ReviewPresenter;
import use_case.generator.ReviewGenerator;
import use_case.input_boundaries.ReviewInputBoundary;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.manager.ProgramStateManager;
import use_case.output_boundaries.ReviewOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// TODO: make layout prettier
public class ReviewFrame extends BasicFrame implements ActionListener {
    private final JPanel reviewPanel;
    private final JLabel card;
    private final JButton showDefButton;
    private final JButton remWrgButton;
    private final JButton remCrtButton;
    private final JButton backButton;

    ReviewOutputBoundary reviewOutputBoundary;
    ReviewInputBoundary reviewInputBoundary;
    ReviewController reviewController;

    public ReviewFrame(ProgramStateInputBoundary programStateInputBoundary) {
        super("Reviewing", programStateInputBoundary);

        reviewOutputBoundary = new ReviewPresenter();
        reviewInputBoundary = new ReviewGenerator(programStateInputBoundary.getCurrPack(), reviewOutputBoundary);
        reviewController = new ReviewController(reviewInputBoundary, programStateInputBoundary);

        this.setSize(500, 500);
        this.reviewPanel = new JPanel();
        this.reviewPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        try {
            reviewController.next();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.card = new JLabel("<html><p>" + reviewOutputBoundary.getCurrCardStrRep() + "</p><html>",
                SwingConstants.CENTER);
        this.card.setBounds(100, 100, 300, 400);
        this.card.setFont(new Font("verdana", Font.BOLD, 20));

        this.showDefButton = new JButton("Show Definition");
        this.showDefButton.setBounds(150, 500, 200, 50);
        this.setFont(new Font("arial", Font.PLAIN, 10));
        this.showDefButton.addActionListener(this);

        this.remWrgButton = new JButton("Remember Wrong");
        this.setFont(new Font("arial", Font.PLAIN, 10));
        this.remWrgButton.setBounds(10, 10, 200, 50);
        this.remWrgButton.addActionListener(this);

        this.remCrtButton = new JButton("Remember Correctly");
        this.setFont(new Font("arial", Font.PLAIN, 10));
        this.remCrtButton.setBounds(10, 10, 200, 50);
        this.remCrtButton.addActionListener(this);

        this.backButton = new JButton("Back to pack: " + programStateInputBoundary.getCurrPackName());
        this.setFont(new Font("arial", Font.PLAIN, 10));
        this.backButton.setBounds(10, 10, 200, 50);
        this.backButton.addActionListener(this);

        // Put constraints on different buttons
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.reviewPanel.add(remWrgButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        this.reviewPanel.add(remCrtButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        this.reviewPanel.add(showDefButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        this.reviewPanel.add(backButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        this.reviewPanel.add(card, gbc);

        add(reviewPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.showDefButton) {
            reviewController.setShowDefinition();
            if (reviewOutputBoundary.getCurrCardStrRep() != null) {
                this.card.setText("<html><p>" + reviewOutputBoundary.getCurrCardStrRep() + "</p><html>");
            }
        } else if (e.getSource() == this.remWrgButton) {
            reviewController.setCantRecall();
            try {
                reviewController.next();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            this.card.setText("<html><p>" + reviewOutputBoundary.getCurrCardStrRep() + "</p><html>");
        } else if (e.getSource() == this.remCrtButton) {
            try {
                reviewController.next();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (!reviewOutputBoundary.getReviewCompleted()) {
                this.card.setText("<html><p>" + reviewOutputBoundary.getCurrCardStrRep() + "</p><html>");
            } else {
                JOptionPane.showMessageDialog(this, "You have finished reviewing all the cards.",
                        "Good job!", JOptionPane.INFORMATION_MESSAGE);
                new CardListFrame(programStateInputBoundary);
                setVisible(false);
            }
        } else if (e.getSource() == this.backButton) {
            new CardListFrame(programStateInputBoundary);
            setVisible(false);
        }
    }

    public static void main(String[] args) throws IOException {
        ProgramStateInputBoundary ps = new ProgramStateManager();
        User user = new User("Runshi", "password");
        Pack vocab = new Pack("vocab_new");
        // each card appears at least (Constants.REVIEW_PROFICIENCY_MAX - card.getProficiency() + 1) times
        // a card will appear again if you press "remember wrong" button (that's why "at least")
        // example: c4 will appear 3-1+1=3 times. If you press "remember wrong" on that card twice (in two occurrences),
        // c4 will appear a total of 5 times
        // if you clicked on remember wrong/correctly and the text field doesn't change, that's not an error.
        // that's simply because that card appears again right after (remember some cards will appear more than once)
        // and yes we could improve this but right now it works
        // TODO: consult with Xing if you are confused. better ask than make changes not knowing what's happening!
        Card c1 = new Card("apple", "fruit");
        c1.setProficiency(2);
        Card c2 = new Card("banana", "another fruit");
        c2.setProficiency(3);
        Card c3 = new Card("bee", "animal");
        c3.setProficiency(3);
        Card c4 = new Card("new", "something you haven't seen before");
        c4.setProficiency(1);
        Card c5 = new Card("old", "something you have seen before");
        c5.setProficiency(0);
        vocab.addCard(c1);
        vocab.addCard(c2);
        vocab.addCard(c3);
        vocab.addCard(c4);
        vocab.addCard(c5);
        user.addPackage(vocab);
        ps.setCurrUser(user);
        ps.setCurrPack("vocab_new");
        new ReviewFrame(ps);
    }
}
