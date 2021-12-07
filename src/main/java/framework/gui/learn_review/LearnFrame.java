package framework.gui.learn_review;

import framework.gui.BasicFrame;
import framework.gui.card.CardListFrame;
import interface_adapter.controller.LearnController;
import interface_adapter.presenters.LearnPresenter;
import use_case.generator.LearnGenerator;
import use_case.input_boundaries.LearnInputBoundary;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.output_boundaries.LearnOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LearnFrame extends BasicFrame implements ActionListener {
    private final JLabel card;
    private final JButton backToPack;
    private final JButton nextButton;

    final LearnOutputBoundary learnOutputBoundary;
    final LearnInputBoundary learnInputBoundary;
    final LearnController learnController;

    public LearnFrame(ProgramStateInputBoundary programStateInputBoundary) {
        super("Learning", programStateInputBoundary);

        learnOutputBoundary = new LearnPresenter();
        learnInputBoundary = new LearnGenerator(programStateInputBoundary.getCurrPack(), learnOutputBoundary);
        learnController = new LearnController(learnInputBoundary);

        this.setSize(600, 400);
        JPanel learnPanel = new JPanel();

        learnController.next();
        this.card = new JLabel("<html><div style=\"width: 500\">" + learnOutputBoundary.getCurrCardStrRep()
                + "</div><html>", SwingConstants.LEFT);

        this.card.setFont(new Font("verdana", Font.BOLD, 18));

        this.card.setForeground(Color.blue);


        this.nextButton = new JButton("Next");
        this.nextButton.setFont(new Font("arial", Font.PLAIN, 20));
        this.nextButton.addActionListener(this);


        this.backToPack = new JButton("Back to pack: " + programStateInputBoundary.getCurrPackName());
        this.backToPack.setFont(new Font("arial", Font.PLAIN, 15));
        this.backToPack.addActionListener(this);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(this.backToPack);
        JPanel mid = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mid.setBounds(0, 100, 600, 500);
        mid.add(this.card);
        JScrollPane scroller = new JScrollPane(mid, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JPanel low = new JPanel(new FlowLayout(FlowLayout.CENTER));
        low.add(this.nextButton);

        mid.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

        learnPanel.setLayout(new BorderLayout());
        learnPanel.add(top, BorderLayout.NORTH);
        learnPanel.add(scroller, BorderLayout.CENTER);
        learnPanel.add(low, BorderLayout.SOUTH);


        add(learnPanel);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.nextButton) {
            String currCardStrRep = null;
            learnController.next();
            try {
                currCardStrRep = learnOutputBoundary.getCurrCardStrRep();
            } catch (NullPointerException ex) {
                learnOutputBoundary.setLearnCompleted();
            }
            if (!learnOutputBoundary.getLearnCompleted()) {
                this.card.setText("<html><div style=\"width: 500\">" + currCardStrRep + "</div><html>");
            } else {
                JOptionPane.showMessageDialog(this, "You have finished learning all the cards.",
                        "Good job!", JOptionPane.INFORMATION_MESSAGE);
                new CardListFrame(programStateInputBoundary);
                setVisible(false);
            }

        } else if (e.getSource() == this.backToPack) {
            new CardListFrame(programStateInputBoundary);
            setVisible(false);
        }
    }


}
