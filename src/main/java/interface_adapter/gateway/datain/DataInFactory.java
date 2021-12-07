package interface_adapter.gateway.datain;

import entity.Card;
import entity.Pack;
import entity.User;

import java.io.IOException;

/**
 * A simple factory class
 * that determines which Writer class will be generated to write/archive objects.
 */
public class DataInFactory {

    /**
     * Construct a specific writer.
     *
     * @param partialDataPath the current state the program is in
     * @param o               the object needs to write/archive
     * @return a Writer according to objects we need to write/archive.
     * @throws IOException the object cannot be written/archived into database
     */
    public Writer getWriter(String[] partialDataPath, Object o) throws IOException {
        if (o instanceof Card) {
            return new CardWriter(partialDataPath, o);
        } else if (o instanceof Pack) {
            return new PackWriter(partialDataPath, o);
        } else if (o instanceof User) {
            return new UserWriter(partialDataPath, o);
        } else {
            throw new IOException();
        }
    }
}