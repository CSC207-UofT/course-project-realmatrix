package interface_adapter.gateway.datain;

import entity.Card;
import use_case.constants.Exceptions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
     *
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
     * @throws Exception if the path of oldName is invalid or the new path exits
     */
    @Override
    public void write(String oldName, Object newO) throws Exception {
        Card newCard = (Card) newO;
        try{
            Path old = Paths.get("user_data/users/" + this.username + "/packages/" + this.packname
                    + "/cards/" + oldName + ".txt");
            Files.move(old, old.resolveSibling(newCard.getTerm()));
        } catch (InvalidPathException e){
            throw new Exception(Exceptions.InvalidPath);
        } catch (IOException e){
            throw new Exception(Exceptions.WritePathExist);
        }
    }

    /**
     * Archive a card. Effectively, this card is deleted because it won't be loaded next time the program runs.
     *
     */
    @Override
    public void archive() throws Exception {
        new File("user_data/users/" + this.username + "/packages/" + this.packname +
                "/archived_cards/").mkdirs();
        try{
            Files.move(new File("user_data/users/" + this.username + "/packages/" + this.packname + "/cards/" +
                    this.card.getTerm() + ".txt").toPath(), new File("user_data/users/" + this.username +
                    "/packages/" + this.packname + "/archived_cards/" + this.card.getTerm() + ".txt").toPath());
        } catch (IOException e){
            throw new Exception(Exceptions.ArchivePathExist);
        }

    } // FIXME: same problem as UserWriter.archive
}
