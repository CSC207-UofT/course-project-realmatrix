package interface_adapter.gateway.datain;

import interface_adapter.Controller.ProgramState;
import entity.Card;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class CardWriter extends Writer {
    private final Card card;

    /**
     * Construct a CardWriter object.
     *
     * @param state the state the program is in
     * @param o     the object (card) to write/update
     */
    public CardWriter(ProgramState state, Object o) {
        super(state, o);
        this.card = (Card) o;
    }

    /**
     * Write or update a card
     *
     */
    @Override
    public void write() throws IOException {
        new File("user_data/users/" + this.username + "/packages/" + this.packname + "/cards/").mkdirs();
        BufferedWriter writer =
                new BufferedWriter(new FileWriter("user_data/users/" + this.username
                        + "/packages/" + this.packname + "/cards/" + this.card.getTerm() + ".txt"));
        writer.write(this.card.getId() + "," + this.card.getTerm() + ","
                + this.card.getDefinition() + "," + this.card.getProficiency());
        writer.close();
    }

    /**
     * Archive a card. Effectively, this card is deleted because it won't be loaded next time the program runs.
     *
     */
    @Override
    public void archive() throws IOException {
        new File("user_data/users/" + this.username + "/packages/" + this.packname +
                "/archived_cards/").mkdirs();
        Files.move(new File("user_data/users/" + this.username + "/packages/" + this.packname + "/cards/" +
                this.card.getTerm() + ".txt").toPath(), new File("user_data/users/" + this.username +
                "/packages/" + this.packname + "/archived_cards/" + this.card.getTerm() + ".txt").toPath());
    }
}
