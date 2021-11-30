package framework.GUI.learn_review;

import entity.Card;
import entity.Pack;
import entity.User;
import framework.GUI.BasicFrame;
import framework.GUI.card.CardListFrame;
import interface_adapter.controller.LearnController;
import interface_adapter.gateway.dataout.Loader;
import interface_adapter.presenters.LearnPresenter;
import use_case.generator.LearnGenerator;
import use_case.input_boundaries.LearnInputBoundary;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.manager.ProgramStateManager;
import use_case.output_boundaries.LearnOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LearnFrame extends BasicFrame implements ActionListener {
    private final JPanel learnPanel;
    private final JLabel card;
    private final JButton backToPack;
    private final JButton nextButton;

    LearnOutputBoundary learnOutputBoundary;
    LearnInputBoundary learnInputBoundary;
    LearnController learnController;

    public LearnFrame(ProgramStateInputBoundary programStateInputBoundary) {
        super("Learning", programStateInputBoundary);

        learnOutputBoundary = new LearnPresenter();
        learnInputBoundary = new LearnGenerator(programStateInputBoundary.getCurrPack(),
                learnOutputBoundary);
        learnController = new LearnController(learnInputBoundary);

        this.setSize(500,800);
        this.learnPanel = new JPanel(new GridLayout(3,1));

        learnController.next();
        String currCardStrRep = learnOutputBoundary.getCurrCardStrRep();
        this.card = new JLabel("<html><p>"+ currCardStrRep + "</p><html>", SwingConstants.CENTER);
        this.card.setBounds(10, 100, 300, 400);
        this.card.setFont(new Font("verdana", Font.BOLD , 30));

        this.nextButton = new JButton("Next");
        this.nextButton.setBounds(150,500,200,50);
        this.setFont(new Font("arial", Font.PLAIN,10));
        this.nextButton.addActionListener(this);

        this.backToPack = new JButton("Back to pack: " + programStateInputBoundary.getCurrPackName());
        this.setFont(new Font("arial", Font.PLAIN,10));
        this.backToPack.setBounds(10, 10, 200, 50);
        this.backToPack.addActionListener(this);

        addComp();
        add(learnPanel);
        setVisible(true);
    }

    private void addComp() {
        this.learnPanel.add(backToPack);
        this.learnPanel.add(card);
        this.learnPanel.add(nextButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.nextButton) {
            learnController.next();
            String currCardStrRep = learnOutputBoundary.getCurrCardStrRep();
            if (!learnOutputBoundary.getLearnCompleted()) {
                this.card.setText("<html><p>"+ currCardStrRep + "</p><html>");
            } else {
                JOptionPane.showMessageDialog(this, "You have finished learning all the cards.",
                        "Good job!", JOptionPane.WARNING_MESSAGE);
                new CardListFrame(programStateInputBoundary);
                setVisible(false);
            }
        } else if (e.getSource() == this.backToPack) {
            new CardListFrame(programStateInputBoundary);
            setVisible(false);
        }
    }

    public static void main(String[] args) throws IOException {
        ProgramStateInputBoundary ps = new ProgramStateManager();
        User user = new User("Yifan", "password");
        Pack vocab = new Pack("vocab");
        vocab.addCard(new Card("apple","fruit"));
        vocab.addCard(new Card("banana","fruit"));
        vocab.addCard(new Card("bee","animal"));
        vocab.addCard(new Card("new", "something you haven't seen before"));
        vocab.addCard(new Card("debugging", "something I hate"));
        user.addPackage(vocab);
        Loader loader = new Loader();
        loader.userLoad(user);
        ps.setCurrUser(user);
        ps.setCurrPack("vocab");
        new LearnFrame(ps);
    }
}
