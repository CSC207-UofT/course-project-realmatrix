package database;

import entity.Card;
import entity.Pack;
import entity.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CardWriter {

    public void writeCard(User user, Pack pack, Card card) throws IOException {
        new File("user_data/" + user.getName() + "/packages/" + pack.getName() + "/cards/").mkdirs();
        BufferedWriter writer =
                new BufferedWriter(new FileWriter("user_data/" + user.getName()
                        + "/packages/" + pack.getName() + "/cards/" + card.getTerm() + ".txt"));
        writer.write(card.getId() + "," + card.getTerm() + ","
                + card.getDefinition() + "," + card.getProficiency());
        writer.close();
    }

    public void eraseCard(User user, Pack pack, Card card) throws IOException {}

    public void updateCard(User user, Pack pack, Card card) throws IOException {}
}
