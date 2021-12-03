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
        learnInputBoundary = new LearnGenerator(programStateInputBoundary.getCurrPack(), learnOutputBoundary);
        learnController = new LearnController(learnInputBoundary);

        this.setSize(600,400);
        this.learnPanel = new JPanel();

        this.card = new JLabel("<html><p>Click Next to start learning</p><html>", SwingConstants.CENTER);

        this.card.setFont(new Font("verdana", Font.BOLD , 30));


        this.nextButton = new JButton("Next");
        this.nextButton.setFont(new Font("arial", Font.PLAIN,25));
        this.nextButton.addActionListener(this);

        this.backToPack = new JButton("Back to pack: " + programStateInputBoundary.getCurrPackName());
        this.backToPack.setFont(new Font("arial", Font.PLAIN,15));
        this.backToPack.addActionListener(this);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(this.backToPack);
        JPanel mid = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mid.add(this.card);
        JPanel low = new JPanel(new FlowLayout(FlowLayout.CENTER));
        low.add(this.nextButton);



        learnPanel.setLayout(new BorderLayout());
        learnPanel.add(top,BorderLayout.NORTH);
        learnPanel.add(mid,BorderLayout.CENTER);
        learnPanel.add(low,BorderLayout.SOUTH);

        add(learnPanel);
        setVisible(true);
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
                        "Good job!", JOptionPane.INFORMATION_MESSAGE);
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
        user.addPackage(vocab);
        Loader loader = new Loader();
        loader.userLoad(user);
        ps.setCurrUser(user);
        ps.setCurrPack("vocab");
        new LearnFrame(ps);
    }
}
