package database;

import entity.Card;
import entity.Pack;
import entity.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class CardWriter {

    /**
     * Write or update a card
     * @param user the user whose package whose card to write/update
     * @param pack the package whose card to write/update
     * @param card the card to write/update
     * @throws IOException
     */
    public void writeCard(User user, Pack pack, Card card) throws IOException {
        new File("user_data/users/" + user.getName() + "/packages/" + pack.getName() + "/cards/").mkdirs();
        BufferedWriter writer =
                new BufferedWriter(new FileWriter("user_data/users/" + user.getName()
                        + "/packages/" + pack.getName() + "/cards/" + card.getTerm() + ".txt"));
        writer.write(card.getId() + "," + card.getTerm() + ","
                + card.getDefinition() + "," + card.getProficiency());
        writer.close();
    }

    /**
     * Archive a card. Effectively, this card is deleted because it won't be loaded next time the program runs.
     * @param user the user whose package whose card to archive
     * @param pack the package whose card to archive
     * @param card the card to archive
     * @throws IOException
     */
    public void archiveCard(User user, Pack pack, Card card) throws IOException {
        new File("user_data/users/" + user.getName() + "/packages/" + pack.getName() +
                "/archived_cards/").mkdirs();
        Files.move(new File("user_data/users/" + user.getName() + "/packages/" + pack.getName() + "/cards/" +
                        card.getTerm() + ".txt").toPath(), new File("user_data/users/" + user.getName() +
                        "/packages/" + pack.getName() + "/archived_cards/" + card.getTerm() + ".txt").toPath());
    }
}
