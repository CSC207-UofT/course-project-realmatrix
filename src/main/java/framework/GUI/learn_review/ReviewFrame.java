package framework.GUI.learn_review;

import entity.Card;
import entity.Pack;
import entity.User;
import framework.GUI.BasicFrame;
import framework.GUI.card.CardListFrame;
import interface_adapter.controller.ReviewController;
import interface_adapter.gateway.dataout.Loader;
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

public class ReviewFrame extends BasicFrame implements ActionListener {
    private final JPanel reviewPanel;
    private final JLabel card;
    private final JButton button1;
    private final JButton button2;
    private final JButton button3;
    private final JButton button4;

    public ReviewFrame(ProgramStateInputBoundary programStateInputBoundary) {
        super("Reviewing", programStateInputBoundary);
        this.setSize(500,800);
        this.reviewPanel = new JPanel(new GridLayout(3, 1));
        this.card = new JLabel("<html><p>Click Remember Correct or Wrong to start reviewing</p><html>", SwingConstants.CENTER);
        this.card.setBounds(10, 100, 300, 400);
        this.card.setFont(new Font("verdana", Font.BOLD , 10));

        this.button1 = new JButton("Show Definition");
        this.button1.setBounds(150,500,200,50);
        this.setFont(new Font("arial", Font.PLAIN,10));
        this.button1.addActionListener(this);

        this.button2 = new JButton("Remember Wrong");
        this.setFont(new Font("arial", Font.PLAIN,10));
        this.button2.setBounds(10, 10, 200, 50);
        this.button2.addActionListener(this);

        this.button3 = new JButton("Remember Correctly");
        this.setFont(new Font("arial", Font.PLAIN,10));
        this.button3.setBounds(10, 10, 200, 50);
        this.button3.addActionListener(this);

        this.button4 = new JButton("Back to pack: " + programStateInputBoundary.getCurrPackName());
        this.setFont(new Font("arial", Font.PLAIN,10));
        this.button4.setBounds(10, 10, 200, 50);
        this.button4.addActionListener(this);

        addComp();
        add(reviewPanel);
        setVisible(true);
    }

    private void addComp(){
        this.reviewPanel.add(card);
        this.reviewPanel.add(button1);
        this.reviewPanel.add(button2);
        this.reviewPanel.add(button3);
        this.reviewPanel.add(button4);
    }

    public static void main(String[] args) throws IOException {
        ProgramStateInputBoundary ps = new ProgramStateManager();
        User user = new User("Runshi", "password");
        Pack vocab = new Pack("vocab");
        vocab.addCard(new Card("apple","fruit"));
        vocab.addCard(new Card("banana","fruit"));
        vocab.addCard(new Card("bee","animal"));
        user.addPackage(vocab);
        Loader loader = new Loader();
        loader.userLoad(user);
        ps.setCurrUser(user);
        ps.setCurrPack("vocab");
        new ReviewFrame(ps);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        ReviewOutputBoundary reviewOutputBoundary = new ReviewPresenter();
        ReviewInputBoundary reviewInputBoundary = new ReviewGenerator(programStateInputBoundary.getCurrPack(), reviewOutputBoundary);
        ReviewController reviewController = new ReviewController(reviewInputBoundary, programStateInputBoundary);
        try {
            reviewController.next();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if(e.getSource() == this.button1){
            this.card.setText(programStateInputBoundary.getCurrCard().getDefinition());
        }else if(e.getSource() == this.button2 || e.getSource() == this.button3){
            if(!reviewOutputBoundary.getReviewCompleted()){
                this.card.setText("<html><p>"+reviewOutputBoundary.getCurrCardStrRep() + "</p><html>");
            }else{
                this.card.setText("<html><p>you have already completed reviewing of this pack</p><html>");
            }
        }else if(e.getSource() == this.button4){
            new CardListFrame(programStateInputBoundary);
            setVisible(false);
        }
    }
}
