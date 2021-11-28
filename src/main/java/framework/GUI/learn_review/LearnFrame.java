package framework.GUI.learn_review;

import entity.Pack;
import entity.User;
import framework.GUI.BasicFrame;
import framework.GUI.Pack.AddPackFrame;
import framework.GUI.Pack.PackFrame;
import interface_adapter.Controller.LearnController;
import interface_adapter.gateway.DataInOut;
import interface_adapter.gateway.IDataInOut;
import interface_adapter.gateway.dataout.Loader;
import interface_adapter.presenters.DatabaseErrMsgPresenter;
import interface_adapter.presenters.LearnPresenter;
import use_case.generator.LearnGenerator;
import use_case.input_boundaries.LearnInputBoundary;
import use_case.input_boundaries.ProgramStateInputBoundary;
import use_case.manager.ProgramStateManager;
import use_case.output_boundaries.DatabaseErrorOutputBoundary;
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

    public LearnFrame(ProgramStateInputBoundary programStateInputBoundary) {
        super("Learning", programStateInputBoundary);
        this.setSize(600,400);
        this.learnPanel = new JPanel(new GridLayout(3,1));

        this.card = new JLabel("<html><p>Click Next to start learning</p><html>", SwingConstants.CENTER);
        this.card.setBounds(10, 100, 300, 400);
        this.card.setFont(new Font("verdana", Font.BOLD , 30));


        this.nextButton = new JButton("Next");
        this.nextButton.setBounds(150,500,200,50);
        this.setFont(new Font("arial", Font.PLAIN,10));
        this.nextButton.addActionListener(this);

        this.backToPack = new JButton("Back to pack: " + programStateInputBoundary.getCurrPackName());
        this.setFont(new Font("arial", Font.PLAIN,10));
        this.backToPack.setBounds(10, 10, 200, 50);
        this.nextButton.addActionListener(this);

        addComp();
        add(learnPanel);
        setVisible(true);
    }
    private void addComp(){
        this.learnPanel.add(backToPack);
        this.learnPanel.add(card);
        this.learnPanel.add(nextButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.nextButton){
            if(check()){
                this.card.setText(programStateInputBoundary.getCurrCard().toString());
            }else{
                this.card.setText("<html><p>you have already completed learning of this pack</p><html>");
            }
        }else if(e.getSource() == this.backToPack){
            new PackFrame(programStateInputBoundary);
            setVisible(false);
        }
    }

    private boolean check(){
        LearnOutputBoundary learnOutputBoundary = new LearnPresenter();
        LearnInputBoundary learnInputBoundary = new LearnGenerator(programStateInputBoundary.getCurrPack(), learnOutputBoundary);
        LearnController learnController = new LearnController(learnInputBoundary);

        learnController.next();
        return !learnOutputBoundary.getLearnCompleted();
    }

    public static void main(String[] args) throws IOException {
        ProgramStateInputBoundary ps = new ProgramStateManager();
        User user = new User("Yifan", "password");
        Pack vocab = new Pack("vocab");
        user.addPackage(vocab);
        Loader loader = new Loader();
        loader.userLoad(user);
        ps.setCurrUser(user);
        ps.setCurrPack("vocab");
        new LearnFrame(ps);
    }
}
