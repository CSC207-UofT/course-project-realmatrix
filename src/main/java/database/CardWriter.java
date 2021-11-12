package database;

import Controller.ProgramState;
import entity.Card;
import entity.Pack;
import entity.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class CardWriter extends Writer {

    /**
     * Write or update a card
     * @param state the state the program is in
     * @param o the object (card) to write/update
     * @throws IOException
     */
    @Override
    public void write(ProgramState state, Object o) throws IOException {
        getName(state);     // get the path name we should write into
        Card c = (Card) o;
        new File("user_data/users/" + this.username + "/packages/" + this.packname + "/cards/").mkdirs();
        BufferedWriter writer =
                new BufferedWriter(new FileWriter("user_data/users/" + this.username
                        + "/packages/" + this.packname + "/cards/" + c.getTerm() + ".txt"));
        writer.write(c.getId() + "," + c.getTerm() + ","
                + c.getDefinition() + "," + c.getProficiency());
        writer.close();
    }

    /**
     * Archive a card. Effectively, this card is deleted because it won't be loaded next time the program runs.
     * @param state the state the program is in
     * @param o the object (card) to archive
     * @throws IOException
     */
    @Override
    public void archive(ProgramState state, Object o) throws IOException {
        getName(state);     // get the path name we should write into
        Card card = (Card) o;
        new File("user_data/users/" + this.username + "/packages/" + this.packname +
                "/archived_cards/").mkdirs();
        Files.move(new File("user_data/users/" + this.username + "/packages/" + this.packname + "/cards/" +
                        card.getTerm() + ".txt").toPath(), new File("user_data/users/" +  this.username +
                        "/packages/" +  this.packname + "/archived_cards/" + card.getTerm() + ".txt").toPath());
    }
}
