package interface_adapter.gateway.datain;

import entity.Card;
import entity.Pack;
import entity.User;
import interface_adapter.Controller.ProgramState;

import java.io.IOException;

/**
 * A simple factory class
 * that determines which Writer class will be generated to write/archive objects.
 */
public class DataInFactory {

    /**
     * @param state the current state the program is in
     * @param o     the object needs to write/archive
     * @return a Writer according to objects we need to write/archive.
     * @throws IOException the object cannot be written/archived into database
     */
    // TODO: does checking data type follow clean architecture principles?
    public Writer getWriter(ProgramState state, Object o) throws IOException {
        if (o instanceof Card) {
            return new CardWriter(state, o);
        } else if (o instanceof Pack) {
            return new PackWriter(state, o);
        } else if (o instanceof User) {
            return new UserWriter(state, o);
        } else {
            throw new IOException("Cannot store such object into database. " +
                    "Please pass in only Card/Pack/User.");
        }

    }

//    /**
//     * Use the writer class to write the object into database.
//     * @throws IOException
//     */
//    @Override
//    public void write() throws IOException {
//        this.writer.write();
//    }
//
//    /**
//     * Use the writer class to write archive object into database.
//     * @throws IOException
//     */
//    @Override
//    public void archive() throws IOException {
//        this.writer.archive();
//    }
}
