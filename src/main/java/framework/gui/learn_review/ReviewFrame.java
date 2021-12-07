package framework.gui.learn_review;

import framework.gui.BasicFrame;
import framework.gui.card.CardListFrame;
import framework.gui.database_error.DatabaseErrorWindow;
import interface_adapter.controller.ReviewController;
import interface_adapter.presenters.ReviewPresenter;
import use_case.generator.ReviewGenerator;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.input_boundaries.ReviewInputBoundary;
import use_case.output_boundaries.ReviewOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ReviewFrame extends BasicFrame implements ActionListener {
    private final JLabel card;
    private final JButton showDefButton;
    private final JButton remWrgButton;
    private final JButton remCrtButton;
    private final JButton backButton;

    final ReviewOutputBoundary reviewOutputBoundary;
    final ReviewInputBoundary reviewInputBoundary;
    final ReviewController reviewController;

    public ReviewFrame(ProgramStateInputBoundary programStateInputBoundary) {
        super("Reviewing", programStateInputBoundary);

        reviewOutputBoundary = new ReviewPresenter();
        reviewInputBoundary = new ReviewGenerator(programStateInputBoundary.getCurrPack(), reviewOutputBoundary);
        reviewController = new ReviewController(reviewInputBoundary, programStateInputBoundary);

        this.setSize(600, 400);
        JPanel reviewPanel = new JPanel();

        try {
            reviewController.next();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.card = new JLabel("<html><div style=\"width: 500\">" + reviewOutputBoundary.getCurrCardStrRep()
                + "</div><html>", SwingConstants.LEFT);
        this.card.setBounds(100, 100, 300, 400);
        this.card.setFont(new Font("verdana", Font.BOLD, 18));
        this.card.setForeground(Color.blue);

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

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(this.backButton);
        JPanel mid = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mid.setBounds(0, 100, 600, 500);
        mid.add(this.card);
        JScrollPane scroller = new JScrollPane(mid, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel subPanel = new JPanel();
        subPanel.add(this.remWrgButton);
        subPanel.add(this.showDefButton);
        subPanel.add(this.remCrtButton);

        mid.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

        reviewPanel.setLayout(new BorderLayout());
        reviewPanel.add(top, BorderLayout.NORTH);
        reviewPanel.add(scroller, BorderLayout.CENTER);
        reviewPanel.add(subPanel, BorderLayout.SOUTH);

        add(reviewPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.showDefButton) {
            reviewController.setShowDefinition();
            if (reviewOutputBoundary.getCurrCardStrRep() != null) {
                this.card.setText("<html><div style=\"width: 500\">" + reviewOutputBoundary.getCurrCardStrRep() + "</div><html>");
            }
        } else if (e.getSource() == this.remWrgButton) {
            reviewController.setCantRecall();
            try {
                reviewController.next();
            } catch (IOException ex) {
                new DatabaseErrorWindow().presentWriteErrMsg();
                ex.printStackTrace();
            }
            this.card.setText("<html><div style=\"width: 500\">" + reviewOutputBoundary.getCurrCardStrRep() + "</div><html>");
        } else if (e.getSource() == this.remCrtButton) {
            try {
                reviewController.next();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (!reviewOutputBoundary.getReviewCompleted()) {
                this.card.setText("<html><div style=\"width: 500\">" + reviewOutputBoundary.getCurrCardStrRep() + "</div><html>");
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


}
