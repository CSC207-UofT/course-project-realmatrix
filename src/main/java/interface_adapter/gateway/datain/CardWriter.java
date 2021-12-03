package interface_adapter.gateway.datain;

import entity.Card;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A writer class the Card.
 */
public class CardWriter extends Writer {
    private final Card card;        // New card to be written


    /**
     * Construct a CardWriter object.
     * @param partialDataPath the state the program is in
     * @param o     the object (card) to write/update
     */
    public CardWriter(String[] partialDataPath, Object o) {
        super(partialDataPath);
        this.card = (Card) o;
    }

    /**
     * Write a new card
     * @throws IOException fails to write
     */
    @Override
    public void write() throws IOException {
        String cardPath = "user_data/users/" + this.username + "/packages/" + this.packname + "/cards/";
        new File(cardPath).mkdirs();
        BufferedWriter writer =
                new BufferedWriter(new FileWriter(cardPath + this.card.getTerm() + ".txt"));
        writer.write(this.card.getDefinition() + "," + this.card.getProficiency());
        writer.close();
    }

    /**
     * Write the card's new term into database by renaming the card directory.
     *
     * @param oldName the card's old term
     * @param newO the card with new term
     * @throws IOException fails to write
     */
    @Override
    public void write(String oldName, Object newO) throws IOException {
        Card newCard = (Card) newO;
        Path old = Paths.get("user_data/users/" + this.username + "/packages/" + this.packname
                + "/cards/" + oldName + ".txt");
        Files.move(old, old.resolveSibling(newCard.getTerm() + ".txt"));
    }

    /**
     * Delete a card.
     * @throws IOException fails to write
     */
    @Override
    public void delete() throws IOException {
        new File("user_data/users/" + this.username + "/packages/" + this.packname + "/cards/" +
                this.card.getTerm() + ".txt").delete();
    }
}
